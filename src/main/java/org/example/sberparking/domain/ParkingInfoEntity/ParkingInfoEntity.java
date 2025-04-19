package org.example.sberparking.domain.ParkingInfoEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.sberparking.domain.ParkingEntity.ParkingEntity;

@Entity
@Table(name = "parking_info")
@Getter
@Setter
public class ParkingInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ParkingEntity parkingEntity;

    @Column(name = "count_of_seats")
    private Integer countOfSeats;

    @Column(name = "count_of_free_seats")
    private Integer countOfFreeSeats;

    @Column(name = "count_of_occupied_seats")
    private Integer countOfOccupiedSeats;

    public ParkingInfoEntity(
            ParkingEntity parkingEntity,
            Integer countOfSeats
    ) {
        this.parkingEntity = parkingEntity;
        this.countOfSeats = countOfSeats;
        this.countOfFreeSeats = countOfSeats;
        this.countOfOccupiedSeats = 0;
    }

    public ParkingInfoEntity() {}
}
