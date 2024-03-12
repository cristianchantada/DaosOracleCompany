<%@ page import="java.sql.*"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.iesVda.classDaos.*"%>


<HTML>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./styles/style.css">
    <title>Busca empleado por id</title>
</head>
<BODY>
	Hello ! The system time is now
	<%=new java.util.Date()%>

	<h2>Introduzca el id de un empleado para obtener sus datos</h2>
	<form action="" method="POST">
		<label for="employeeId">Id de empleado: </label> <input type="number"
			name="employeeId" id="employeeId"> <input type="submit"
			value="consultar">
	</form>

	<%
	EmployeeDao employeeDao = new EmployeeDao();
	String employeeIdParamStr = request.getParameter("employeeId");

	if (employeeIdParamStr != null && !employeeIdParamStr.isEmpty()) {
		int employeeIdParam = Integer.parseInt(employeeIdParamStr);
		Employee employee = new Employee();
		employee.setEmployeeId(employeeIdParam);
		employee = employeeDao.get(employee);

		Job employeeJob = employee.getJob();
		Department employeeDepartment = employee.getDepartment();

		Location departmentLocation = employeeDepartment.getLocation();
		Country locationCountry = departmentLocation.getCountry();
		Region countryRegion = locationCountry.getRegion();

		List<JobHistory> employeeJobHistoryList = employee.getJobHistory();

		if (employee != null) {
			//TODO: todo el html hacer un System.out.println del html a ver si va con el toString()
			//System.out.println(employee);
	%>
	<h2>Información del Empleado</h2>
	<p>
		Id del empleado:
		<%=employee.getEmployeeId()%></p>
	<p>
		Nombre:
		<%=employee.getFirstName()%></p>
	<p>
		Apellidos:
		<%=employee.getLastName()%></p>
	<p>
		Correo electrónico:
		<%=employee.getEmail()%></p>
	<p>
		Número de teléfono:
		<%=employee.getPhoneNumber()%></p>
	<p>
		Job Id:
		<%=employee.getJobId()%></p>
			<p>
		Fecha de contratación:
		<%=employee.getHireDate()%></p>
	<p>
		Salario:
		<%=employee.getSalary()%></p>
	<p>
		Cmmisión Pct:
		<%=employee.getCommissionPct()%></p>
	<p>
		Id de su manager de departamento:
		<%=employee.getManagerId()%></p>
	<p>
		Id de su departamento:
		<%=employee.getDepartmentId()%></p>
		
	<h3>Trabajo actual del empleado:</h3>
		<p>Id del empleo: <%=employeeJob.getJobId()%></p>
		<p>Denominación del empleo: <%=employeeJob.getJobTitle()%></p>
		<p>Salario mínimo: <%=employeeJob.getMinSalary()%></p>
		<p>Salario máximo <%=employeeJob.getMaxSalary()%></p>
					
	<h3>Departamento actual del empleado:</h3>
		<p>Id del departamento: <%=employeeDepartment.getDepartmentId()%></p>
		<p>Nombre del departamento: <%=employeeDepartment.getDepartmentName()%></p>
		<p>Id del manager: <%=employeeDepartment.getManagerId()%></p>
		
		<h4>Manager del departamento:</h4>
		<p></p>		
		
		
		<h4>Localización del departamento</h4>
			<p>Id: <%=departmentLocation.getLocationId()%></p>
			<p>Calle:  <%=departmentLocation.getStreetAddress()%></p>
			<p>Código postal:  <%=departmentLocation.getPostalCode()%></p>
			<p>Ciudad:  <%=departmentLocation.getCity()%></p>
			<p>Estado/Provincia:  <%=departmentLocation.getStateProvice()%></p>			
			
			<h5>País del departamento</h5>
				<p>Id de país:  <%=locationCountry.getCountryId()%></p>
				<p>País:  <%=locationCountry.getCountryName()%></p>
				
				<h6>Región del país del departamento</h6>
					<p>Id de la región:  <%=countryRegion.getRegionId()%></p>
					<p>Región:  <%=countryRegion.getRegionName()%></p>	
				
	<h3>Historial de empleos del trabajador</h3>
	<% 
		int i = 1;
		for(JobHistory jobHistory: employeeJobHistoryList){ %>
		<h4><%= i++ %>. Empleo <%=jobHistory.getJob() %></h4>
			<p>Departamento:  <%=jobHistory.getDepartment() %></p>
			<p>Fecha de inicio:  <%=jobHistory.getStartDate()%></p>
			<p>Fecha de fin:  <%=jobHistory.getEndDate()%></p>
	<% } %>
	
				
				
		



	<%
	} else {
	%>
	<p>No se encontró ninguna región con el ID proporcionado.</p>

	<%
	}
	}
	%>
</BODY>
</HTML>
