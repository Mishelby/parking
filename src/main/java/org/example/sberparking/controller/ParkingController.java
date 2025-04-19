package org.example.sberparking.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sberparking.domain.CarEntity.EntryCarDto;
import org.example.sberparking.domain.ParkingEntity.ParkingInfoDto;
import org.example.sberparking.service.ParkingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/parking")
@RequiredArgsConstructor
public class ParkingController {
    private final ParkingService parkingService;

    @PostMapping("/entry")
    public ResponseEntity<String> entryParkingCar(EntryCarDto entryCarDto) {
        log.info("Post request for entry car {}", entryCarDto);
        return ResponseEntity.ok().body(parkingService.entryCar(entryCarDto));
    }

    @PostMapping("/exit")
    public ResponseEntity<String> exitParkingCar(
            @RequestParam("carNumber") String carNumber
    ) {
        log.info("Post request for exit parking car");
        return ResponseEntity.ok().body(parkingService.exitParkingCar(carNumber));
    }

    @GetMapping("/report/")
    public ResponseEntity<ParkingInfoDto> getParkingInfo(
            @RequestParam("start_date") String startDate,
            @RequestParam("end_date") String endDate
    ) {
        log.info("Get request for parking info {} {}", startDate, endDate);
        return ResponseEntity.ok().body(parkingService.findParkingInfo(startDate, endDate));
    }

}
