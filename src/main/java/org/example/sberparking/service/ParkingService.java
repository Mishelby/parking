package org.example.sberparking.service;

import lombok.extern.slf4j.Slf4j;
import org.example.sberparking.domain.CarEntity.EntryCarDto;
import org.example.sberparking.domain.CarInfoEntity.CarInfoEntity;
import org.example.sberparking.mapper.ParkingMapper;
import org.example.sberparking.repository.CarInfoRepository;
import org.example.sberparking.repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
        isValidParking(entryCarDto);
        var carInfoEntity = checkCarInfo(entryCarDto);
        var savedParking = parkingRepository.save(
                parkingMapper.toEntity(carInfoEntity)
        );

        return isValidParkingTime(savedParking.getParkingTime());
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

    private void isValidParking(EntryCarDto entryCarDto) {
        if (entryCarDto == null)
            throw new IllegalArgumentException("Entry car is null");

        if (entryCarDto.carNumber() == null || entryCarDto.carType() == null)
            throw new IllegalArgumentException("Car number or type is null");
    }

    private String isValidParkingTime(LocalDateTime parkingTime) {
        return parkingTime != null ? parkingTime.toString() : LocalDateTime.now().toString();
    }
}
