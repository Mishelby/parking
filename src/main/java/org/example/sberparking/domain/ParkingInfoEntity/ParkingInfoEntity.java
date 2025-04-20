package org.example.sberparking.domain.ParkingInfoEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "parking_info")
@Getter
@Setter
public class ParkingInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "count_of_seats")
    private Long countOfSeats;

    @Column(name = "count_of_free_seats")
    private Long countOfFreeSeats;

    @Column(name = "count_of_occupied_seats")
    private Long countOfOccupiedSeats;

    public ParkingInfoEntity(
            Long countOfSeats
    ) {
        this.countOfSeats = countOfSeats;
        this.countOfFreeSeats = countOfSeats;
        this.countOfOccupiedSeats = 0L;
    }

    public ParkingInfoEntity() {}
}
