package com.fleetmanagementapi.controllers;

import com.fleetmanagementapi.models.Taxi;
import com.fleetmanagementapi.services.TaxiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TaxiController {
    @Autowired
    private TaxiService taxiService;

    @GetMapping(value = "/taxis")
    public ResponseEntity<Object> get(@PageableDefault(size = 10) Pageable pageable){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Page<Taxi> list = taxiService.findAll(pageable);
            return new ResponseEntity<Object>(list, HttpStatus.OK);
        }
        catch (Exception e){
            map.put("message", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}