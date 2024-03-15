package com.iesVda.classDaos;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

public class EmployeeDao implements DaoInterface<Employee> {

	private List<Employee> employees = new ArrayList<>();

	private String dbUser;
	private String dbPasswd;
	private String dbURL;
	private Connection conn;
	private String sql;
	private int numRowsAffected;
	private Statement stmt;
	private ResultSet rs;

	public EmployeeDao() {
		// initialize driver class
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println("Fail to initialize Oracle JDBC driver in EmployeeDao: " + e.toString() + "<P>");
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
			System.out.println("Connection failed in EmployeeDao: " + e.toString() + "<P>");
		}

		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Employee get(Employee employee) {

		int eId = employee.getEmployeeId();

		Employee e = new Employee(eId);
		sql = "SELECT employee_id, first_name, last_name, email, phone_number" 
		+ ", hire_date, job_id, salary, commission_pct,"
		+ "manager_id, department_id"
		+ " FROM employees WHERE employee_id = " + eId;
		
		try {
			this.rs = stmt.executeQuery(sql);
			if (rs.next()) { // Mover el cursor al primer registro

				e.setEmployeeId(rs.getInt("employee_id"));	
				
				System.out.println("e.getEmployeeId en EMPLOYEE DAO" + e.getEmployeeId());
				
				e.setFirstName(rs.getString("first_name"));
				e.setLastName(rs.getString("last_name"));
				e.setEmail(rs.getString("email"));
				e.setPhoneNumber(rs.getString("phone_number"));
				e.setJobId(rs.getString("job_id"));
				e.setSalary(Double.parseDouble(rs.getString("salary")));
				e.setCommissionPct(Double.parseDouble(rs.getString("commission_pct")));
				
				System.out.println("e employeeId = " + e.getEmployeeId());
				
				if (!(rs.getString("manager_id") == null)) {
					e.setManagerId(Integer.parseInt(rs.getString("manager_id")));
				} else {
					e.setManagerId(0);
				}

				if (!((rs.getString("department_id")) == null)) {
					e.setDepartmentId(Integer.parseInt(rs.getString("department_id")));
				} else {
					e.setDepartmentId(0);
				}

			try {
					// Definir el formato de la cadena de fecha
					DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

					// Parsear la cadena a LocalDate
					LocalDate localDate = LocalDate.parse(rs.getString("hire_date"), format);
					e.setHireDate(localDate);
					System.out.println("Fecha parseada: " + localDate);
				} catch (DateTimeParseException dTe) {
					System.out.println("Error al parsear la fecha en EmployeeDao: " + dTe.getMessage());
				}
			} else {
				// Manejar el caso en que no se encuentre ningún resultado para el ID dado
				System.out.println("No se encontró ningún empleado con el ID proporcionado.");
				e.setEmployeeId(0);;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.out.println("ENTRA AQUÏ employeeDao");
		} catch (NumberFormatException nfe) {
			System.out.println("Ha ocurrido un error de parseo a entero en EmployeeDao: " + nfe.getMessage());
			nfe.printStackTrace();
		} finally {
			closeConnection();
		}

		DepartmentDao departmentDao = new DepartmentDao();
		Department department = new Department();
		department.setDepartmentId(e.getDepartmentId());
		e.setDepartment(departmentDao.get(department));

		JobDao jobDao = new JobDao();
		Job job = new Job(e.getJobId());
		job = jobDao.get(job);
		e.setJob(job);

		JobHistory jobHistory = new JobHistory(e.getEmployeeId());
		JobHistoryDao jobHistoryDao = new JobHistoryDao();
		jobHistory.setEmployeeId(eId);
		List<JobHistory> jobsList = jobHistoryDao.getJobHistoryList(jobHistory);
		e.setJobHistory(jobsList);
		
		
		System.out.println("e employEEEE iD = " + e.getEmployeeId());
		return e;
	}

	public Employee getManager(Employee e) {
		int eId = e.getEmployeeId();
		sql = "SELECT first_name, last_name, email, phone_number" + ", hire_date, job_id, salary, commission_pct,"
				+ "manager_id, department_id" + " FROM employees WHERE employee_id = " + eId;
		try {
			this.rs = stmt.executeQuery(sql);
			if (rs.next()) { // Mover el cursor al primer registro

				e.setFirstName(rs.getString("first_name"));
				e.setLastName(rs.getString("last_name"));
				e.setEmail(rs.getString("email"));
				e.setPhoneNumber(rs.getString("phone_number"));
				e.setJobId(rs.getString("job_id"));
				e.setSalary(Double.parseDouble(rs.getString("salary")));
				e.setCommissionPct(Double.parseDouble(rs.getString("commission_pct")));

				if (!(rs.getString("manager_id") == null)) {
					e.setManagerId(Integer.parseInt(rs.getString("manager_id")));
				} else {
					e.setManagerId(0);
				}

				if (!((rs.getString("department_id")) == null)) {
					e.setDepartmentId(Integer.parseInt(rs.getString("department_id")));
				} else {
					e.setDepartmentId(0);
				}

				// e.setDepartment();
				// e.setManager();
				// e.setWorking();

				// Lógica de obtención de fecha y formateo a LocalDate
				try {
					// Definir el formato de la cadena de fecha
					DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

					// Parsear la cadena a LocalDate
					LocalDate localDate = LocalDate.parse(rs.getString("hire_date"), format);
					e.setHireDate(localDate);
					System.out.println("Fecha parseada: " + localDate);
				} catch (DateTimeParseException dTe) {
					System.out.println("Error al parsear la fecha en EmployeeDao: " + dTe.getMessage());
				}
			} else {
				// Manejar el caso en que no se encuentre ningún resultado para el ID dado
				System.out.println("No se encontró ningún empleado con el ID proporcionado.");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (NumberFormatException nfe) {
			System.out.println("Ha ocurrido un error de parseo a entero en EmployeeDao: " + nfe.getMessage());
			nfe.printStackTrace();
		} finally {
			closeConnection();
		}

		return e;
	}

	@Override
	public List<Employee> getAll() {
		return employees;
	}

	@Override
	public void save(Employee e) {
		employees.add(e);
	}

	@Override
	public void update(Employee e, String[] params) {

	}

	@Override
	public void delete(Employee e) {
		employees.remove(e);
	}

	public static void main(String[] args) throws SQLException {

		// initialize driver class
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println("Fail to initialize Oracle JDBC driver" + "in employeeDao: " + e.toString() + "<P>");
		}
		String dbUser = "hr";
		String dbPasswd = "oracle";
		String dbURL = "jdbc:oracle:thin:@localhost:1521/freepdb1";

		// connect
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(dbURL, dbUser, dbPasswd);
			System.out.println(" Connection status: " + conn + "<P>");
		} catch (Exception e) {
			System.out.println("Connection failed in EmployeeDao: " + e.toString() + "<P>");
		}

		String sql;
		int numRowsAffected;
		Statement stmt = conn.createStatement();
		ResultSet rs;

		sql = "SELECT * FROM employees";

		rs = stmt.executeQuery(sql);

		while (rs.next()) {
			System.out.println("Id: " + rs.getString("employee_id") + "<BR>" + ", Nombre: " + rs.getString("first_name")
					+ "<BR>" + ", Apellidos: " + rs.getString("last_name") + "<BR>" + ", email: "
					+ rs.getString("email") + "<BR>" + ", Teléfono: " + rs.getString("phone_number") + "<BR>"
					+ ", Fecha de contratación: " + rs.getString("hire_date") + "<BR>" + ", Salario: "
					+ rs.getString("salary") + "<BR>" + ", Comisión: " + rs.getString("commission_pct") + "<BR>");
		}

		System.out.println("<P>");

		System.out.println("<P>");
		rs.close();
		stmt.close();

		// commit only when updating the DB
		// conn.commit();
		// disconnect
		conn.close();
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
			System.out.println("Close connection error in EmployeeDao");
			e.printStackTrace();
		}
	}

}
