<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista de planes</title>
</head>
<body>
 <h1> Lista de Planes:</h1>
  <c:forEach var="plan" 
	             items="${planes}"
	             varStatus="status">
	                <tr>
	                    <td><b>${plan.id}</b></td>
	           
	                    <td>${plan.nombre}</td><br>
	                   
	               
	                </tr>
	                
	            </c:forEach>
 

</body>
</html>