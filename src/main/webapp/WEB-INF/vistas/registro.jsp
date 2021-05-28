<<<<<<< HEAD
<<<<<<< Upstream, based on branch 'master' of https://github.com/natavila/proyecto-garage.git
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Registro</title>
	<!-- Bootstrap core CSS 
		    <link href="css/bootstrap.min.css" rel="stylesheet" >-->
		    <!-- Bootstrap theme
		    <link href="css/bootstrap-theme.min.css" rel="stylesheet"> -->
		    <link href="css/estilos.css" rel="stylesheet">
		    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
		    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
	</head>
	<body>
		<main class="container"> <!-- Clase de Bootstrap. Hace que los elementos no lleguen hasta el borde -->
			<form:form  action="procesarRegistro" method="POST" modelAttribute="cliente" class="row g-3">
			<h1 class="mt-3">Registro</h1>
				<div class="col-md-6">			
		    <label for="nombre" class="form-label control-label">Nombre</label>
		    <form:input type="text" class="form-control" id="nombre" path="nombre"/>
		  </div>
		  <div class="col-md-6">
		    <label for="apellido" class="form-label control-label">Apellido</label>
		    <form:input type="text" class="form-control" id="apellido" path="apellido"/>
		  </div>
		  <div class="col-12">
		    <label for="dni" class="form-label control-label">DNI</label>
		    <input type="number" class="form-control" id="dni" name="dni"/>
		  </div>
		  <div class="col-12">
		    <label for="email" class="form-label control-label">Correo electronico</label>
		    <form:input type="email" class="form-control" id="email" path="email"/>
		  </div>
		  <div class="col-12">
		    <label for="email" class="form-label control-label">Roll</label>
		    <form:input type="text" class="form-control" id="roll" path="roll"/>
		  </div>
		  
		  <div class="col-12">
		    <label for="password" class="form-label control-label">Contraseña</label>
		    <form:input type="password" class="form-control" id="password" path="password"/>
		  </div>
		  <div class="col-12">
		    <label for="repass" class="form-label control-label">Confirmar contraseña</label>
		    <input type="password" class="form-control" id="repassword" name="repassword">
		  </div>
		  <div class="col-12">
		    <button type="submit" class="btn btn-primary control-label mb-3">Registrarme</button>
		  </div>
  		</form:form>
  		<%--Bloque que es visible si el elemento error no estÃ¡ vacÃ­o	--%>
				<c:if test="${not empty error}">
			        <h4><span>${error}</span></h4>
			        <br>
		        </c:if>	
	</main>
	</body>
=======
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Registro</title>
	<!-- Bootstrap core CSS 
		    <link href="css/bootstrap.min.css" rel="stylesheet" >-->
		    <!-- Bootstrap theme
		    <link href="css/bootstrap-theme.min.css" rel="stylesheet"> -->
		    <link href="css/estilos.css" rel="stylesheet">
		    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
		    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
	</head>
	<body>
		<main class="container"> <!-- Clase de Bootstrap. Hace que los elementos no lleguen hasta el borde -->
			<form:form  action="procesarRegistro" method="POST" modelAttribute="cliente" class="row g-3">
			<h1 class="mt-3">Registro</h1>
				<div class="col-md-6">			
		    <label for="nombre" class="form-label control-label">Nombre</label>
		    <form:input type="text" class="form-control" id="nombre" path="nombre"/>
		  </div>
		  <div class="col-md-6">
		    <label for="apellido" class="form-label control-label">Apellido</label>
		    <form:input type="text" class="form-control" id="apellido" path="apellido"/>
		  </div>
		  <div class="col-12">
		    <label for="dni" class="form-label control-label">DNI</label>
		    <input type="number" class="form-control" id="dni" name="dni"/>
		  </div>
		  <div class="col-12">
		    <label for="email" class="form-label control-label">Correo electronico</label>
		    <form:input type="email" class="form-control" id="email" path="email"/>
		  </div>
		  <div class="col-12">
		    <label for="password" class="form-label control-label">Contraseña</label>
		    <form:input type="password" class="form-control" id="password" path="password"/>
		  </div>
		  <div class="col-12">
		    <label for="repass" class="form-label control-label">Confirmar contraseña</label>
		    <input type="password" class="form-control" id="repassword" name="repassword">
		  </div>
		  <div class="col-12">
		    <button type="submit" class="btn btn-primary control-label mb-3">Registrarme</button>
		  </div>
  		</form:form>
  		<%--Bloque que es visible si el elemento error no estÃ¡ vacÃ­o	--%>
				<c:if test="${not empty error}">
			        <h4><span>${error}</span></h4>
			        <br>
		        </c:if>	
	</main>
	</body>
