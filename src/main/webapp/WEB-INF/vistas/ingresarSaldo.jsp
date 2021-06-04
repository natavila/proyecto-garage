<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ingresar dinero</title>
</head>
<body>
	<form:form action="${pageContext.request.contextPath}/procesarSaldo/${cliente.id}" method="POST" modelAttribute="billetera">
		<label>Ingrese el alias</label>
		<form:input type="text" name="alias" path="alias"/>
		<label>Ingrese el monto</label>
		<input type="number" name="monto" id="saldo"/>
		<div class="col-12">
		    <button type="submit" class="btn btn-primary control-label mb-3">Ingresar</button>
		  </div>
	</form:form>
</body>
</html>