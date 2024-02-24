package com.fleetmanagementapi.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TaxiTest {
    @Test
public void createTaxiSuccess(){
Taxi taxi = new Taxi();
taxi.setIdTaxi(20);
taxi.setPlate("1254a5");
        Assertions.assertEquals(20, taxi.getIdTaxi());
        Assertions.assertEquals("1254a5", taxi.getPlate());
}
}
