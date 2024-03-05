package com.fleetmanagementapi.models;

import java.time.LocalDate;

public class EmailDTO { //Le puse DTO que significa Data Transfer Object por si chocaba con otras clases, representa un objeto.
    private String destinatario;
    private  String asunto;
    private  String mensaje;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    private LocalDate date;

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    private String plate;

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}

// 'EmailDTO' esta clase se utiliza para encapsular la informacion relacionada con el envio de correos electronicos incluyendo el destinatario, asunto, mensaje, fecha y placa.
// Es común en aplicaciones Spring utilizar clases DTO para transferir datos entre capas de la aplicación, como los controladores y los servicios.