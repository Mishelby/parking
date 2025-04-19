package org.example.sberparking.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum CarType {
    PASSENGER("Легковой"),
    CARGO("Грузовой");

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

    @JsonValue
    public String getJsonValue() {
        return description;
    }

    @JsonCreator
    public static CarType fromString(String description) {
        for (CarType type : CarType.values()) {
            if (type.getDescription().equals(description)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown enum value: " + description);
    }
}
