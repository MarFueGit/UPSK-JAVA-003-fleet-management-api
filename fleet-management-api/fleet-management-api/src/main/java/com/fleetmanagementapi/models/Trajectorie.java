package com.fleetmanagementapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "trajectories")
public class Trajectorie {
    @Id
    @Column(name="idtrajectorie")
    private int idTrajectorie; //IdTrajectorie
    @Column(name = "taxi_id")
    private int taxiId;
    @Column(name = "date")
    private LocalDateTime date;
    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "longitude")
    private Double longitude;

    private String plate;

    public int getIdTrajectorie() {
        return idTrajectorie;
    }

    public void setIdTrajectorie(int id) {
        this.idTrajectorie = id;
    }

    public int getTaxiId() {
        return taxiId;
    }

    public void setTaxiId(int taxiId) {
        this.taxiId= taxiId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }
}
