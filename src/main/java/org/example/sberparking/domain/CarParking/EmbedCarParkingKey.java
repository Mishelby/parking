package org.example.sberparking.domain.CarParking;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmbedCarParkingKey {
    @Column(name = "car_id")
    private Long carId;
    @Column(name = "parking_id")
    private Long parkingId;
}
