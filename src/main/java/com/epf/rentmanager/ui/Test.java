package com.epf.rentmanager.ui;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

public class Test {
    public static void main(String args[]){
        try {
            System.out.println(ClientService.getInstance().findAll());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

        try {
            System.out.println(ClientService.getInstance().findById(3));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

        try {
            System.out.println(VehicleService.getInstance().findAll());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

        try {
            System.out.println(VehicleService.getInstance().findById(2));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

        try {
            System.out.println(ReservationService.getInstance().findAll());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }


}
