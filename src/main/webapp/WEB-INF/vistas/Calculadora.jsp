<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p>Calculadora</p>



<form method="get" action="realizaroperacion">

<div>
Operando 1 
<input type="number" name="operando1">
</div>
<div>
Operando 2
<input type="number" name="operando2">
</div>
<div>


<h1>Elija una Operacion</h1>
</div>
<input type="radio" name="operacion" value="1">suma

<br>

<input type="radio" name="operacion" value="2">resta

<br>

<input type="radio" name="operacion" value="3">multiplicacion
<button type="submit" class="btn btn-default">calcular</button>

</form>

<c:if test="${not empty resulatado}">
	<h4><span>${error}</span></h4>
<h1> El resultado de ${operacion}  ${operando1} y el ${operando2} es ${resulatado} </h1>
      <br>
		        </c:if>

</body>
</html>