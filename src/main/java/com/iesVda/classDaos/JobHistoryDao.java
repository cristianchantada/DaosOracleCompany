package com.iesVda.classDaos;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class JobHistoryDao implements DaoInterface<JobHistory> {

	private List<JobHistory> jobHistories = new ArrayList<>();

	private String dbUser;
	private String dbPasswd;
	private String dbURL;
	private Connection conn;
	private String sql;
	private int numRowsAffected;
	private Statement stmt;
	private ResultSet rs;

	public JobHistoryDao() {
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
	public JobHistory get(JobHistory jb) {
		return jb;
	}

	public List<JobHistory> getJobHistoryList(JobHistory jobHistory) {

		int employeeId = jobHistory.getEmployeeId();
		sql = "SELECT * FROM job_history WHERE employee_id = " + employeeId;
		List<JobHistory> jobList = new ArrayList();

		try {
			this.rs = stmt.executeQuery(sql);

			while (rs.next()) {
				jobHistory = new JobHistory();

				jobHistory.setEmployeeId(rs.getInt("employee_id"));

				try {
					DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					LocalDate localStartDate = LocalDate.parse(rs.getString("start_date"), format);
					jobHistory.setStartDate(localStartDate);
				} catch (DateTimeParseException dTe) {
					System.out.println("Error al parsear la fecha stertDate en JobHistoryDao: " + dTe.getMessage());
				}

				try {
					DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					LocalDate localEndDate = LocalDate.parse(rs.getString("end_date"), format);
					jobHistory.setEndDate(localEndDate);
				} catch (DateTimeParseException dTe) {
					System.out.println("Error al parsear la fecha endDate en JobHistoryDao: " + dTe.getMessage());
				}

				jobHistory.setJobId(rs.getString("job_id"));
				jobHistory.setDepartmentId(Integer.parseInt(rs.getString("department_id")));
				Department department = new Department(jobHistory.getDepartmentId());
				DepartmentDao departmentDao = new DepartmentDao();
				Job job = new Job(jobHistory.getJobId());
				JobDao jobDao = new JobDao();
				jobHistory.setDepartment(departmentDao.getDepartmentOfJobHistory(department));
				jobHistory.setJob(jobDao.get(job));
				jobList.add(jobHistory);
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
	public List<JobHistory> getAll() {
		return jobHistories;
	}

	@Override
	public void save(JobHistory e) {
		jobHistories.add(e);
	}

	@Override
	public void update(JobHistory e, String[] params) {

	}

	@Override
	public void delete(JobHistory e) {
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
