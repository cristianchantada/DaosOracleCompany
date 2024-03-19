package com.iesVda.classDaos;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class jobHistoryDao implements DaoInterface<jobHistory> {

	private List<jobHistory> jobHistories = new ArrayList<>();

	private String dbUser;
	private String dbPasswd;
	private String dbURL;
	private Connection conn;
	private String sql;
	private int numRowsAffected;
	private Statement stmt;
	private ResultSet rs;

	public jobHistoryDao() {
		// initialize driver class
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println("Fail to initialize Oracle JDBC driver in JobHistoryDao: " + e.toString() + "<P>");
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
			System.out.println("Connection failed in JobHistoryDao: " + e.toString() + "<P>");
		}

		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public jobHistory get(jobHistory jb) {
		return jb;
	}
	
    
	public List<jobHistory> getEmployeeJobHistory(int employeeId){

		jobHistory jobHistoryVar = new jobHistory(employeeId);
	
		sql = "SELECT * FROM job_history WHERE employee_id = " + employeeId;
		List<jobHistory> jobList = new ArrayList<>();

		try {
			this.rs = stmt.executeQuery(sql);

			while (rs.next()) {
				jobHistoryVar = new jobHistory();

				jobHistoryVar.setEmployeeId(rs.getInt("employee_id"));

				try {
					DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					LocalDate localStartDate = LocalDate.parse(rs.getString("start_date"), format);
					jobHistoryVar.setStartDate(localStartDate);
				} catch (DateTimeParseException dTe) {
					System.out.println("Error al parsear la fecha stertDate en JobHistoryDao: " + dTe.getMessage());
				}

				try {
					DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					LocalDate localEndDate = LocalDate.parse(rs.getString("end_date"), format);
					jobHistoryVar.setEndDate(localEndDate);
				} catch (DateTimeParseException dTe) {
					System.out.println("Error al parsear la fecha endDate en JobHistoryDao: " + dTe.getMessage());
				}

				jobHistoryVar.setJobId(rs.getString("job_id"));
				jobHistoryVar.setDepartmentId(Integer.parseInt(rs.getString("department_id")));
				Department department = new Department(jobHistoryVar.getDepartmentId());
				DepartmentDao departmentDao = new DepartmentDao();
				Job job = new Job(jobHistoryVar.getJobId());
				JobDao jobDao = new JobDao();
				jobHistoryVar.setDepartment(departmentDao.getDepartmentOfJobHistory(department));
				jobHistoryVar.setJob(jobDao.get(job));
				jobHistoryVar.setJob_title(jobDao.get(job).getJobTitle());
				jobList.add(jobHistoryVar);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (NumberFormatException nfe) {
			System.out.println("Ha ocurrido un error de parseo a entero en JobHistoryDao: " + nfe.getMessage());
			nfe.printStackTrace();
		} finally {
			closeConnection();
		}

		return jobList;
	}




	public List<jobHistory> getEmployeeJobHistory(jobHistory jobHistoryVar) {

		int employeeId = jobHistoryVar.getEmployeeId();
		sql = "SELECT * FROM job_history WHERE employee_id = " + employeeId;
		List<jobHistory> jobList = new ArrayList<>();

		try {
			this.rs = stmt.executeQuery(sql);

			while (rs.next()) {
				jobHistoryVar = new jobHistory();

				jobHistoryVar.setEmployeeId(rs.getInt("employee_id"));

				try {
					DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					LocalDate localStartDate = LocalDate.parse(rs.getString("start_date"), format);
					jobHistoryVar.setStartDate(localStartDate);
				} catch (DateTimeParseException dTe) {
					System.out.println("Error al parsear la fecha stertDate en JobHistoryDao: " + dTe.getMessage());
				}

				try {
					DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					LocalDate localEndDate = LocalDate.parse(rs.getString("end_date"), format);
					jobHistoryVar.setEndDate(localEndDate);
				} catch (DateTimeParseException dTe) {
					System.out.println("Error al parsear la fecha endDate en JobHistoryDao: " + dTe.getMessage());
				}

				jobHistoryVar.setJobId(rs.getString("job_id"));
				jobHistoryVar.setDepartmentId(Integer.parseInt(rs.getString("department_id")));
				Department department = new Department(jobHistoryVar.getDepartmentId());
				DepartmentDao departmentDao = new DepartmentDao();
				Job job = new Job(jobHistoryVar.getJobId());
				JobDao jobDao = new JobDao();
				jobHistoryVar.setDepartment(departmentDao.getDepartmentOfJobHistory(department));
				jobHistoryVar.setJob(jobDao.get(job));
				jobList.add(jobHistoryVar);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (NumberFormatException nfe) {
			System.out.println("Ha ocurrido un error de parseo a entero en JobHistoryDao: " + nfe.getMessage());
			nfe.printStackTrace();
		} finally {
			closeConnection();
		}

		return jobList;
	}

	@Override
	public List<jobHistory> getAll() {
		return jobHistories;
	}

	@Override
	public void save(jobHistory e) {
		jobHistories.add(e);
	}

	@Override
	public void update(jobHistory e, String[] params) {

	}

	@Override
	public void delete(jobHistory e) {
		jobHistories.remove(e);
	}

	public void closeConnection() {
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
