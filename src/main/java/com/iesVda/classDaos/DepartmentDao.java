package com.iesVda.classDaos;

import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao implements DaoInterface<Department> {

	private List<Department> departments = new ArrayList<>();

	private String dbUser;
	private String dbPasswd;
	private String dbURL;
	private Connection conn;
	private String sql;
	private int numRowsAffected;
	private Statement stmt;
	private ResultSet rs;

	public DepartmentDao() {
		// initialize driver class
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println("Fail to initialize Oracle JDBC driver in DepartmentDao: " + e.toString() + "<P>");
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
			System.out.println("Connection failed in DepartmentDao: " + e.toString() + "<P>");
		}

		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			System.out.println("SQL exception in connection in DepartmentDao: ");
			e.printStackTrace();
		}
	}

	@Override
	public Department get(Department department) {
		int departmentId = department.getDepartmentId();
		sql = "SELECT * FROM departments WHERE department_id = " + departmentId;
		
		System.out.println("DEPARTMENT ID = " + departmentId);
		
		try {
			this.rs = stmt.executeQuery(sql);
			if (rs.next()) { // Mover el cursor al primer registro
				department.setDepartmentName(rs.getString("department_name"));
				department.setManagerId(Integer.parseInt(rs.getString("manager_id")));
				department.setLocationId(Integer.parseInt(rs.getString("location_id")));
			} else {
				// Manejar el caso en que no se encuentre ningún resultado para el ID dado
				System.out.println("No se encontró ningún departamento con el ID proporcionado.");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.out.println("ENTRA AQUÏ");
		} catch (NumberFormatException nfe) {
			System.out.println("Ha ocurrido un error de parseo a entero en DepartmentDao: " + nfe.getMessage());
			nfe.printStackTrace();
		} finally {
			closeConnection();
		}
		
		System.out.println("locationId en DepartmentDao: " + department.getLocationId());

		Location location = new Location(department.getLocationId());
		LocationDao locationDao = new LocationDao();
		department.setLocation(locationDao.get(location));
		Employee managerOfDepartment = new Employee(department.getManagerId());
		EmployeeDao employeeDao = new EmployeeDao();
		department.setManager(employeeDao.getManager(managerOfDepartment));
		return department;
	}
	
	public Department getDepartmentOfJobHistory(Department department) {
		int departmentId = department.getDepartmentId();
		sql = "SELECT * FROM departments WHERE department_id = " + departmentId;
		
		try {
			this.rs = stmt.executeQuery(sql);
			if (rs.next()) { // Mover el cursor al primer registro
				department.setDepartmentName(rs.getString("department_name"));
				department.setManagerId(Integer.parseInt(rs.getString("manager_id")));
				department.setLocationId(Integer.parseInt(rs.getString("location_id")));
			} else {
				// Manejar el caso en que no se encuentre ningún resultado para el ID dado
				System.out.println("No se encontró ningún departamento con el ID proporcionado.");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (NumberFormatException nfe) {
			System.out.println("Ha ocurrido un error de parseo a entero en DepartmentDao: " + nfe.getMessage());
			nfe.printStackTrace();
		} finally {
			closeConnection();
		}
		
		System.out.println("locationId en DepartmentDao: " + department.getLocationId());
		return department;
	}

	@Override
	public List<Department> getAll() {
		return departments;
	}

	@Override
	public void save(Department e) {
		departments.add(e);
	}

	@Override
	public void update(Department e, String[] params) {

	}

	@Override
	public void delete(Department e) {
		departments.remove(e);
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
			System.out.println("Close connection error in DepartmentDao");
			e.printStackTrace();
		}
	}

}
