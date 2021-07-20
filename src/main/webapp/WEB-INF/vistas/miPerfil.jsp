<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous"/>
	    <link href="css/estilos.css"/>
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
<meta charset="ISO-8859-1">
<title>${cliente.nombre} ${cliente.apellido} - (${cliente.email})</title>
</head>
<body>
<%@ include file="header.jsp" %>
	<div class="container">
		<div class="border mt-3 mb-3 shadow p-3 mb-5 bg-body rounded">
		<svg style="margin-left: 43%; margin-top:50px;" id="457bf273-24a3-4fd8-a857-e9b918267d6a" data-name="Layer 1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="200" height="200" viewBox="0 0 698 698"><defs><linearGradient id="b247946c-c62f-4d08-994a-4c3d64e1e98f" x1="349" y1="698" x2="349" gradientUnits="userSpaceOnUse"><stop offset="0" stop-color="gray" stop-opacity="0.25"/><stop offset="0.54" stop-color="gray" stop-opacity="0.12"/><stop offset="1" stop-color="gray" stop-opacity="0.1"/></linearGradient></defs><title>profile pic</title><g opacity="0.5"><circle cx="349" cy="349" r="349" fill="url(#b247946c-c62f-4d08-994a-4c3d64e1e98f)"/></g><circle cx="349.68" cy="346.77" r="341.64" fill="#f5f5f5"/><path d="M601,790.76a340,340,0,0,0,187.79-56.2c-12.59-68.8-60.5-72.72-60.5-72.72H464.09s-45.21,3.71-59.33,67A340.07,340.07,0,0,0,601,790.76Z" transform="translate(-251 -101)" fill="#6c63ff"/><circle cx="346.37" cy="339.57" r="164.9" fill="#333"/><path d="M293.15,476.92H398.81a0,0,0,0,1,0,0v84.53A52.83,52.83,0,0,1,346,614.28h0a52.83,52.83,0,0,1-52.83-52.83V476.92a0,0,0,0,1,0,0Z" opacity="0.1"/><path d="M296.5,473h99a3.35,3.35,0,0,1,3.35,3.35v81.18A52.83,52.83,0,0,1,346,610.37h0a52.83,52.83,0,0,1-52.83-52.83V476.35A3.35,3.35,0,0,1,296.5,473Z" fill="#fdb797"/><path d="M544.34,617.82a152.07,152.07,0,0,0,105.66.29v-13H544.34Z" transform="translate(-251 -101)" opacity="0.1"/><circle cx="346.37" cy="372.44" r="151.45" fill="#fdb797"/><path d="M489.49,335.68S553.32,465.24,733.37,390l-41.92-65.73-74.31-26.67Z" transform="translate(-251 -101)" opacity="0.1"/><path d="M489.49,333.78s63.83,129.56,243.88,54.3l-41.92-65.73-74.31-26.67Z" transform="translate(-251 -101)" fill="#333"/><path d="M488.93,325a87.49,87.49,0,0,1,21.69-35.27c29.79-29.45,78.63-35.66,103.68-69.24,6,9.32,1.36,23.65-9,27.65,24-.16,51.81-2.26,65.38-22a44.89,44.89,0,0,1-7.57,47.4c21.27,1,44,15.4,45.34,36.65.92,14.16-8,27.56-19.59,35.68s-25.71,11.85-39.56,14.9C608.86,369.7,462.54,407.07,488.93,325Z" transform="translate(-251 -101)" fill="#333"/><ellipse cx="194.86" cy="372.3" rx="14.09" ry="26.42" fill="#fdb797"/><ellipse cx="497.8" cy="372.3" rx="14.09" ry="26.42" fill="#fdb797"/></svg>
		<div style="margin-left:37%; margin-top:20px;">
		<h5>Cliente: ${cliente.nombre} ${cliente.apellido}</h5>
		<h5>Email: ${cliente.email}</h5>
		<h5>Localidad: ${cliente.localidad}</h5>
		<h5>Plan contratado: 
		<c:if test="${empty cliente.plan.nombre}">
		No posee plan contratado
		</c:if>
		<c:if test="${not empty cliente.plan.nombre}">
		${cliente.plan.nombre}
		</c:if></h5>
		</div>
		<c:if test="${not empty billetera}">
		<h3 style="text-align:center; margin-top:20px; margin-bottom:50px;">Saldo actual: $${billetera.saldo}</h3>
		</c:if>
		<div class="d-grid gap-2 d-md-flex justify-content-md-end">
		<a class="btn btn-warning justify-content-md-end" href="modificarCliente" role="button">Modificar datos</a>
		</div>
		</div>
		<div style="margin-top:50px; margin-bottom:50px; text-align:center;">
		<a class="btn btn-primary" href="misAutos" role="button">Mis Autos</a>
		<a class="btn btn-primary" href="ticketsCliente" role="button">Mis Tickets</a>
		<a class="btn btn-primary" href="mostrarBilletera" role="button">Mi billetera</a>
		<c:if test="${empty cliente.plan.nombre}">
		<a class="btn btn-primary" href="planes" role="button">Ver planes</a>
		</c:if>
		</div>
		<table class="table table-hover">
		<h3>Últimas reservas</h3>
	  <thead>
	    <tr>
	       		<th scope="col">Fecha de operación</th>
	       		<th scope="col">Patente</th>
                <th scope="col">Garage</th>
                <th scope="col">Localidad</th>
                <th scope="col">Monto pagado</th>  
                <th scope="col">Codigo QR</th>       
	    </tr>
	  </thead>
	  <tbody>
	  <c:forEach var="estacionamiento" 
	             items="${estacionamiento}"
	             varStatus="status">
	                <tr>
	                <td><b>${estacionamiento.fechaOperacion}</b></td> 
	                <td><b>${estacionamiento.auto.patente}</b></td>
	                <td><b>${estacionamiento.garage1.nombre}</b></td>
	                <td><b>${estacionamiento.garage1.localidad}</b></td>
	                <td><b>$${estacionamiento.precioAPagar}</b></td>  
	                <td><img src="<%=request.getContextPath()%>/imagenes/${estacionamiento.imagenQR}"></td>              
	                </tr>	                
	            </c:forEach>
	  </tbody>
	</table>
	</div>
</body>
</html>