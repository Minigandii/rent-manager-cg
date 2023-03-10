package com.epf.rentmanager.model;

import java.time.LocalDate;

public class Client {
    private long id;
    private String nom;
    private String prenom;
    private String email;
    private LocalDate naissance;

    public Client(long id, String nom, String prenom, String email, LocalDate naissance) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.naissance = naissance;
    }
    public Client() {

    }

    // Setter
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setNaissance(LocalDate naissance) {
        this.naissance = naissance;
    }

    // getter
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public String getEmail() {
        return email;
    }
    public LocalDate getNaissance() {
        return naissance;
    }

    @Override
    public String toString() {
        return "Client{" +
                " id=" + id + '\n'+
                " nom='" + nom + '\'' + '\n'+
                " prenom='" + prenom + '\'' + '\n'+
                " email='" + email + '\'' + '\n'+
                " naissance=" + naissance +
                '}'+'\n';
    }
}
