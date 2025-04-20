package org.example.sberparking.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sberparking.domain.CarEntity.EntryCarDto;
import org.example.sberparking.domain.CarEntity.ExitCarDto;
import org.example.sberparking.domain.ParkingInfoEntity.ParkingInfoDto;
import org.example.sberparking.service.ParkingService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api/v1/parking")
@RequiredArgsConstructor
public class ParkingController {
    private final ParkingService parkingService;

    @PostMapping
    public ResponseEntity<String> entryParkingCar(
            @RequestParam Long countOfSeats
    ) {
        log.info("Post request for create parking{}", countOfSeats);
        return ResponseEntity.ok().body(parkingService.createParking(countOfSeats));
    }

    @PostMapping("/entry")
    public ResponseEntity<String> entryParkingCar(
            @RequestBody EntryCarDto entryCarDto
    ) {
        log.info("Post request for entry car {}", entryCarDto);
        return ResponseEntity.ok().body(parkingService.entryCar(entryCarDto));
    }

    @PostMapping("/exit")
    public ResponseEntity<String> exitParkingCar(
            @RequestBody ExitCarDto exitCarDto
    ) {
        log.info("Post request for exit parking car");
        return ResponseEntity.ok().body(parkingService.exitParkingCar(exitCarDto));
    }

    @GetMapping("/report/")
    public ResponseEntity<ParkingInfoDto> getParkingInfo(
            @RequestParam("start_date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime startDate,

            @RequestParam("end_date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime endDate
    ) {
        log.info("Get request for parking info {} {}", startDate, endDate);
        return ResponseEntity.ok().body(parkingService.findParkingInfo(startDate, endDate));
    }

}
