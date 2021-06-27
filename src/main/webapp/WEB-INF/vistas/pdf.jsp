<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Creando pdf</h1>
	<form action="generarPdf" name="formPDF" method="POST">
		<label>Texto:</label>
		<br>
		<textarea name="contenido"></textarea>
	</form>
	
</body>
</html>