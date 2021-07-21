<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous"/>
	    <link href="css/estilos.css"/>
	    <script src="js/jsPDF/dist/jspdf.min.js"></script>
	    <script src="js/jquery.min.js"></script>
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
			<!-- jQuery library -->
<script src="js/jquery.min.js"></script>

<!-- jsPDF library -->
<script src="js/jsPDF/dist/jspdf.min.js"></script>

<meta charset="ISO-8859-1">
<title>Tickets</title>
</head>
<body>
<div class="container-fluid col-12">
<%@ include file="header.jsp" %>
</div>
	<div class="container col-6">
		<div class="border mt-3 mb-3 shadow p-3 mb-5 bg-body rounded">
		<h3 style="text-align:center;"><strong>¡Escanea el código QR para poder ingresar al garage!</strong></h3>
		</div>
	</div>
	
	
	<div class="container col-4">
	
	<div id="text" >
		<table class="table table-hover">
		<h3>Tickets</h3>
	
	  <c:forEach var="estacionamiento" 
	             items="${estacionamiento}"
	             varStatus="status">
	  <thead>
	    		
	       		<tr> <th scope="col">Fecha de operación</th> <td><b>${estacionamiento.fechaOperacion}</b></td> </tr>
	       		<tr> <th scope="col">N° Ticket</th> <td><b>${estacionamiento.id}</b></td> </tr>
	       		<tr> <th scope="col">Patente</th><td><b>${estacionamiento.auto.patente}</b></td> </tr>
                <tr> <th scope="col">Garage</th><td><b>${estacionamiento.garage1.nombre}</b> </tr>
                <tr> <th scope="col">Localidad</th><td><b>${estacionamiento.garage1.localidad}</b></td> </tr>
                <tr> <th scope="col">Monto pagado</th> <td><b>$${estacionamiento.precioAPagar}</b> </td> </tr>
                 <th scope="col"></th> <td><img src="<%=request.getContextPath()%>/imagenes/${estacionamiento.imagenQR}"> </td> 
	  </thead>
	  <tbody>
	                    
	                             
	            </c:forEach>
	  </tbody>
	 
	</table>
	
</div> 


	<c:if test="${empty estacionamiento}">
		<div class="alert alert-warning" role="alert">
  			${mensaje}
		</div>
	</c:if>
	
	<div class="container-fluid col-12">
	 <button  class ="btn btn-dark mb-3" role="button" id="btn">Imprimir Ticket</button>
	 <a class="btn btn-dark mb-3" role="button" href="javascript:history.back()"> Volver</a>
	</div>
	
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.0.272/jspdf.debug.js"></script>
<script src="custom.js"></script> 

<script type="text/javascript">
  

  $(document).on('click','#btn',function(){
let pdf = new jsPDF('2','mm',[297, 210]);


let section=$('body');



let page= function() {
	
    
	pdf.save('ticket.pdf');
};

pdf.addHTML(section,page);

})

</Script>
</div>
</body>

</html>