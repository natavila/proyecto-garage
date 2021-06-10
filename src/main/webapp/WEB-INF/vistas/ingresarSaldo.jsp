<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<title>Ingresar dinero</title>
</head>
<body>
	<div class="container">
	<form:form action="${pageContext.request.contextPath}/procesarSaldo/${cliente.id}" method="POST" modelAttribute="billetera">
	<div class="col-md-6">	
		<label>Ingrese el monto</label>
		<input type="number" name="monto" id="saldo"/>
		</div>
		<div class="col-12">
		    <button type="submit" class="btn btn-primary control-label mb-3">Ingresar</button>
		  </div>
	</form:form>
	</div>
	<c:if test="${not empty error}">
			<div class="alert alert-danger" role="alert">
				<h6>${error}</h6>

			</div>
		</c:if>
</body>
</html>