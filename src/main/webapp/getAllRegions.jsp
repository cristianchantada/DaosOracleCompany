<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista de Regiones</title>
</head>
<body>

<%
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
    } catch (Exception e) {
        out.println("Fail to initialize Oracle JDBC driver: " + e.toString() + "<P>");
    }

    String dbUser = "hr";
    String dbPasswd = "oracle";
    String dbURL = "jdbc:oracle:thin:@localhost:1521/freepdb1";

    Connection conn = null;
    try {
        conn = DriverManager.getConnection(dbURL, dbUser, dbPasswd);
        out.println(" Connection status: " + conn + "<P>");
    } catch(Exception e) {
        out.println("Connection failed: " + e.toString() + "<P>");
    }

    String sql;
    int numRowsAffected;
    Statement stmt = conn.createStatement();
    ResultSet rs;

    sql = "SELECT * FROM regions";

    rs = stmt.executeQuery(sql);
%>

<h2>Lista de Regiones</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Nombre Región</th>
    </tr>

<%
    while (rs.next()) {
%>
    <tr>
        <td><%= rs.getString("region_id") %></td>
        <td><%= rs.getString("region_name") %></td>
    </tr>
<%
    }
    rs.close();
    stmt.close();
    conn.close();
%>

</table>

</body>
</html>
