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
<%@ include file="headerAdmin.jsp" %>

		<div class = "container mt-3">
			<div id="loginbox" style="margin-top:25px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
				<%--Definicion de un form asociado a la accion /validar-login por POST. Se indica ademas que el model attribute se--%>
				<%--debe referenciar con el nombre usuario, spring mapea los elementos de la vista con los atributos de dicho objeto--%>
					<%--para eso debe coincidir el valor del elemento path de cada input con el nombre de un atributo del objeto --%>
				<form:form action="confirmarAgregarGarage" method="POST" modelAttribute="garage">
			    	<h3 class="form-signin-heading">Registrar Garage</h3>
					<hr class="colorgraph"><br>
					<%--Elementos de entrada de datos, el elemento path debe indicar en que atributo del objeto usuario se guardan los datos ingresados--%>
					<p><b>NOMBRE</b></p>
					<form:input path="nombre" id="nombre" name="nombre" type="text" class="form-control"/>
					<p><b>CALLE</b></p>
					<form:input path="calle" id="calle" name="calle" type="text" class="form-control"/>
					<p><b>Numero</b></p>
					<form:input path="numero" id="numero" name="numero" type="number" class="form-control" />
					
					<p><b>Localidad</b></p>

					<form:select path = "localidad" id="localidad" name="localidad" class="form-control" >
                     <form:option value = "NONE" label = "Select"/>
                     <form:options items = "${loc}" />
                    </form:select>  
					
					<br>
					<p><b>PRECIO HORA $$</b></p>
					<form:input path="precioHora" id="precioHora" name="precioHora" type="number" class="form-control"/>
					<p><b>PRECIO ESTADIA$$</b></p>
					<form:input path="precioEstadia" id="precioEstadia" name="precioEstadia" type="number" class="form-control"/>
					<p><b>CAPACIDAD</b></p>	
					<form:input path="capacidad" id="capacidad" name="capacidad" type="number" class="form-control"/>
					<h4 class="form-signin-heading"><b>Coordenadas</b></h4>
					<hr class="colorgraph"><br>
					<a role="button"class="btn btn-primary"  href="https://www.google.com/maps/" target="_blank">Buscar Coordenadas?</a>
					<hr class="colorgraph"><br>
					<p><b>LATITUD</b></p>
					<form:input path="latitud" id="latitud" name="latitud" type="number" class="form-control"/>
					<p><b>LONGITUD</b></p>	
					<form:input path="longitud" id="longitud" name="longitud" type="number" class="form-control"/>
					<br>
					
					<button class="btn btn-lg btn-success btn-block" Type="Submit">Agregar</button>
					
				</form:form>
				</br>
				<a class="btn btn-primary" href="javascript:history.back()" role="button">Volver</a>
				<%--Bloque que es visible si el elemento error no está vacío	--%>
				<c:if test="${not empty error}">
			        <h4><span>${error}</span></h4>
			        <br>
		        </c:if>	
		     
		        
			</div>
		</div>
		
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
	
		


</html>
