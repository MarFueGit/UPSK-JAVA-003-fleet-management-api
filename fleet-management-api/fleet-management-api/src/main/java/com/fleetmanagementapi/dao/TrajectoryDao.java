package com.fleetmanagementapi.dao;

import com.fleetmanagementapi.models.Taxi;
import com.fleetmanagementapi.models.Trajectorie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public  interface TrajectoryDao extends JpaRepository<Trajectorie, Integer> {
    Page<Trajectorie> findByTaxiIdAndDateGreaterThanEqual(int taxi_id, LocalDateTime date, Pageable pageable);
    @Query(value = "WITH trajectories AS (\n" +
            "    SELECT \n" +
            "        t.idtrajectorie,\n" +
            "        t.taxi_id,\n" +
            "        t.latitude,\n" +
            "        t.longitude,\n" +
            "        t.date,\n" +
            "        ROW_NUMBER() OVER (PARTITION BY t.taxi_id ORDER BY t.date DESC) AS row_num,\n" +
            "        ta.plate\n" +
            "    FROM \n" +
            "        trajectories t\n" +
            "    INNER JOIN\n" +
            "        taxis ta ON t.taxi_id = ta.idtaxi\n" +
            ")\n" +
            "SELECT \n" +
            "    idtrajectorie,\n" +
            "    taxi_id,\n" +
            "    latitude,\n" +
            "    longitude,\n" +
            "    date,\n" +
            "    plate\n" +
            "FROM \n" +
            "    trajectories\n" +
            "WHERE \n" +
            "    row_num = 1;",
            nativeQuery = true)
    List<Trajectorie> findLatestTrajectories();
}
