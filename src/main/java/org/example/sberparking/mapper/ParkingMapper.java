package org.example.sberparking.mapper;

import org.example.sberparking.domain.CarInfoEntity.CarInfoEntity;
import org.example.sberparking.domain.ParkingEntity.ParkingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ParkingMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "carInfoEntity", expression = "java(defaultCarInfoList(parkingEntity))")
    @Mapping(target = "parkingTime", expression = "java(defaultParkingTime(parkingEntity))")
    @Mapping(target = "parkingEndTime", expression = "java(defaultParkingEndTime(parkingEntity))")
    ParkingEntity toEntity(CarInfoEntity carInfoEntity);

    default List<CarInfoEntity> defaultCarInfoList(ParkingEntity parkingEntity) {
        if (parkingEntity.getCarInfoEntity() == null) {
            parkingEntity.setCarInfoEntity(new ArrayList<>());
        }

        return parkingEntity.getCarInfoEntity();
    }

    default LocalDateTime defaultParkingTime(ParkingEntity parkingEntity) {
        if (parkingEntity.getParkingTime() == null) {
            parkingEntity.setParkingTime(LocalDateTime.now());
        }
        return parkingEntity.getParkingTime();
    }

    default LocalDateTime defaultParkingEndTime(ParkingEntity parkingEntity) {
        parkingEntity.setParkingEndTime(null);
        return parkingEntity.getParkingEndTime();
    }
}
