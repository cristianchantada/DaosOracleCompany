package com.iesVda.classDaos;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.sql.Connection;

public class LocationDao implements DaoInterface<Location> {

	private List<Location> location = new ArrayList<>();

	private String dbUser;
	private String dbPasswd;
	private String dbURL;
	private Connection conn;
	private String sql;
	private int numRowsAffected;
	private Statement stmt;
	private ResultSet rs;

	public LocationDao() {
		// initialize driver class
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println("Fail to initialize Oracle JDBC driver in LocationDao: " + e.toString() + "<P>");
		}

		dbUser = "hr";
		dbPasswd = "oracle";
		dbURL = "jdbc:oracle:thin:@localhost:1521/freepdb1";

		// connect
		conn = null;
		try {
			conn = DriverManager.getConnection(dbURL, dbUser, dbPasswd);
			System.out.println(" Connection status: " + conn + "<P>");
		} catch (Exception e) {
			System.out.println("Connection failed in LcationDao: " + e.toString() + "<P>");
		}

		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			System.out.println("SQl exception in LocationDao");
			e.printStackTrace();
		}
	}

	@Override
	public Location get(Location location) {
		int locationId = location.getLocationId();
		System.out.println("locationId" + locationId);
		sql = "SELECT * FROM locations WHERE location_id = " + locationId;

		try {
			this.rs = stmt.executeQuery(sql);
			if (rs.next()) { // Mover el cursor al primer registro

				location.setStreetAddress(rs.getString("street_address"));
				location.setPostalCode(Long.parseLong(rs.getString("postal_code")));
				location.setCity(rs.getString("city"));
				location.setStateProvice(rs.getString("state_province"));
				location.setCountryId(rs.getString("country_id"));

			} else {
				// Manejar el caso en que no se encuentre ningún resultado para el ID dado
				System.out.println("No se encontró ninguna localización con el ID proporcionado.");
			}
		} catch (SQLException e1) {
			System.out.println("SQl exception in LocationDao");
			e1.printStackTrace();
		} catch (NumberFormatException nfe) {
			System.out.println("Ha ocurrido un error de parseo a entero in LocationDao: " + nfe.getMessage());
			nfe.printStackTrace();
		} finally {
			closeConnection();
		}

		Country country = new Country(location.getCountryId());
		CountryDao countryDao = new CountryDao();
		location.setCountry(countryDao.get(country));
		return location;
	}

	@Override
	public List<Location> getAll() {
		return location;
	}

	@Override
	public void save(Location e) {
		location.add(e);
	}

	@Override
	public void update(Location e, String[] params) {

	}

	@Override
	public void delete(Location e) {
		location.remove(e);
	}

	public void closeConnection() {
		try {
			rs.close();
			stmt.close();
			// commit only when updating the DB
			// conn.commit();
			// disconnect
			conn.close();
		} catch (SQLException e) {
			System.out.println("Close connection error in LocationDao");
			e.printStackTrace();
		}
	}

}
