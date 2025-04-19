package org.example.sberparking.domain.CarInfoEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.sberparking.enums.CarType;

@Entity
@Table(name = "car_info")
@Getter
@Setter
public class CarInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "car_number",nullable = false)
    private String carNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "car_type")
    private CarType carType;

    public CarInfoEntity(
            String carNumber,
            CarType carType
    ) {
        this.carNumber = carNumber;
        this.carType = carType;
    }

    public CarInfoEntity() {
    }
}