>>>>>>> b7f08d3865a369885f4ff345531403514a4216cb
=======
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Registro</title>
	<!-- Bootstrap core CSS 
		    <link href="css/bootstrap.min.css" rel="stylesheet" >-->
		    <!-- Bootstrap theme
		    <link href="css/bootstrap-theme.min.css" rel="stylesheet"> -->
		    <link href="css/estilos.css" rel="stylesheet">
		    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
		    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
	</head>
	<body>
		<main class="container"> <!-- Clase de Bootstrap. Hace que los elementos no lleguen hasta el borde -->
			<form:form  action="procesarRegistro" method="POST" modelAttribute="cliente" class="row g-3">
			<h1 class="mt-3">Registro</h1>
				<div class="col-md-6">			
		    <label for="nombre" class="form-label control-label">Nombre</label>
		    <form:input type="text" class="form-control" id="nombre" path="nombre"/>
		  </div>
		  <div class="col-md-6">
		    <label for="apellido" class="form-label control-label">Apellido</label>
		    <form:input type="text" class="form-control" id="apellido" path="apellido"/>
		  </div>
		  <div class="col-12">
		    <label for="dni" class="form-label control-label">DNI</label>
		    <input type="number" class="form-control" id="dni" name="dni"/>
		  </div>
		  <div class="col-12">
		    <label for="email" class="form-label control-label">Correo electronico</label>
		    <form:input type="email" class="form-control" id="email" path="email"/>
		  </div>
		  <div class="col-12">
		    <label for="password" class="form-label control-label">Contraseña</label>
		    <form:input type="password" class="form-control" id="password" path="password"/>
		  </div>
		  <div class="col-12">
		    <label for="repass" class="form-label control-label">Confirmar contraseña</label>
		    <input type="password" class="form-control" id="repassword" name="repassword">
		  </div>
		  <div class="col-12">
		    <button type="submit" class="btn btn-primary control-label mb-3">Registrarme</button>
		  </div>
  		</form:form>
  		<%--Bloque que es visible si el elemento error no estÃ¡ vacÃ­o	--%>
				<c:if test="${not empty error}">
			        <h4><span>${error}</span></h4>
			        <br>
		        </c:if>	
	</main>
	</body>
=======
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Registro</title>
	<!-- Bootstrap core CSS 
		    <link href="css/bootstrap.min.css" rel="stylesheet" >-->
		    <!-- Bootstrap theme
		    <link href="css/bootstrap-theme.min.css" rel="stylesheet"> -->
		    <link href="css/estilos.css" rel="stylesheet">
		    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
		    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
	</head>
	<body>
		<main class="container"> <!-- Clase de Bootstrap. Hace que los elementos no lleguen hasta el borde -->
			<form:form  action="procesarRegistro" method="POST" modelAttribute="cliente" class="row g-3">
			<h1 class="mt-3">Registro</h1>
				<div class="col-md-6">			
		    <label for="nombre" class="form-label control-label">Nombre</label>
		    <form:input type="text" class="form-control" id="nombre" path="nombre"/>
		  </div>
		  <div class="col-md-6">
		    <label for="apellido" class="form-label control-label">Apellido</label>
		    <form:input type="text" class="form-control" id="apellido" path="apellido"/>
		  </div>
		  <div class="col-12">
		    <label for="dni" class="form-label control-label">DNI</label>
		    <input type="number" class="form-control" id="dni" name="dni"/>
		  </div>
		  <div class="col-12">
		    <label for="email" class="form-label control-label">Correo electronico</label>
		    <form:input type="email" class="form-control" id="email" path="email"/>
		  </div>
		  <div class="col-12">
		    <label for="password" class="form-label control-label">Contraseña</label>
		    <form:input type="password" class="form-control" id="password" path="password"/>
		  </div>
		  <div class="col-12">
		    <label for="repass" class="form-label control-label">Confirmar contraseña</label>
		    <input type="password" class="form-control" id="repassword" name="repassword">
		  </div>
		  <div class="col-12">
		    <button type="submit" class="btn btn-primary control-label mb-3">Registrarme</button>
		  </div>
  		</form:form>
  		<%--Bloque que es visible si el elemento error no estÃ¡ vacÃ­o	--%>
				<c:if test="${not empty error}">
			        <h4><span>${error}</span></h4>
			        <br>
		        </c:if>	
	</main>
	</body>
>>>>>>> refs/remotes/origin/SantosGaston
>>>>>>> e61c56b ge remote-tracking branch 'origin/SantosGaston' into ramaNataly
	</html>