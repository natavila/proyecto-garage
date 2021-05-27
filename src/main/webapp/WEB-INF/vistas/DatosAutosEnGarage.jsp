<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head th:replace="layout/layout::head">
    <meta charset="UTF-8">
</head>
<body>
    
<h1> ${nombre} </h1>
<h5><a href="DatosDeUnGaragePorPantalla">Agregar Auto</a></h5>  
        <table border="1">
            <tr>
                <th style="width:  50px;">Id</th>
                <th style="width: 250px;">Patente</th>
                
            </tr>
            <c:forEach var="auto" 
                       items="${autos}"
                       varStatus="status">
                <tr>
                    <td><b>${auto.id}</b></td>
                    <td>${auto.patente}</td>
                    
   				
   					<td> <a href="${pageContext.request.contextPath}/SacarAutoDeGarage/${auto.id}">Eliminar Auto</a> </td>
                </tr>
            </c:forEach>

    <footer th:replace="layout/layout::footer" class="bg-dark"></footer>
</body>
</html>