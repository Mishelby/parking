package org.example.sberparking.repository;

import org.example.sberparking.domain.ParkingInfoEntity.ParkingInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingInfoRepository extends JpaRepository<ParkingInfoEntity, Long> {

}
