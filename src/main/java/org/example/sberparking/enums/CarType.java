package org.example.sberparking.enums;

import java.util.Arrays;

public enum CarType {
    PASSENGER("Легковая"),
    CARGO("Грузовая");

    String description;

    CarType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static void isValid(CarType carType) {
        if (Arrays.stream(CarType.values())
                .noneMatch(carType::equals)
        ) {
            throw new IllegalArgumentException("Данный тип автомобиля не найден!");
        }
    }
}
