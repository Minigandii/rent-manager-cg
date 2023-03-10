package com.epf.rentmanager.model;

import java.time.LocalDate;

public class Reservation {
    private long id;
    private Client client;
    private Vehicle vehicle;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    public Reservation(long id, Client client, Vehicle vehicle, LocalDate dateDebut, LocalDate dateFin) {
        this.id = id;
        this.client = client;
        this.vehicle = vehicle;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Reservation() {
    }

    public long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", client=" + client +
                ", vehicle=" + vehicle +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                '}';
    }
}
