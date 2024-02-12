package com.fleetmanagementapi.dao;

import com.fleetmanagementapi.models.Taxi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaxiDao extends JpaRepository<Taxi, Integer> {
}
