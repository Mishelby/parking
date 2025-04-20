package org.example.sberparking.service;

import lombok.extern.slf4j.Slf4j;
import org.example.sberparking.domain.CarEntity.CarEntity;
import org.example.sberparking.domain.CarEntity.EntryCarDto;
import org.example.sberparking.domain.CarEntity.ExitCarDto;
import org.example.sberparking.domain.CarParking.CarParkingEntity;
import org.example.sberparking.domain.ParkingEntity.AverageParkingInfo;
import org.example.sberparking.domain.ParkingEntity.ParkingEntity;
import org.example.sberparking.domain.ParkingInfoEntity.ParkingInfoDto;
import org.example.sberparking.domain.ParkingInfoEntity.ParkingInfoEntity;
import org.example.sberparking.mapper.ParkingMapper;
import org.example.sberparking.repository.CarParkingRepository;
import org.example.sberparking.repository.CarRepository;
import org.example.sberparking.repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ParkingService {
    private final ParkingRepository parkingRepository;
    private final CarParkingRepository carParkingRepository;
    private final ParkingMapper parkingMapper;

    private static final Integer ONE = 1;
    private static final Integer CORRECT_SEATS = 100;
    private final CarRepository carRepository;

    @Autowired
    public ParkingService(
            ParkingRepository parkingRepository,
            CarParkingRepository carParkingRepository,
            ParkingMapper parkingMapper,
            CarRepository carRepository) {
        this.parkingRepository = parkingRepository;
        this.carParkingRepository = carParkingRepository;
        this.parkingMapper = parkingMapper;
        this.carRepository = carRepository;
    }

    @Transactional
    public String createParking(Long countOfSeats) {
        isParkingValidData(countOfSeats);
        var savedParkingEntity = parkingRepository.save(
                parkingMapper.toEntity(
                        new ParkingInfoEntity(countOfSeats),
                        UUID.randomUUID().toString()
                )
        );

        return savedParkingEntity.getParkingInfoEntity().getCountOfSeats() + " seats";
    }

    @Transactional
    public String entryCar(
            EntryCarDto entryCarDto
    ) {
        log.info("Entry car for parking: {}", entryCarDto);
        isValidParking(entryCarDto);
        isCarAlreadyParking(entryCarDto);

        var parkingEntity = parkingRepository.findByNumber(entryCarDto.parkingNumber())
                .orElseThrow(() -> new IllegalArgumentException("Parking with number %s not found!"
                        .formatted(entryCarDto.parkingNumber())));
        var carEntity = carRepository.findByNumber(entryCarDto.carNumber()).orElseThrow();

        if (parkingEntity.getParkingInfoEntity().getCountOfFreeSeats()
                .equals(0L))
            throw new IllegalArgumentException("Нет места на парковке! %s".formatted(entryCarDto.parkingNumber()));

        setEntryParkingSeats(parkingEntity);
        var savedCarParking = carParkingRepository.save(
                getCarParkingEntity(carEntity, parkingEntity)
        );

        if (Boolean.FALSE.equals(carEntity.isParking())) {
            carEntity.setIsParking(true);
        }

        parkingRepository.save(parkingEntity);
        return isValidParkingTime(savedCarParking.getCheckInTime());
    }

    @Transactional
    public String exitParkingCar(
            ExitCarDto exitCarDto
    ) {
        log.info("Exit car from parking: {}", exitCarDto);
        checkCarNumber(exitCarDto.carNumber());
        isCarInParking(exitCarDto);

        var parkingEntity = parkingRepository.findByNumber(exitCarDto.parkingNumber()).orElseThrow(
                () -> new IllegalArgumentException("Parking with number %s not found!"
                        .formatted(exitCarDto.parkingNumber()))
        );
        var carEntity = carRepository.findByNumber(exitCarDto.carNumber()).orElseThrow();

        setExitParkingSeats(parkingEntity);
        var carParkingEntity = carParkingRepository.findByCarAndParkingNumber(
                exitCarDto.parkingNumber(),
                exitCarDto.carNumber()
        ).orElseThrow();
        carParkingEntity.setExitTime(LocalDateTime.now());

        if (Boolean.TRUE.equals(carEntity.getIsParking())) {
            carEntity.setIsParking(false);
        }

        parkingRepository.save(parkingEntity);
        return isValidParkingTime(carParkingEntity.getExitTime());
    }

    @Transactional(readOnly = true)
    public ParkingInfoDto findParkingInfo(
            LocalDateTime startTime,
            LocalDateTime endTime
    ) {
        var carsParking = carParkingRepository.getCarParkingByDateAndParkingNumber(startTime, endTime);
        if (carsParking.isEmpty())
            return new ParkingInfoDto(0L, 0L, null);

        var parkingEntity = parkingRepository.findById(carsParking.get(0).getParking().getId()).orElseThrow(
                () -> new IllegalArgumentException("Parking with id %s not found!"
                        .formatted(carsParking.get(0).getParking().getId()))
        );

        var allParkingTime = carsParking.stream()
                .filter(carParkingEntity -> carParkingEntity.getExitTime() != null)
                .mapToDouble(carParking -> {
                    LocalDateTime checkInTime = carParking.getCheckInTime();
                    LocalDateTime exitTime = carParking.getExitTime();
                    return Duration.between(checkInTime, exitTime).toMinutes();
                }).sum();

        double hours = allParkingTime / 60;
        double minutes = allParkingTime % 60;
        double seconds = allParkingTime % 60 / 60;

        return new ParkingInfoDto(
                parkingEntity.getParkingInfoEntity().getCountOfOccupiedSeats(),
                parkingEntity.getParkingInfoEntity().getCountOfFreeSeats(),
                new AverageParkingInfo(hours, minutes, seconds)
        );
    }

    private static void isParkingValidData(Long countOfSeats) {
        if (countOfSeats == null || countOfSeats < CORRECT_SEATS)
            throw new IllegalArgumentException("Invalid data for creating parking");
    }

    private static CarParkingEntity getCarParkingEntity(
            CarEntity carEntity,
            ParkingEntity parkingEntity
    ) {
        return new CarParkingEntity(carEntity, parkingEntity, LocalDateTime.now());
    }

    private void isCarAlreadyParking(EntryCarDto entryCarDto) {
        carParkingRepository.findByCarAndParkingNumber(
                entryCarDto.parkingNumber(),
                entryCarDto.carNumber()
        ).ifPresent(parking -> {
            if (parking.getExitTime() == null) {
                throw new IllegalArgumentException("Car already parking! %s"
                        .formatted(entryCarDto.carNumber()));
            }
        });
    }

    private static void setEntryParkingSeats(ParkingEntity parkingEntity) {
        var countOfFreeSeats = parkingEntity.getParkingInfoEntity().getCountOfFreeSeats();
        var countOfOccupiedSeats = parkingEntity.getParkingInfoEntity().getCountOfOccupiedSeats();
        parkingEntity.getParkingInfoEntity().setCountOfFreeSeats(countOfFreeSeats - ONE);
        parkingEntity.getParkingInfoEntity().setCountOfOccupiedSeats(countOfOccupiedSeats + ONE);
    }

    private static void setExitParkingSeats(ParkingEntity parkingEntity) {
        var countOfFreeSeats = parkingEntity.getParkingInfoEntity().getCountOfFreeSeats();
        var countOfOccupiedSeats = parkingEntity.getParkingInfoEntity().getCountOfOccupiedSeats();
        parkingEntity.getParkingInfoEntity().setCountOfFreeSeats(countOfFreeSeats + ONE);
        parkingEntity.getParkingInfoEntity().setCountOfOccupiedSeats(countOfOccupiedSeats - ONE);
    }

    private void isCarInParking(
            ExitCarDto exitCarDto
    ) {
        if (!carParkingRepository.isParkingExistsCar(exitCarDto.carNumber(), exitCarDto.parkingNumber()))
            throw new IllegalArgumentException("Car with number %s not exists in parking!"
                    .formatted(exitCarDto.carNumber()));
    }

    private void checkCarNumber(String carNumber) {
        if (carNumber == null)
            throw new IllegalArgumentException("Car number cannot be null!");
    }

    private void isValidParking(EntryCarDto entryCarDto) {
        if (entryCarDto == null)
            throw new IllegalArgumentException("Entry car is null");

        if (entryCarDto.carNumber() == null || entryCarDto.carType() == null)
            throw new IllegalArgumentException("Car number or type is null");
    }

    private String isValidParkingTime(LocalDateTime parkingTime) {
        return parkingTime != null
                ? parkingTime.toString()
                : LocalDateTime.now().toString();
    }
}
