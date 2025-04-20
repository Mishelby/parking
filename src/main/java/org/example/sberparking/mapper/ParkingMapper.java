package org.example.sberparking.mapper;

import org.example.sberparking.domain.ParkingEntity.ParkingEntity;
import org.example.sberparking.domain.ParkingInfoEntity.ParkingInfoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ParkingMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "parkingNumber", source = "uniqueNumber")
    @Mapping(target = "parkingInfoEntity", source = "parkingInfoEntity")
    ParkingEntity toEntity(ParkingInfoEntity parkingInfoEntity, String uniqueNumber);
}
