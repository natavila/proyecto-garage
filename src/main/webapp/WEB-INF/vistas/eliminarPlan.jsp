<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Eliminar plan</title>
</head>
<body>

	<form:form action="eliminarPlan" method="POST" modelAttribute="plan"
		class="row g-3">
		<h1 class="mt-3">Eliminacion de plan</h1>
		<div class="col-md-6">
			<label for="nombre" class="form-label control-label">Nombre
				de plan</label>
			<form:input type="text" class="form-control" id="nombre"
				path="nombre" />

			<button type="submit" class="btn btn-primary control-label mb-3"  href="${pageContext.request.contextPath}/homeAdmin/${plan.nombre}" >Eliminar</button>
		</div>
		</form:form>
</body>
</html>