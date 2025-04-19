package org.example.sberparking.domain.CarEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.sberparking.enums.CarType;

@Entity
@Table(name = "car")
@Getter
@Setter
@ToString
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "car_number", unique = true, nullable = false)
    private String number;

    @Enumerated(EnumType.STRING)
    @Column(name = "car_type")
    private CarType carType;

    public CarEntity(
            String number,
            CarType carType
    ) {
        this.number = number;
        this.carType = carType;
    }

    public CarEntity() {}
}
