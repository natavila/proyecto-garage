<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
	<!-- Bootstrap core CSS -->
	    <link href="css/bootstrap.min.css" rel="stylesheet" >
	    <!-- Bootstrap theme -->
	    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
		<meta charset="ISO-8859-1">
		<title>Registro Garage</title>
</head>
<body>

	<body>
		<div class = "container">
			<div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
				<%--Definicion de un form asociado a la accion /validar-login por POST. Se indica ademas que el model attribute se--%>
				<%--debe referenciar con el nombre usuario, spring mapea los elementos de la vista con los atributos de dicho objeto--%>
					<%--para eso debe coincidir el valor del elemento path de cada input con el nombre de un atributo del objeto --%>
				<form:form action="confirmarAgregarGarage" method="POST" modelAttribute="garage">
			    	<h3 class="form-signin-heading">Garage</h3>
					<hr class="colorgraph"><br>

					
					<%--Elementos de entrada de datos, el elemento path debe indicar en que atributo del objeto usuario se guardan los datos ingresados--%>
					<form:input path="nombre" id="nombre" name="nombre" type="text" class="form-control"/><p>NOMBRE</p>
					<form:input path="calle" id="calle" name="calle" type="text" class="form-control"/><p>CALLE</p>
					<form:input path="numero" id="numero" name="numero" type="text" class="form-control" /><p>Numero</p>
					<form:input path="localidad" id="localidad" name="localidad" type="text" class="form-control"/><p>Localidad</p>
					<form:input path="precioHora" id="precioHora" name="precioHora" type="text" class="form-control"/><p>PRECIO HORA</p>
					<form:input path="precioEstadia" id="precioEstadia" name="precioEstadia" type="text" class="form-control"/><p>PRECIO ESTADIA</p>
					<form:input path="precioMes" id="precioMes" name="precioMes" type="text" class="form-control"/><p>PRECIO MES</p>
					<form:input path="capacidad" id="capacidad" name="capacidad" type="text" class="form-control"/><p>CAPACIDAD</p>	
					<button class="btn btn-lg btn-primary btn-block" Type="Submit">Agregar</button>
				</form:form>

				<%--Bloque que es visible si el elemento error no está vacío	--%>
				<c:if test="${not empty error}">
			        <h4><span>${error}</span></h4>
			        <br>
		        </c:if>	
		        
		        
		       
		        
		      
		        <p class="text-center">
		        <a href="lista">Volver</a>
		        </p>
		        
			</div>
		</div>
		
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
	
		

</body>
</html>
