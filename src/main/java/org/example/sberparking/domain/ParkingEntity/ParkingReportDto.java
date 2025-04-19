package org.example.sberparking.domain.ParkingEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record ParkingReportDto(
        String carNumber,
        String carType,
        LocalDateTime checkInTime,
        LocalDateTime exitTime
) {
}
