package com.fleetmanagementapi.controllers;

import com.fleetmanagementapi.models.Taxi;
import com.fleetmanagementapi.services.TaxiService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Object> get(){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<Taxi> list = taxiService.findAll();
            return new ResponseEntity<Object>(list, HttpStatus.OK);
        }
        catch (Exception e){
            map.put("message", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
