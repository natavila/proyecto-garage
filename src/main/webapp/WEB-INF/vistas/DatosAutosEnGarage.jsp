<<<<<<< HEAD
<<<<<<< Upstream, based on branch 'master' of https://github.com/natavila/proyecto-garage.git
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
=======
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
>>>>>>> b7f08d3865a369885f4ff345531403514a4216cb
=======
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
=======
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
>>>>>>> refs/remotes/origin/SantosGaston
>>>>>>> e61c56b ge remote-tracking branch 'origin/SantosGaston' into ramaNataly
</html>