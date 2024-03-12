<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.iesVda.classDaos.*" %>


<html>
	<head>
	    <title>Consulta de Región</title>
	</head>
	<body>

<%

	RegionDao regionDao = new RegionDao();
	
	// Procesar el formulario si se envió
	String regionIdParam = request.getParameter("regionId");
	System.out.println("regionIdParam : " + regionIdParam);
	if (regionIdParam != null && !regionIdParam.isEmpty()) {
	    int regionId = Integer.parseInt(regionIdParam);
	    Region region = new Region();
	    region.setRegionId(regionId);
	    region = regionDao.get(region);

	    if (region != null){
%>
            <h2>Información de la Región</h2>
            <p>ID: <%= region.getRegionId() %></p>
            <p>Nombre Región: <%= region.getRegionName() %></p>
<%
        } else {
%>
            <p>No se encontró ninguna región con el ID proporcionado.</p>
<%
        }
	} 
%>
		<h2>Consulta de una región por su ID</h2>
		<form action="" method="post">
		    <label for="regionId">Ingrese el ID de la región:</label>
		    <input type="text" id="regionId" name="regionId" required>
		    <button type="submit">Consultar</button>
		</form>
		<br>
		<h2>Obtención de todas las regiones</h2>
		<form action="" method="post">
		    <input type="hidden" id="getAllRegions" name="regionId" required>
		    <button type="submit">Devolver todas las regiones</button>
		</form>
	</body>
</html>
