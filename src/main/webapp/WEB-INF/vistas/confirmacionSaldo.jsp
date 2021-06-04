<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Confirmacion de saldo</title>
</head>
<body>
	<h1>Su nuevo saldo es: ${saldo}</h1>
	
	<c:if test="${not empty mensaje}">
				
				    ${mensaje}
				 				        	        
		        </c:if>
</body>
</html>