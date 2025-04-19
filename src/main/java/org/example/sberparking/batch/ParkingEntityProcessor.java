package org.example.sberparking.batch;

import org.example.sberparking.domain.ParkingEntity.ParkingEntity;
import org.example.sberparking.domain.ParkingEntity.ParkingReportDto;
import org.springframework.batch.item.ItemProcessor;

import java.util.List;

public class ParkingEntityProcessor implements ItemProcessor<ParkingEntity, List<ParkingReportDto>> {
    @Override
    public List<ParkingReportDto> process(ParkingEntity parking) throws Exception {
        var carsInfo = parking.getCarInfoEntity();

        return carsInfo.stream()
                .map(carInfo -> {
                    var parkingInfoEntity = carInfo.getParkingEntity();
                    return new ParkingReportDto(
                            carInfo.getCarNumber(),
                            carInfo.getCarType().getDescription(),
                            parkingInfoEntity.getParkingTime(),
                            parkingInfoEntity.getParkingEndTime()
                    );
                }).toList();
    }
}
