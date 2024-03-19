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
    	<h1>Busca empleado por ID</h1>
    </header>
       <main>
       <form action="" method="POST">
           <label for="employeeId">ID de empleado:</label>
           <input type="number" name="employeeId" id="employeeId">
           <button type="submit">Consultar</button>
       </form>
       <section id="employeeInfo">
                <% 
                EmployeeDao employeeDao = new EmployeeDao();
                String employeeIdParamStr = request.getParameter("employeeId");

                if (employeeIdParamStr != null && !employeeIdParamStr.isEmpty()) {
                    int employeeIdParam = Integer.parseInt(employeeIdParamStr);
                    Employee employeeInput = new Employee();                 
                    employeeInput.setEmployeeId(employeeIdParam);
                    
                    Employee employee = new Employee();
                    employee = employeeDao.get(employeeInput);
                                    
                    Job employeeJob = employee.getJob();
                    Department employeeDepartment = employee.getDepartment();
					
                    
                    
                    Location departmentLocation = employeeDepartment.getLocation();
                    Employee manager = employeeDepartment.getManager();
                    Country locationCountry = departmentLocation.getCountry();
                    Region countryRegion = locationCountry.getRegion();

                    List<jobHistory> employeeJobHistoryList = employee.getJobHistory();
                                                     
                   
                    if (employee.getEmployeeId() != 0) { %>

                   <h3>Historial de empleos del trabajador</h3>
                   <% 
                       int i = 1;
                       if (employeeJobHistoryList != null && !employeeJobHistoryList.isEmpty()) {
                           for(jobHistory jobHistory: employeeJobHistoryList) { %>
                               <h5>Empleo número <%= i++ %></h5>
                               <ul>
                               	
                                   <li><strong>Denominación del trabajo:</strong> <%= jobHistory.getJob_title() %></li>
                                   <li><strong>Departamento:</strong> <%= jobHistory.getDepartment().getDepartmentName() %></li>
                                   <li><strong>Fecha de inicio:</strong> <%= jobHistory.getStartDate() %></li>
                                   <li><strong>Fecha de fin:</strong> <%= jobHistory.getEndDate() %></li>
                               </ul>
                           <% } %>
                       <% } else { %>
                           <p>El empleado no posee historial de trabajos</p>
                       <% } %>   
               <% } else { %>
                   <p>No se encontró ningún empleado con el ID proporcionado.</p>
               <% } %>  
           <% } %>                      
</body>
</html>