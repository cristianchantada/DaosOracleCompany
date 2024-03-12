package com.iesVda.classDaos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDao implements DaoInterface<Job> {

    private List<Job> jobs = new ArrayList<>();
    
    private String dbUser;
    private String dbPasswd;
    private String dbURL;
    private Connection conn;
    private String sql;
    private int numRowsAffected;
    private Statement stmt;
    private ResultSet rs;

    public JobDao(){
        //initialize driver class
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (Exception e) {
            System.out.println("Fail to initialize Oracle JDBC driver in JobDao: " + e.toString() + "<P>");
        }

        dbUser = "hr";
        dbPasswd = "oracle";
        dbURL = "jdbc:oracle:thin:@localhost:1521/freepdb1";

        //connect
        conn = null;
        try {
            conn = DriverManager.getConnection(dbURL,dbUser,dbPasswd);
            System.out.println(" Connection status: " + conn + "<P>");
        } catch(Exception e) {
            System.out.println("Connection failed in JobDao: " + e.toString() + "<P>");
        }

        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Job get(Job job) {
    	
    	String jobId = job.getJobId();

		sql = "SELECT * FROM jobs WHERE job_id = '" + jobId + "'";

		try {
			this.rs = stmt.executeQuery(sql);
			if (rs.next()) { // Mover el cursor al primer registro
				
				job.setJobTitle(rs.getString("job_title"));
				job.setMinSalary(Double.parseDouble(rs.getString("min_salary")));
				job.setMaxSalary(Double.parseDouble(rs.getString("max_salary")));

			} else {
				// Manejar el caso en que no se encuentre ningún resultado para el ID dado
				System.out.println("No se encontró ningún JOB con el ID proporcionado.");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (NumberFormatException nfe) {
			System.out.println("Ha ocurrido un error de parseo a entero en JobDao: " + nfe.getMessage());
			nfe.printStackTrace();
		} finally {
			closeConnection();
		}

        return job;
    }
   

    @Override
    public List<Job> getAll() {
        return jobs;
    }

    @Override
    public void save(Job e) {
        jobs.add(e);
    }

    @Override
    public void update(Job e, String[] params) {

    }

    @Override
    public void delete(Job e) {
        jobs.remove(e);
    }
    
    
    public void closeConnection(){
		try {
			rs.close();
			stmt.close();
			//commit only when updating the DB
			//conn.commit();
			//disconnect
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}
