package com.fleetmanagementapi.services;
import com.fleetmanagementapi.models.Trajectorie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;

public interface TrajectoryService {
    Page<Trajectorie> findByTaxiIdAndDateGreaterThanEqual(int taxiId, LocalDateTime date, Pageable pageable);
}
