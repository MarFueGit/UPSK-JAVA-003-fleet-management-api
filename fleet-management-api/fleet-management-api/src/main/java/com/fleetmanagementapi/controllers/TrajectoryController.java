package com.fleetmanagementapi.controllers;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.fleetmanagementapi.models.EmailDTO;
import com.fleetmanagementapi.models.Trajectorie;
import com.fleetmanagementapi.services.IEmailService;
import com.fleetmanagementapi.services.TrajectoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/api")
@Tag(name = "Trajectories", description = "Colección de endpoints para obtener ubicaciones de los taxis")

public class TrajectoryController {
    @Autowired
    private TrajectoryService trajectoryService;
    @Autowired
    IEmailService emailService;

    @GetMapping(value = "/trajectories/{id}")
    @Operation(summary = "Obtiene todas las ubicaciones por id y fecha")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))
            }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })})

    public ResponseEntity<Object> get(
            @PathVariable int id,
            @RequestParam(value = "date", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            Pageable pageable){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            LocalDateTime dateSearch = date.atStartOfDay();
            Page<Trajectorie> list = trajectoryService.findByTaxiIdAndDateGreaterThanEqual(id, dateSearch, pageable);
            return new ResponseEntity<Object>(list, HttpStatus.OK);
        }
        catch (Exception e){
            map.put("message", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/trajectorie/latest")
    @Operation(summary = "Obtiene la última ubicación de cada taxi")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))
            }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })})
    public ResponseEntity<Object> getLastTrajectories(int page, int pageSize){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Page<Trajectorie> list = trajectoryService.findLatestTrajectories(page, pageSize);
            return new ResponseEntity<Object>(list, HttpStatus.OK);
        }
        catch (Exception e){
            map.put("message", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //locations/email
    @PostMapping("/locations/email")
    public ResponseEntity<Object> getTaxiLocations(@RequestBody (required = false) EmailDTO email ) throws MessagingException, IOException {
        
        List<Object> data = trajectoryService.getTrajectoriesByPlateAndDate(email.getPlate(), email.getDate().atStartOfDay());
        // Create a new workbook
        Workbook workbook = new XSSFWorkbook();

        // Create a new sheet
        Sheet sheet = workbook.createSheet("Taxi Data");
        // Create headers
        Row headerRow = sheet.createRow(0);
        String[] headers = {"ID TAXI", "PLATE", "LATITUDE", "LONGITUDE", "DATE"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Populate data
        for (int rowIndex = 0; rowIndex < data.size(); rowIndex++) {
            Row row = sheet.createRow(rowIndex + 1);
            Object[] rowData = (Object[]) data.get(rowIndex);
            for (int cellIndex = 0; cellIndex < rowData.length; cellIndex++) {
                Cell cell = row.createCell(cellIndex);
                Object value = rowData[cellIndex];
                if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                } else if (value instanceof String) {
                    cell.setCellValue((String) value);
                } else if (value instanceof LocalDateTime) {
                    LocalDateTime localDateTime = (LocalDateTime) value;
                    cell.setCellValue(localDateTime.toString());
                } else {
                    // Handle other data types or unsupported types
                    cell.setCellValue(value != null ? value.toString() : "");
                }
            }
        }

        // Write workbook to byte array
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        byte[] bytes = outputStream.toByteArray();

        // Envio de correo
        emailService.sendMail(email, bytes);
        return new ResponseEntity<Object>(data, HttpStatus.OK);
    }
}