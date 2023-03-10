package com.epf.rentmanager.model;

public class Vehicle {
    private long id;
    private int nb_places;
    private String constructeur;
    private String modele;

    public Vehicle(long id, int nb_places, String constructeur, String modele) {
        this.id = id;
        this.nb_places = nb_places;
        this.constructeur = constructeur;
        this.modele = modele;
    }

    public Vehicle() {
    }

    public long getId() {
        return id;
    }

    public int getNbPlaces() {
        return nb_places;
    }

    public String getConstructeur() {
        return constructeur;
    }

    public String getModele() {
        return modele;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nb_places = nbPlaces;
    }

    public void setConstructeur(String constructeur) {
        this.constructeur = constructeur;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id + '\n'+
                " nb_places=" + nb_places + '\n'+
                " constructeur='" + constructeur + '\'' + '\n'+
                " modele='" + modele + '\'' +
                '}';
    }
}
