package org.example.sberparking.service;

import lombok.extern.slf4j.Slf4j;
import org.example.sberparking.domain.CarEntity.EntryCarDto;
import org.example.sberparking.domain.CarInfoEntity.CarInfoEntity;
import org.example.sberparking.domain.ParkingEntity.AverageParkingInfo;
import org.example.sberparking.domain.ParkingEntity.ParkingEntity;
import org.example.sberparking.domain.ParkingEntity.ParkingInfoDto;
import org.example.sberparking.mapper.ParkingMapper;
import org.example.sberparking.repository.CarInfoRepository;
import org.example.sberparking.repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ParkingService {
    private final ParkingRepository parkingRepository;
    private final CarInfoRepository carInfoRepository;
    private final ParkingMapper parkingMapper;

    @Autowired
    public ParkingService(
            ParkingRepository parkingRepository,
            CarInfoRepository carInfoRepository,
            ParkingMapper parkingMapper
    ) {
        this.parkingRepository = parkingRepository;
        this.carInfoRepository = carInfoRepository;
        this.parkingMapper = parkingMapper;
    }

    @Transactional
    public String entryCar(
            EntryCarDto entryCarDto
    ) {
        log.info("Entry car for parking: {}", entryCarDto);
        isValidParking(entryCarDto);

        var carInfoEntity = checkCarInfo(entryCarDto);
        var parkingEntity = new ParkingEntity();
        parkingEntity.getCarInfoEntity().add(carInfoEntity);
        var savedParking = parkingRepository.save(parkingEntity);
        log.info("Saved parking: {}", savedParking);

        return isValidParkingTime(savedParking.getParkingTime());
    }

    @Transactional
    public String exitParkingCar(
            String carNumber
    ) {
        checkCarNumber(carNumber);
        var parkingEntity = parkingRepository.findByCarNumber(carNumber).orElseThrow(
                () -> new IllegalArgumentException("Parking for car with number %s not found!"
                        .formatted(carNumber))
        );

        parkingEntity.setParkingTime(LocalDateTime.now());
        return parkingEntity.getParkingEndTime().toString();
    }

    @Transactional(readOnly = true)
    public ParkingInfoDto findParkingInfo(
            String startTime,
            String endTime
    ) {
        var parkingEntity = parkingRepository.findByDate(startTime, endTime).orElseThrow(
                () -> new IllegalArgumentException("Not parking info found for time between %s and %s!"
                        .formatted(startTime, endTime))
        );
        double allTimeInMinutes = parkingEntity.getCarInfoEntity()
                .stream()
                .filter(car -> car.getParkingEntity().getParkingEndTime() != null)
                .mapToDouble(car -> {
                    var startParkingTime = car.getParkingEntity().getParkingTime();
                    var endParkingTime = car.getParkingEntity().getParkingEndTime();
                    return Duration.between(startParkingTime, endParkingTime).toMinutes();
                }).sum();

        double hours = Math.floor(allTimeInMinutes / 60);
        double minutes = allTimeInMinutes % 60;
        double seconds = (minutes - Math.floor(minutes)) * 60;

        return new ParkingInfoDto(
                parkingEntity.getParkingInfoEntity().getCountOfOccupiedSeats(),
                parkingEntity.getParkingInfoEntity().getCountOfFreeSeats(),
                new AverageParkingInfo(
                        roundTo1Decimal(hours),
                        roundTo1Decimal(Math.floor(minutes)),
                        roundTo1Decimal(seconds)
                )
        );
    }

    private static double roundTo1Decimal(double value) {
        return Math.round(value * 10.0) / 10.0;
    }

    private CarInfoEntity checkCarInfo(
            EntryCarDto entryCarDto
    ) {
        if (carInfoRepository.isExists(entryCarDto.carNumber(), entryCarDto.carType())) {
            return carInfoRepository.findByCarNumber(entryCarDto.carNumber()).orElseThrow();
        }

        return carInfoRepository.save(
                new CarInfoEntity(
                        entryCarDto.carNumber(), entryCarDto.carType()
                ));
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
