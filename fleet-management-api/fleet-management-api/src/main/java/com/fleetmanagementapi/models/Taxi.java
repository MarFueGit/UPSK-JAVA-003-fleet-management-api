package com.fleetmanagementapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "taxis")
public class Taxi{
    @Id
    @Column(name = "idtaxi")
    private int idTaxi; // IdTaxi

    @Column(name = "plate")
    private String plate;

    public int getIdTaxi() {
        return this.idTaxi;
    } //son métodos de acceso (getters) para los atributos id y plate, respectivamente.

    public void setIdTaxi(int id) { // son métodos de modificación (setters) para los atributos id y plate, respectivamente.
        this.idTaxi = id;
    }

    public String getPlate() {
        return plate;
    } //devuelve el valor actual del atributo plate de la instancia de Taxi en la que se llama al método.

    public void setPlate(String plate) {
        this.plate = plate;
    }//Este método se utiliza para establecer el valor del atributo plate de la clase Taxi con el valor proporcionado.
}
