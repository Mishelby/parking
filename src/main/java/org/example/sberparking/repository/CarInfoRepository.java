package org.example.sberparking.repository;

import org.example.sberparking.domain.CarEntity.EntryCarDto;
import org.example.sberparking.domain.CarInfoEntity.CarInfoEntity;
import org.example.sberparking.enums.CarType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarInfoRepository extends JpaRepository<CarInfoEntity, Long> {
    @Query("""
            SELECT (COUNT(*) > 0) 
            FROM CarInfoEntity cie
            WHERE cie.carNumber =: carNumber
            AND cie.carType =: carType           
            """)
    boolean isExists(
            @Param("carNumber") String carNumber,
            @Param("carType") CarType carType
    );

    @Query("SELECT cie FROM CarInfoEntity cie WHERE cie.carNumber =: carNumber")
    Optional<CarInfoEntity> findByCarNumber(@Param("carNumber") String carNumber);
}
