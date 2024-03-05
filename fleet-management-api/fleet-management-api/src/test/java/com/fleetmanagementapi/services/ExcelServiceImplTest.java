package com.fleetmanagementapi.services;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExcelServiceImplTest {

    @Test
    public void testCreateExcelFile() throws IOException {
        // Prepare test data
        List<Object> testData = Arrays.asList(
                new Object[]{"1", "ABC123", 1.0, 2.0, LocalDateTime.now()},
                new Object[]{"2", "DEF456", 3.0, 4.0, LocalDateTime.now()}
        );

        // Call the method under test
        ExcelServiceImpl excelService = new ExcelServiceImpl();
        Workbook workbook = excelService.createExcelFile(testData);

        // Validate the generated Excel file
        assertNotNull(workbook);

        // Validate the content of the generated Excel file
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        byte[] excelBytes = outputStream.toByteArray();
        assertTrue(excelBytes.length > 0); // verificamos que el excel no este vacio
    }
}