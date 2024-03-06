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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TrajectoryServiceImplementTest {

    // preparamos nuestros servicios para mockealos
    @Mock
    private TrajectoryDao trajectoryDao;

    @InjectMocks
    private TrajectoryServiceImplement trajectoryService;

    //Iniciamos los mocks
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindByTaxiIdAndDateGreaterThanEqual() {
        // Creamos una trayectoria
        Trajectorie trajectory = new Trajectorie();
        trajectory.setIdTrajectorie(1);
        trajectory.setDate(LocalDateTime.now());
        int taxiId = 123;
        LocalDateTime date = LocalDateTime.now();
        Pageable pageable = Pageable.unpaged();

        // Mockeamos la llamada de findByTaxiIdAndDateGreaterThanEqual con mockito
        when(trajectoryDao.findByTaxiIdAndDateGreaterThanEqual(taxiId, date, pageable))
                .thenReturn(new PageImpl<>(Collections.singletonList(trajectory)));

        // Invocamos a findByTaxiIdAndDateGreaterThanEqual
        Page<Trajectorie> result = trajectoryService.findByTaxiIdAndDateGreaterThanEqual(taxiId, date, pageable);

        // verificamos que si nos devuelva la data mockeada
        assertEquals(1, result.getTotalElements());
    }

    @Test
    public void testFindLatestTrajectories() {
        // Mocking data
        List<Trajectorie> mockedTrajectories = new ArrayList<>();
        // Add some mock data to the list

        // Mocking the behavior of trajectoryDao
        when(trajectoryDao.findLatestTrajectories(any(Pageable.class))).thenReturn(mockedTrajectories);

        // Calling the service method
        int initPage = 0;
        int pageSize = 10;
        Page<Trajectorie> resultPage = trajectoryService.findLatestTrajectories(initPage, pageSize);

        // Verifying the behavior
        assertEquals(mockedTrajectories.size(), resultPage.getContent().size());
        // You can add more assertions based on your requirements

        // Verifying that the method was called with the correct arguments
        verify(trajectoryDao, times(1)).findLatestTrajectories(PageRequest.of(initPage, pageSize));
    }

    @Test
    void testGetTrajectoriesByPlateAndDate() {
        // 1. Set up the necessary test environment
        TrajectoryDao trajectoryDao = mock(TrajectoryDao.class);
        TrajectoryServiceImplement trajectoryService = new TrajectoryServiceImplement(trajectoryDao);

        // 2. Define input parameters
        String plate = "ABC123";
        LocalDateTime date = LocalDateTime.of(2022, 1, 1, 0, 0);

        // 3. Mock the dependencies
        List<Object> mockData = Arrays.asList("Trajectory1", "Trajectory2");
        when(trajectoryDao.getTrajectoriesByPlateAndDate(plate, date)).thenReturn(mockData);

        // 4. Call the method under test
        List<Object> result = trajectoryService.getTrajectoriesByPlateAndDate(plate, date);

        // 5. Verify the result
        assertEquals(mockData, result);

        // 6. Verify interactions with mocks
        verify(trajectoryDao, times(1)).getTrajectoriesByPlateAndDate(plate, date);
    }

}
