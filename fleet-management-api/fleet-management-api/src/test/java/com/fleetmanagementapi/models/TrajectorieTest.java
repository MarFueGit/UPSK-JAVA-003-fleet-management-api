package com.fleetmanagementapi.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TrajectorieTest {
@Test
public void createTrajectorieSuccess(){
   Trajectorie trajectorie = new Trajectorie();
  trajectorie.setIdTrajectorie(5);
   trajectorie.setTaxiId(8);
 LocalDateTime now = LocalDateTime.now();
   trajectorie.setDate(now);
   trajectorie.setLatitude(15.20);
   trajectorie.setLongitude(18.20);
   trajectorie.setPlate("ABC");
    Assertions.assertEquals(5, trajectorie.getIdTrajectorie());
    Assertions.assertEquals(8,trajectorie.getTaxiId());
    Assertions.assertEquals(now, trajectorie.getDate());
    Assertions.assertEquals(15.20,trajectorie.getLatitude());
    Assertions.assertEquals(18.20,trajectorie.getLongitude());
    Assertions.assertEquals("ABC", trajectorie.getPlate());

}
}