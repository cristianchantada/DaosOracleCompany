<%@ page import="java.sql.*"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.iesVda.classDaos.*"%>
<%@ page import="com.iesVda.classDaos.Location"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./styles/style.css">
<title>Buscar job history por id del empleado</title>
</head>
<body>
	<header>
    	<h1>Empleados de la compañía y número de los mismos</h1>
    </header>
    <%
    	companyEmployees employeesOfTheCompanyInstance = new companyEmployees();
    	List<Employee> listOfTheEmployees = employeesOfTheCompanyInstance.getEmployeesOfCompany();
    	%>	
    	<h5><strong>Numero total de empleados de la empresa: </strong><%= employeesOfTheCompanyInstance.getNumberOfEmployees()%></h5>
   	<%	
    	int i = 1;
    	for(Employee employee : listOfTheEmployees){
    %>		
        <h5><%= i++ %>.- Información del Empleado con Id <%=employee.getEmployeeId()%></h5>
        <ul>
           <li><strong>ID del empleado:</strong> <%=employee.getEmployeeId()%></li>
           <li><strong>Nombre:</strong> <%=employee.getFirstName()%></li>
           <li><strong>Apellidos:</strong> <%=employee.getLastName()%></li>
           <li><strong>Correo electrónico:</strong> <%=employee.getEmail()%></li>
           <li><strong>Número de teléfono:</strong> <%=employee.getPhoneNumber()%></li>
           <li><strong>Job Id:</strong> <%=employee.getJobId()%></li>
           <li><strong>Fecha de contratación:</strong> <%=employee.getHireDate()%></li>
           <li><strong>Salario:</strong> <%=employee.getSalary()%> €</li>
           <li><strong>Comisión Pct:</strong> <%=employee.getCommissionPct()%> %</li>
           <li><strong>Id de su manager de departamento:</strong> <%=employee.getManagerId()%></li>
           <li><strong>Id de su departamento:</strong> <%=employee.getDepartmentId()%></li>
		</ul>
    <%		
    	}
    %>
    
</body>
</html>