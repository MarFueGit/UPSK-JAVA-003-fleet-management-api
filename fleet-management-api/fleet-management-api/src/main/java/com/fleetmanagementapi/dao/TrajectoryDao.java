package com.fleetmanagementapi.dao;

import com.fleetmanagementapi.models.Taxi;
import com.fleetmanagementapi.models.Trajectorie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public  interface TrajectoryDao extends JpaRepository<Trajectorie, Integer> {
    Page<Trajectorie> findByTaxiIdAndDateGreaterThanEqual(int taxi_id, LocalDateTime date, Pageable pageable);
}
