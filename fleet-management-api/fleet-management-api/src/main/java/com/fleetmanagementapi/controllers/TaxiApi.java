package com.fleetmanagementapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class TaxiApi {
    @GetMapping("/taxis")
    public String holaMundo(){
        return "El api esta funcionando";
    }
}
