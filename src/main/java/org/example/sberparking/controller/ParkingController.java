package org.example.sberparking.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sberparking.domain.CarEntity.EntryCarDto;
import org.example.sberparking.service.ParkingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/parking")
@RequiredArgsConstructor
public class ParkingController {
    private final ParkingService parkingService;

    @PostMapping("/entry")
    public ResponseEntity<String> entryCar(EntryCarDto entryCarDto) {
        log.info("Post request for entry car {}", entryCarDto);
        return ResponseEntity.ok().body(parkingService.entryCar(entryCarDto));
    }

}
