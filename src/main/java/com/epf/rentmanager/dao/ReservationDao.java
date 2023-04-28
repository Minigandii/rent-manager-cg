package com.epf.rentmanager.dao;

import java.sql.*;
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
	private static final String FIND_RESERVATIONS_BY_ID_QUERY = "SELECT id,vehicle_id, client_id, debut, fin FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";

	public long create(Reservation reservation) throws DaoException {
		long id=0;
		long id_client = reservation.getClient().getId();
		long id_vehicle=reservation.getVehicle().getId();
		try {
			Connection connection= ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(CREATE_RESERVATION_QUERY, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1,id_client);
			ps.setLong(2,id_vehicle);
			ps.setDate(3, Date.valueOf(reservation.getDateDebut()));
			ps.setDate(4, Date.valueOf(reservation.getDateFin()));
			ps.execute();
			ResultSet resultSet = ps.getGeneratedKeys();
			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}
			ps.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return id;
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

	public Reservation findById(long id) throws DaoException {

		Reservation reservation = new Reservation();
		try{
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement stat = connection.prepareStatement(FIND_RESERVATIONS_BY_ID_QUERY);
			stat.setLong(1,id);
			ResultSet rs = stat.executeQuery();
			while(rs.next()){
				int client_id = rs.getInt("client_id");
				Client client = clientDao.findById(client_id);
				int vehicle_id = rs.getInt("vehicle_id");
				Vehicle vehicle = vehicleDao.findById(vehicle_id);
				LocalDate debut = rs.getDate("debut").toLocalDate();
				LocalDate fin = rs.getDate("fin").toLocalDate();

				reservation = new Reservation(id,client,vehicle,debut,fin);
			}
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
		return reservation;
	}

}
