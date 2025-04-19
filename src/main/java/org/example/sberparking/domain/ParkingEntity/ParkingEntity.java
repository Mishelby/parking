package org.example.sberparking.domain.ParkingEntity;

import jakarta.persistence.*;

@Entity
@Table(name = "parking")
public class ParkingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
