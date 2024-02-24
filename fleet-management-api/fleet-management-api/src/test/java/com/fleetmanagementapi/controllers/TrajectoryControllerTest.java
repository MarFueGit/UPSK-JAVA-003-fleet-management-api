package com.fleetmanagementapi.controllers;

import com.fleetmanagementapi.controllers.TrajectoryController;
import com.fleetmanagementapi.models.Trajectorie;
import com.fleetmanagementapi.services.TrajectoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TrajectoryControllerTest {

    @Mock
    private TrajectoryService trajectoryService;

    @InjectMocks
    private TrajectoryController trajectoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGet() {
        // Mock data
        int taxiId = 1;
        LocalDateTime date = LocalDateTime.of(2022, 1, 1, 0, 0);
        Pageable pageable = Pageable.unpaged();

        Trajectorie trajectory = new Trajectorie();
        trajectory.setIdTrajectorie(1);
        trajectory.setTaxiId(taxiId);
        trajectory.setDate(date);

        // Mock behavior of trajectoryService.findByTaxiIdAndDateGreaterThanEqual()
        when(trajectoryService.findByTaxiIdAndDateGreaterThanEqual(anyInt(), any(LocalDateTime.class), any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.singletonList(trajectory)));

        // Call the method to be tested
        ResponseEntity<Object> responseEntity = trajectoryController.get(taxiId, LocalDate.of(2022, 1, 1), pageable);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Page<Trajectorie> result = (Page<Trajectorie>) responseEntity.getBody();
        assertEquals(1, result.getTotalElements());
        assertEquals(taxiId, result.getContent().get(0).getTaxiId());
        assertEquals(date, result.getContent().get(0).getDate());
    }
}
