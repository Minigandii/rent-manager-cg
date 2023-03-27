package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.stereotype.Repository;

@Repository

public class ReservationDao {

	private ClientDao clientDao;
	private VehicleDao vehicleDao;

	private ReservationDao(ClientDao clientDao, VehicleDao vehicleDao) {
		this.vehicleDao= vehicleDao;
		this.clientDao = clientDao;
	}
	
	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
		
	public long create(Reservation reservation) throws DaoException {
		return 0;
	}
	
	public long delete(Reservation reservation) throws DaoException {
		return 0;
	}
	
	public List<Reservation> findResaByClientId(long clientId) throws DaoException {
		return new ArrayList<Reservation>();
	}
	
	public List<Reservation> findResaByVehicleId(long vehicleId) throws DaoException {
		return new ArrayList<Reservation>();
	}

	public List<Reservation> findAll() throws DaoException {

		List<Reservation> reservations = new ArrayList<Reservation>();
		try{
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(FIND_RESERVATIONS_QUERY);

			while(rs.next()){
				int id = rs.getInt("id");
				int client_id = rs.getInt("client_id");
				Client client = clientDao.findById(client_id);
				int vehicle_id = rs.getInt("vehicle_id");
				Vehicle vehicle = vehicleDao.findById(vehicle_id);
				LocalDate debut = rs.getDate("debut").toLocalDate();
				LocalDate fin = rs.getDate("fin").toLocalDate();

				reservations.add(new Reservation(id,client,vehicle,debut,fin));
			}
		}catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
		return reservations;
	}
}
