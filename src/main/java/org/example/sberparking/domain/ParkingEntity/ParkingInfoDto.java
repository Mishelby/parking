package org.example.sberparking.domain.ParkingEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record ParkingInfoDto(
        Integer countOfOccupiedSeats,
        Integer countOfFreeSeats,
        AverageParkingInfo averageStayTime
) {
}
