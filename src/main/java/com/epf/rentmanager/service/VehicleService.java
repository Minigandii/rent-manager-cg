package com.epf.rentmanager.service;

import java.util.List;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.VehicleDao;
import org.springframework.stereotype.Service;

@Service

public class VehicleService {

	private VehicleDao vehicleDao;
	public static VehicleService instance;
	
	private VehicleService(VehicleDao vehicleDao) {
		this.vehicleDao = vehicleDao;
	}

	public long create(Vehicle vehicle) throws ServiceException {
		// TODO: créer un véhicule

		return 0;
	}

	public Vehicle findById(long id) throws ServiceException {
		try{
			return vehicleDao.findById(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public List<Vehicle> findAll() throws ServiceException {
		try{
			return vehicleDao.findAll();
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
