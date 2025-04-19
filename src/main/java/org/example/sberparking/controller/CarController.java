package org.example.sberparking.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sberparking.domain.CarEntity.CreateCarDto;
import org.example.sberparking.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/car")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @PostMapping
    public ResponseEntity<String> postCar(
            @RequestBody CreateCarDto createCarDto
    ){
        log.info("Post request for create car {}", createCarDto);
        return ResponseEntity.ok().body(carService.createCar(createCarDto));
    }
}
