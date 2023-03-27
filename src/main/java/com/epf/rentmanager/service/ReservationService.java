package com.epf.rentmanager.service;

import java.util.List;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.model.Vehicle;
import org.springframework.stereotype.Service;

@Service


public class ReservationService {

    private ReservationDao reservationDao;

    private ReservationService(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    public long create(Reservation reservation) throws ServiceException {
        // TODO: créer une Reservation

        return 0;
    }


    public List<Reservation> findAll() throws ServiceException {
        try{
            return reservationDao.findAll();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public int count() throws ServiceException {
        try {
            return findAll().size();
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
