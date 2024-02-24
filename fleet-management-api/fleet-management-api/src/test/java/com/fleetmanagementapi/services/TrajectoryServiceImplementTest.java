package com.fleetmanagementapi.services;

import com.fleetmanagementapi.dao.TrajectoryDao;
import com.fleetmanagementapi.models.Trajectorie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class TrajectoryServiceImplementTest {

    @Mock
    private TrajectoryDao trajectoryDao;

    @InjectMocks
    private TrajectoryServiceImplement trajectoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindByTaxiIdAndDateGreaterThanEqual() {
        // Mock data
        Trajectorie trajectory = new Trajectorie();
        trajectory.setIdTrajectorie(1);
        trajectory.setTaxiId(123);
        trajectory.setDate(LocalDateTime.now());

        int taxiId = 123;
        LocalDateTime date = LocalDateTime.now();
        Pageable pageable = Pageable.unpaged();

        // Mock behavior of trajectoryDao.findByTaxiIdAndDateGreaterThanEqual()
        when(trajectoryDao.findByTaxiIdAndDateGreaterThanEqual(taxiId, date, pageable))
                .thenReturn(new PageImpl<>(Collections.singletonList(trajectory)));

        // Call the method to be tested
        Page<Trajectorie> result = trajectoryService.findByTaxiIdAndDateGreaterThanEqual(taxiId, date, pageable);

        // Assertions
        assertEquals(1, result.getTotalElements());
        assertEquals(123, result.getContent().get(0).getTaxiId());
    }
}
