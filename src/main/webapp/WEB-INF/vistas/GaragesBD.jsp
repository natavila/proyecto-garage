<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Garages en BD</title>
<body>
	<h2>Buscar Garage:</h2>
				<form:form action="buscoElGarage" method="POST" modelAttribute="garage">
			    	
					<hr class="colorgraph"><br>

					<form:input path="nombre" id="nombre" name="nombre" type="text" class="form-control"/><p>NOMBRE</p>
				
					<button class="btn btn-lg btn-primary btn-block" Type="Submit">Buscar</button>
				</form:form>
	
</body>
=======
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Garages en BD</title>
<body>
	<h2>Buscar Garage:</h2>
				<form:form action="buscoElGarage" method="POST" modelAttribute="garage">
			    	
					<hr class="colorgraph"><br>

					<form:input path="nombre" id="nombre" name="nombre" type="text" class="form-control"/><p>NOMBRE</p>
				
					<button class="btn btn-lg btn-primary btn-block" Type="Submit">Buscar</button>
				</form:form>
	
</body>
>>>>>>> b7f08d3865a369885f4ff345531403514a4216cb
</html>