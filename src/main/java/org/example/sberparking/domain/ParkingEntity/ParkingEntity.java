package org.example.sberparking.domain.ParkingEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.sberparking.domain.CarInfoEntity.CarInfoEntity;
import org.example.sberparking.domain.ParkingInfoEntity.ParkingInfoEntity;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "parking")
@Getter
@Setter
public class ParkingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "parkingEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<CarInfoEntity> carInfoEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parking_info_id")
    private ParkingInfoEntity parkingInfoEntity;

    @Column(name = "check_in_time")
    private LocalDateTime parkingTime;

    @Column(name = "exit_time")
    private LocalDateTime parkingEndTime;

    public ParkingEntity(
            List<CarInfoEntity> carInfoEntity
    ) {
        this.carInfoEntity = carInfoEntity;
    }

    public ParkingEntity() {}

    @PrePersist
    public void prePersist() {
        this.parkingTime = LocalDateTime.now();
    }
}
