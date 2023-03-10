package com.epf.rentmanager.main;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;

import java.time.LocalDate;

public class main {
    public static void main(String args[]){
        Client client1 = new Client();
        client1.setEmail("cg@gmail.com");
        client1.setNaissance(LocalDate.now());
        client1.setPrenom("Clem");
        client1.setNom("Gandi");

        System.out.println(client1);
    }
}
