/*package com.iesVda.classDaos;
import java.sql.*;

public class Connection {

    private String dbUser;
    private String dbPasswd;
    private String dbURL;
    private Connection conn;
    private String sql;
    private int numRowsAffected;
    private Statement stmt;
    private ResultSet rs;

    public MyConnection(){
        //initialize driver class

       public stablishConnection(){
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            } catch (Exception e) {
                System.out.println("Fail to initialize Oracle JDBC driver: " + e.toString() + "<P>");
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
                System.out.println("Connection failed: " + e.toString() + "<P>");
            }

            try {
                stmt = conn.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public closeConnection(){

        }


    }
}*/