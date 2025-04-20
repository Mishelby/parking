package org.example.sberparking.repository;

import org.example.sberparking.domain.ParkingEntity.ParkingEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ParkingRepository extends JpaRepository<ParkingEntity, Long> {

    @Query("""
            SELECT pe
            FROM ParkingEntity pe
            WHERE pe.parkingNumber = :parkingNumber                        
            """)
    Optional<ParkingEntity> findByNumber(String parkingNumber);
}
