package com.iesVda.classDaos;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class RegionDao implements DaoInterface<Region> {

	private List<Region> region = new ArrayList<>();

	private String dbUser;
	private String dbPasswd;
	private String dbURL;
	private Connection conn;
	private String sql;
	private int numRowsAffected;
	private Statement stmt;
	private ResultSet rs;

	public RegionDao() {
		// initialize driver class
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println("Fail to initialize Oracle JDBC driver in Regiondao: " + e.toString() + "<P>");
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
			System.out.println("Connection failed in RegionDao: " + e.toString() + "<P>");
		}

		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/*
	 * @Override public Region get(Region e) { int rgId = e.getRegionId(); sql =
	 * "SELECT region_name FROM regions WHERE region_id = " + rgId; try { this.rs =
	 * stmt.executeQuery(sql); String name = rs.getString("region_name");
	 * 
	 * 
	 * System.out.println("name: " + name);
	 * 
	 * e.setRegionName(name);
	 * 
	 * } catch (SQLException e1) { e1.printStackTrace(); } closeConnection(); return
	 * e; }
	 */

	@Override
	public Region get(Region region) {
		int regionId = region.getRegionId();
		sql = "SELECT * FROM regions WHERE region_id = '" + regionId + "'";
		try {
			this.rs = stmt.executeQuery(sql);
			if (rs.next()) { // Mover el cursor al primer registro
				String name = rs.getString("region_name");
				region.setRegionName(name);
			} else {
				// Manejar el caso en que no se encuentre ningún resultado para el ID dado
				System.out.println("No se encontró ninguna región con el ID proporcionado.");
			}
		} catch (SQLException e1) {
			System.out.println("SQL exception in RegionDao");
			e1.printStackTrace();
		} finally {
			closeConnection(); // Asegúrate de cerrar la conexión
		}

		return region;
	}

	@Override
	public List<Region> getAll() {
		sql = "Select * FROM regions";
		try {
			rs = stmt.executeQuery(sql);
			System.out.println("Result: " + rs);
		} catch (SQLException e1) {
			System.out.println("SQL exception in RegionDao");
			e1.printStackTrace();
		}

		return region;
	}

	@Override
	public void save(Region e) {
		region.add(e);
	}

	@Override
	public void update(Region e, String[] params) {

	}

	@Override
	public void delete(Region e) {
		region.remove(e);
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
			System.out.println("Close connection error in RegionDao");
			e.printStackTrace();
		}
	}

}
