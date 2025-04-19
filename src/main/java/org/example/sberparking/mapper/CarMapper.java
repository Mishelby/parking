package org.example.sberparking.mapper;

import org.example.sberparking.domain.CarEntity.CarEntity;
import org.example.sberparking.domain.CarEntity.CreateCarDto;
import org.example.sberparking.enums.CarType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Arrays;

@Mapper(componentModel = "spring")
public interface CarMapper {

    @Mapping(target = "number", source = "createCarDto.number")
    @Mapping(target = "carType", source = "createCarDto.type")
    CarEntity toEntity(CreateCarDto createCarDto);

    default CarType carTypeFromString(
            String carType
    ) {
        return Arrays.stream(CarType.values())
                .filter(type -> type.getDescription().equalsIgnoreCase(carType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid car type: %s".formatted(carType)));
    }
}
