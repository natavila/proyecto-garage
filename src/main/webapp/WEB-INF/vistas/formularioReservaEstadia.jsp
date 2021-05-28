<<<<<<< HEAD
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
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous"/>
<title>Billetera</title>
</head>
<body>
<div class="container">
<form:form action="${pageContext.request.contextPath}/realizarReservaEstadia/${garage.id}" method="GET" modelAttribute="ticket">

<h2>Fecha Desde</h2>
<form:input type="date" name="fechaDesde" path="fechaDesde"/>
<h2>Fecha Hasta</h2>
<form:input type="date" name="fechaHasta" path="fechaHasta"/><br>

<button type="submit" class="btn btn-primary">Calcular</button>
</form:form>

</div>
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
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous"/>
<title>Billetera</title>
</head>
<body>
<div class="container">
<form:form action="${pageContext.request.contextPath}/realizarReservaEstadia/${garage.id}" method="GET" modelAttribute="ticket">

<h2>Fecha Desde</h2>
<form:input type="date" name="fechaDesde" path="fechaDesde"/>
<h2>Fecha Hasta</h2>
<form:input type="date" name="fechaHasta" path="fechaHasta"/><br>

<button type="submit" class="btn btn-primary">Calcular</button>
</form:form>

</div>
</body>
>>>>>>> b7f08d3865a369885f4ff345531403514a4216cb
=======
=======
>>>>>>> branch 'ramaNataly' of https://github.com/natavila/proyecto-garage.git
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous"/>
<title>Billetera</title>
</head>
<body>
<div class="container">
<form:form action="${pageContext.request.contextPath}/realizarReservaEstadia/${garage.id}" method="GET" modelAttribute="ticket">

<h2>Fecha Desde</h2>
<form:input type="date" name="fechaDesde" path="fechaDesde"/>
<h2>Fecha Hasta</h2>
<form:input type="date" name="fechaHasta" path="fechaHasta"/><br>

<button type="submit" class="btn btn-primary">Calcular</button>
</form:form>

</div>
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
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous"/>
<title>Billetera</title>
</head>
<body>
<div class="container">
<form:form action="${pageContext.request.contextPath}/realizarReservaEstadia/${garage.id}" method="GET" modelAttribute="ticket">

<h2>Fecha Desde</h2>
<form:input type="date" name="fechaDesde" path="fechaDesde"/>
<h2>Fecha Hasta</h2>
<form:input type="date" name="fechaHasta" path="fechaHasta"/><br>

<button type="submit" class="btn btn-primary">Calcular</button>
</form:form>

</div>
</body>
>>>>>>> refs/remotes/origin/SantosGaston
<<<<<<< HEAD
>>>>>>> e61c56b ge remote-tracking branch 'origin/SantosGaston' into ramaNataly
=======
>>>>>>> branch 'ramaNataly' of https://github.com/natavila/proyecto-garage.git
</html>