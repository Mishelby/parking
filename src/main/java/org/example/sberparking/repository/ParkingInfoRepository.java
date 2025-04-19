package org.example.sberparking.repository;

import org.example.sberparking.domain.ParkingInfoEntity.ParkingInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingInfoRepository extends JpaRepository<ParkingInfoEntity, Long> {
}
