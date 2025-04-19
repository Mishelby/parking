package org.example.sberparking.repository;

import org.example.sberparking.domain.ParkingEntity.ParkingEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingRepository extends JpaRepository<ParkingEntity, Long> {

    @EntityGraph(attributePaths = {"carInfoEntity"})
    @Query("""
            SELECT pe
            FROM ParkingEntity pe
            LEFT JOIN FETCH CarInfoEntity cie ON pe.id = cie.parkingEntity.id
            WHERE cie.carNumber =: carNumber
            """)
    Optional<ParkingEntity> findByCarNumber(String carNumber);


    @EntityGraph(attributePaths = {"carInfoEntity", "parkingInfoEntity"})
    @Query("""
            SELECT pe 
            FROM ParkingEntity pe
            WHERE pe.parkingTime BETWEEN :startTime AND :endTime
            """)
    Optional<ParkingEntity> findByDate(String startTime, String endTime);
}
