package org.example.sberparking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SberParkingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SberParkingApplication.class, args);
    }

}
