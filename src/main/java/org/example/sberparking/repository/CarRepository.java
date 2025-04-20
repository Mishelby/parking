package org.example.sberparking.repository;

import org.example.sberparking.domain.CarEntity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {
    @Query("""
            SELECT (COUNT(c) > 0)
            FROM CarEntity c
            WHERE c.number = :number                        
            """)
    boolean isExistsByNumber(String number);

    Optional<CarEntity> findByNumber(String number);
}
