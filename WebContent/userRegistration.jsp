<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>User registration</title>
	<%@ include file="../WEB-INF/jspf/head.jspf" %>
</head>
<body>
	<%@ include file="../WEB-INF/jspf/header.jspf" %>
	<form action="UserRegistration" method="POST">
		<label for="">Username:</label>
		<input type="text" name="username">
		<label for="">Password:</label>
		<input type="password" name="password">
		<input type="submit" value="Create Account">
	</form>
	<%= request.getAttribute("registration_logger") %>
	<%@ include file="../WEB-INF/jspf/footer.jspf" %>
</body>
</html>