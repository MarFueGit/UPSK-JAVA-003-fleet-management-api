package com.fleetmanagementapi.controllers;

import com.fleetmanagementapi.controllers.TrajectoryController;
import com.fleetmanagementapi.models.EmailDTO;
import com.fleetmanagementapi.models.Trajectorie;
import com.fleetmanagementapi.services.IEmailService;
import com.fleetmanagementapi.services.IExcelService;
import com.fleetmanagementapi.services.TrajectoryService;
import org.apache.poi.ss.usermodel.Workbook;
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

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class TrajectoryControllerTest {

    @Mock
    private IExcelService excelService;

    @Mock
    private IEmailService emailService;

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
        assertEquals(date, result.getContent().get(0).getDate());
    }

    @Test
    public void testGetLastTrajectories_Success() {
        int taxiId = 1;
        LocalDateTime date = LocalDateTime.of(2022, 1, 1, 0, 0);
        Pageable pageable = Pageable.unpaged();

        Trajectorie trajectory = new Trajectorie();
        trajectory.setIdTrajectorie(1);
        trajectory.setDate(date);

        when(trajectoryService.findLatestTrajectories(anyInt(), anyInt())).thenReturn(new PageImpl<>(Collections.singletonList(trajectory)));

        // Calling the controller method
        ResponseEntity<Object> response = trajectoryController.getLastTrajectories(1, 10);

        // Verifying the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetTaxiLocations() throws Exception {
        // Mocking input data
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setPlate("PlateNumber");
        emailDTO.setDate(LocalDate.now());

        // Mocking data from trajectoryService
        List<Object> mockData = new ArrayList<>();
        mockData.add("MockedData1");
        mockData.add("MockedData2");
        when(trajectoryService.getTrajectoriesByPlateAndDate(eq("PlateNumber"), any())).thenReturn(mockData);

        // Mocking excel file creation
        Workbook mockWorkbook = mock(Workbook.class);
        when(excelService.createExcelFile(mockData)).thenReturn(mockWorkbook);

        // Mocking email sending
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        doNothing().when(emailService).sendMail(eq(emailDTO), any(byte[].class));

        // Calling the controller method
        ResponseEntity<Object> responseEntity = trajectoryController.getTaxiLocations(emailDTO);

        // Verifying the response status
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Verifying the response data
        assertEquals(mockData, responseEntity.getBody());

        // Verifying interactions with mocks
        verify(trajectoryService, times(1)).getTrajectoriesByPlateAndDate(eq("PlateNumber"), any());
        verify(excelService, times(1)).createExcelFile(mockData);
        verify(emailService, times(1)).sendMail(eq(emailDTO), any(byte[].class));
    }
}


