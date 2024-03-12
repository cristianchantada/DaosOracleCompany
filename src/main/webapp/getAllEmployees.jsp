<%@ page import="java.sql.*" %>
<HTML>
    <HEAD>
        <TITLE>Lista de Empleados</TITLE>
    </HEAD>
    <BODY>
        <H2>Lista de Empleados</H2>
        <%
            try { 
                Class.forName("oracle.jdbc.driver.OracleDriver");
            } catch (Exception e) {
                out.println("Error al inicializar el controlador Oracle JDBC: " + e.toString() + "<P>");
            }
            
            String dbUser = "hr";
            String dbPasswd = "oracle";
            String dbURL = "jdbc:oracle:thin:@localhost:1521/freepdb1";
            
            Connection conn = null;
            
            try {
                conn = DriverManager.getConnection(dbURL, dbUser, dbPasswd);
                out.println("Estado de la conexi�n: Conectado<P>");
            } catch(Exception e) {
                out.println("Fallo de conexi�n: " + e.toString() + "<P>"); 
            }
            
            String sql = "SELECT * FROM employees";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
        %>

        <TABLE border="1">
            <TR>
                <TH>ID</TH>
                <TH>Nombre</TH>
                <TH>Apellidos</TH>
                <TH>Email</TH>
                <TH>Tel�fono</TH>
                <TH>Fecha de Contrataci�n</TH>
                <TH>Salario</TH>
                <TH>Comisi�n</TH>
            </TR>
            
            <%
                while (rs.next()) {
            %>
            <TR>
                <TD><%= rs.getString("employee_id") %></TD>
                <TD><%= rs.getString("first_name") %></TD>
                <TD><%= rs.getString("last_name") %></TD>
                <TD><%= rs.getString("email") %></TD>
                <TD><%= rs.getString("phone_number") %></TD>
                <TD><%= rs.getString("hire_date") %></TD>
                <TD><%= rs.getString("salary") %></TD>
                <TD><%= rs.getString("commission_pct") %></TD>
            </TR>
            <%
                }
                rs.close();
                stmt.close();
                conn.close();
            %>
        </TABLE>
        
        <P>Bye bye! The system time is now <%= new java.util.Date() %></P>
    </BODY>
</HTML>
