package org.example.sberparking.domain.CarEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.example.sberparking.enums.CarType;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record EntryCarDto(
        String carNumber,
        CarType carType
) {
}
