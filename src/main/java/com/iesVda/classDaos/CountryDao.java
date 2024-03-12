package com.iesVda.classDaos;
import java.util.ArrayList;
import java.sql.*;
import java.sql.Connection;
import java.util.List;

public class CountryDao implements DaoInterface<Country> {

    private List<Country> countries = new ArrayList<>();
    
    private String dbUser;
    private String dbPasswd;
    private String dbURL;
    private Connection conn;
    private String sql;
    private int numRowsAffected;
    private Statement stmt;
    private ResultSet rs;

    public CountryDao(){
        //initialize driver class
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (Exception e) {
            System.out.println("Fail to initialize Oracle JDBC driver in CountryDao: " + e.toString() + "<P>");
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
            System.out.println("Connection failed in CountryDao: " + e.toString() + "<P>");
        }

        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            System.out.println("SQL exception in CountryDao");
            e.printStackTrace();
        }
    }

    @Override
    public Country get(Country country) {

    	String countryId = country.getCountryId();
        sql = "SELECT * FROM countries WHERE country_id = '" + countryId + "'";

        try {
            this.rs = stmt.executeQuery(sql);
            if (rs.next()) { // Mover el cursor al primer registro
                country.setCountryName(rs.getString("country_name"));
                country.setRegionid(Integer.parseInt(rs.getString("region_id")));
            } else {
                // Manejar el caso en que no se encuentre ningún resultado para el ID dado
                System.out.println("No se encontró ningún país con el ID proporcionado.");
            }
        } catch (SQLException e1) {
            System.out.println("SQL exception in CountryDao");
            e1.printStackTrace();
        } catch (NumberFormatException nfe) {
            System.out.println("Ha ocurrido un error de parseo a entero en CountryDao: " + nfe.getMessage());
            nfe.printStackTrace();
        } finally {
            closeConnection();
        }

        Region region = new Region(country.getRegionid());
        RegionDao regionDao = new RegionDao();
        country.setRegion(regionDao.get(region));
        return country;
    }

    @Override
    public List<Country> getAll() {
        return countries;
    }

    @Override
    public void save(Country e) {
        countries.add(e);
    }

    @Override
    public void update(Country e, String[] params) {

    }

    @Override
    public void delete(Country e) {
        countries.remove(e);
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
            System.out.println("Close connection error in CountryDao");
			e.printStackTrace();
		}
    }
    
}
