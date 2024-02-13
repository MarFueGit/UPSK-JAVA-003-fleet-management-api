package com.fleetmanagementapi.services;

import com.fleetmanagementapi.models.Taxi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface TaxiService {
    Page<Taxi> findAll(Pageable pageable);
}
