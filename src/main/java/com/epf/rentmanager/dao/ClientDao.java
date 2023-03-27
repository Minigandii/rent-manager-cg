package com.epf.rentmanager.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

@Repository

public class ClientDao {

	private ClientDao() {}
	
	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
	
	public long create(Client client) throws DaoException {
		return 0;
	}
	
	public long delete(Client client) throws DaoException {
		return 0;
	}

	public Client findById(long id) throws DaoException {

		Client client = new Client();
		try{
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement stat = connection.prepareStatement(FIND_CLIENT_QUERY);
			stat.setLong(1,id);
			ResultSet rs = stat.executeQuery();
			while(rs.next()){
				String nom =  rs.getString("nom");
				String prenom = rs.getString("prenom");
				String email = rs.getString("email");
				LocalDate naissance = rs.getDate("naissance").toLocalDate();

				client = new Client(id,nom,prenom,email,naissance);
			}
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
		return client;
	}

	public List<Client> findAll() throws DaoException {

		List<Client> clients = new ArrayList<Client>();
		try{
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(FIND_CLIENTS_QUERY);

			while(rs.next()){
				int id = rs.getInt("id");
				String nom =  rs.getString("nom");
				String prenom = rs.getString("prenom");
				String email = rs.getString("email");
				LocalDate naissance = rs.getDate("naissance").toLocalDate();

				clients.add(new Client(id,nom,prenom,email,naissance));
			}
		}catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
		return clients;
	}
}
