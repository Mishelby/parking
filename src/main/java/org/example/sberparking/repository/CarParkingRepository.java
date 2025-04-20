package org.example.sberparking.repository;

import org.example.sberparking.domain.CarParking.CarParkingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarParkingRepository extends JpaRepository<CarParkingEntity, Long> {
    @Query("""
            SELECT (COUNT(cpe) > 0)
            FROM CarParkingEntity cpe         
            WHERE cpe.car.number = :carNumber 
            AND cpe.parking.parkingNumber = :parkingNumber                          
            """)
    boolean isParkingExistsCar(String carNumber, String parkingNumber);

    @Query("""
            SELECT cpe
            FROM CarParkingEntity cpe
            WHERE cpe.parking.parkingNumber = :parkingNumber
            AND cpe.car.number = :carNumber                                    
            """)
    Optional<CarParkingEntity> findByCarAndParkingNumber(
            String parkingNumber,
            String carNumber
    );

}
