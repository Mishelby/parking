package org.example.sberparking.mapper;

import org.example.sberparking.domain.CarEntity.EntryCarDto;
import org.example.sberparking.domain.CarInfoEntity.CarInfoEntity;
import org.example.sberparking.domain.ParkingEntity.ParkingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ParkingMapper {

    @Mapping(target = "", source = "")
    ParkingEntity toEntity(CarInfoEntity carInfoEntity);
}
