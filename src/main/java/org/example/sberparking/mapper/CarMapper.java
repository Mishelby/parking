package org.example.sberparking.mapper;

import org.example.sberparking.domain.CarEntity.CarEntity;
import org.example.sberparking.domain.CarEntity.CreateCarDto;
import org.example.sberparking.enums.CarType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarMapper {

    @Mapping(target = "number", source = "number")
    @Mapping(target = "carType", expression = "java(carTypeToString(createCarDto.type()))")
    CarEntity toEntity(CreateCarDto createCarDto);

    default CarType carTypeToString(
            String carType
    ) {
        var type = CarType.valueOf(carType);
        CarType.isValid(type);
        return type;
    }
}
