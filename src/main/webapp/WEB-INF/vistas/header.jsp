<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous"/>
	    <link href="css/estilos.css"/>
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
<meta charset="ISO-8859-1">

</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		  <div class="container">
		  
		    <a class="navbar-brand" href="#">Navbar</a>
		    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		      <span class="navbar-toggler-icon"></span>
		    </button>
		    <button class="btn btn-dark" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasWithBothOptions" aria-controls="offcanvasWithBothOptions">Menu</button>
		<div class="offcanvas offcanvas-start" data-bs-scroll="true" tabindex="-1" id="offcanvasWithBothOptions" aria-labelledby="offcanvasWithBothOptionsLabel">
		  <div class="offcanvas-header text-light bg-dark">
		    <h5 class="offcanvas-title" id="offcanvasWithBothOptionsLabel">¡Bienvenido/a ${cliente.nombre}!</h5>
		    <button type="button" class="btn-close btn-light text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
		  </div>
		  <div class="offcanvas-body">
		    <p><a href="${pageContext.request.contextPath}/mostrarBilletera/${cliente.id}">Mi billetera</a></p>
		   	<p><a href="${pageContext.request.contextPath}/formularioSaldo/${cliente.id}">Ingresar dinero</a></p>
		    <p><a href="${pageContext.request.contextPath}/mostrarGarages/${cliente.id}/${cliente.nombre}">Hacer reserva</a></p>
		  	<p><a href="${pageContext.request.contextPath}/mostrarAutosClientes/${cliente.id}">Mis autos<a></p>
		  	<p><a href="">Últimos movimientos<a></p>
		  </div>
		  <div class="container offcanvas-footer">
    		<p>Configuracion</p>
  			</div>
		</div>
		    <div class="collapse navbar-collapse" id="navbarSupportedContent">
		      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
		        <li class="nav-item">
<<<<<<< HEAD
		          <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/home/${cliente.id}">Home</a>
=======
		          <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/validar-login/">Home</a>
>>>>>>> branch 'master' of https://github.com/natavila/proyecto-garage.git
		        </li>
		         <li class="nav-item">
		          <a class="nav-link" href="${pageContext.request.contextPath}/datosCliente/${cliente.id}">Mi perfil</a>
		        </li>
		        <li class="nav-item">	       
		          <a class="nav-link" href="#">Garages</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="${pageContext.request.contextPath}/cerrarSesion">Cerrar Sesion</a>
		        </li>
		      </ul>
		    </div>
		  </div>
		</nav>	
</body>
</html>