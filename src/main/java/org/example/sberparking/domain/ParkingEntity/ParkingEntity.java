package org.example.sberparking.domain.ParkingEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.sberparking.domain.ParkingInfoEntity.ParkingInfoEntity;


@Entity
@Table(name = "parking")
@Getter
@Setter
public class ParkingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", unique = true, nullable = false)
    private String parkingNumber;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parking_info_id")
    private ParkingInfoEntity parkingInfoEntity;;

    public ParkingEntity(
            String parkingNumber
    ) {
        this.parkingNumber = parkingNumber;
    }

    public ParkingEntity() {}
}
