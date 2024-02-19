package com.fleetmanagementapi.controllers;

import com.fleetmanagementapi.models.Trajectorie;
import com.fleetmanagementapi.services.TrajectoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Tag(name = "Trajectories", description = "Colección de endpoints para obtener ubicaciones de los taxis")

public class TrajectoryController {
    @Autowired
    private TrajectoryService trajectoryService;

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
}