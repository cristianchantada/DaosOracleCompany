<%@ page import="java.sql.*"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.iesVda.classDaos.*"%>
<%@ page import="com.iesVda.classDaos.Location"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./styles/style.css">
    <title>Busca empleado por id</title>
</head>
<body>
    <div class="container">
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

                    List<JobHistory> employeeJobHistoryList = employee.getJobHistory();
                                                     
                   
                    if (employee.getEmployeeId() != 0) { %>
               
                        <h2>Información del Empleado</h2>
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

                        <h3>Trabajo actual del empleado:</h3>
                        <ul>
                            <li><strong>Id del empleo:</strong> <%=employeeJob.getJobId()%></li>
                            <li><strong>Denominación del empleo:</strong> <%=employeeJob.getJobTitle()%></li>
                            <li><strong>Salario mínimo:</strong> <%=employeeJob.getMinSalary()%> €</li>
                            <li><strong>Salario máximo:</strong> <%=employeeJob.getMaxSalary()%> €</li>
                        </ul>

                        <h3>Departamento actual del empleado:</h3>
                        <ul>
                            <li><strong>Id del departamento:</strong> <%=employeeDepartment.getDepartmentId()%></li>
                            <li><strong>Nombre del departamento:</strong> <%=employeeDepartment.getDepartmentName()%></li>
                            <li><strong>Id del manager:</strong> <%=employeeDepartment.getManagerId()%></li>
                        </ul>

                        <h4>Manager del departamento:</h4>
                        <% if(manager.getEmployeeId() != employee.getEmployeeId()){ %>                         
                            <ul>
                                <li><strong>Nombre</strong> <%= manager.getFirstName() %></li>
                                <li><strong>Apellidos</strong> <%= manager.getLastName()%></li>
                            </ul>
                        <% } else { %>
                            <ul>
                                <li><strong><%= manager.getFirstName() %> <%= manager.getLastName()%></strong> es el propio manager del departamento.</li>                  
                            </ul>
                        <% } %>   

                        <h4>Localización del departamento:</h4>
                        <ul>
                            <li><strong>Id:</strong> <%=departmentLocation.getLocationId()%></li>
                            <li><strong>Calle:</strong> <%=departmentLocation.getStreetAddress()%></li>
                            <li><strong>Código postal:</strong> <%=departmentLocation.getPostalCode()%></li>
                            <li><strong>Ciudad:</strong> <%=departmentLocation.getCity()%></li>
                            <li><strong>Estado/Provincia:</strong> <%=departmentLocation.getStateProvice()%></li>
                        </ul>

                        <h5>País del departamento:</h5>
                        <ul>
                            <li><strong>Id de país:</strong> <%=locationCountry.getCountryId()%></li>
                            <li><strong>País:</strong> <%=locationCountry.getCountryName()%></li>
                        </ul>

                        <h6>Región del país del departamento:</h6>
                        <ul>
                            <li><strong>Id de la región:</strong> <%=countryRegion.getRegionId()%></li>
                            <li><strong>Región:</strong> <%=countryRegion.getRegionName()%></li>
                        </ul>

                        <h3>Historial de empleos del trabajador</h3>
                        <% 
                            int i = 1;
                            if (employeeJobHistoryList != null && !employeeJobHistoryList.isEmpty()) {
                                for(JobHistory jobHistory: employeeJobHistoryList) { %>
                                    <ul>
                                    	<li>Empleo número <%= i++ %>: -------------------------------------------------------</li>
                                        <li><strong>Trabajo:</strong> <%= jobHistory.getJob().getJobTitle() %></li>
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
            </section>
        </main>
    </div>
</body>
</html>
