package org.example.sberparking.domain.CarParking;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.sberparking.domain.CarEntity.CarEntity;
import org.example.sberparking.domain.ParkingEntity.ParkingEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "car_parking")
@Getter
@Setter
public class CarParkingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private CarEntity car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parking_id")
    private ParkingEntity parking;

    @Column(name = "check_in_time")
    private LocalDateTime checkInTime;

    @Column(name = "exit_time")
    private LocalDateTime exitTime;

    public CarParkingEntity(
            CarEntity car,
            ParkingEntity parking,
            LocalDateTime parkingTime
    ) {
        this.car = car;
        this.parking = parking;
        this.checkInTime = parkingTime;
    }


    public CarParkingEntity() {}
}
