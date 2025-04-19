package org.example.sberparking.domain.ParkingEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.sberparking.domain.CarInfoEntity.CarInfoEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "parking")
@Getter
@Setter
public class ParkingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private CarInfoEntity carInfoEntity;

    @Column(name = "check_in_time")
    private LocalDateTime parkingTime;

    @Column(name = "exit_time")
    private LocalDateTime parkingEndTime;

    public ParkingEntity(
            CarInfoEntity carInfoEntity
    ) {
        this.carInfoEntity = carInfoEntity;
    }

    public ParkingEntity() {}

    @PrePersist
    public void prePersist() {
        this.parkingTime = LocalDateTime.now();
    }
}
