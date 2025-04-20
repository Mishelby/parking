package org.example.sberparking.domain.ParkingInfoEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.example.sberparking.domain.ParkingEntity.AverageParkingInfo;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record ParkingInfoDto(
        Long countOfOccupiedSeats,
        Long countOfFreeSeats,
        AverageParkingInfo averageStayTime
) {
}
