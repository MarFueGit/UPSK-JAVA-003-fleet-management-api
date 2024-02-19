package com.fleetmanagementapi.dao;

import com.fleetmanagementapi.models.Taxi;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TaxiDao extends JpaRepository<Taxi, Integer> {
}
