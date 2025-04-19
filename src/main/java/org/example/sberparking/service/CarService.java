package org.example.sberparking.service;

import lombok.extern.slf4j.Slf4j;
import org.example.sberparking.domain.CarEntity.CarEntity;
import org.example.sberparking.domain.CarEntity.CreateCarDto;
import org.example.sberparking.mapper.CarMapper;
import org.example.sberparking.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Autowired
    public CarService(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    @Transactional
    public String createCar(
            CreateCarDto createCarDto
    ) {
        log.info("Creating new car {}", createCarDto);
        isCarValid(createCarDto);

        var savedCar = carRepository.save(
                carMapper.toEntity(createCarDto)
        );
        log.info("Saved car {}", savedCar);

        return savedCar.getNumber();
    }

    private static void isCarValid(
            CreateCarDto createCarDto
    ) {
        if (createCarDto == null)
            throw new IllegalArgumentException("CreateCarDto cannot be null");

        if (createCarDto.number() == null || createCarDto.type() == null)
            throw new IllegalArgumentException("type or number cannot be null");
    }
}
