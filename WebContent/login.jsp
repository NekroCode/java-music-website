<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Login</title>
	<%@ include file="../WEB-INF/jspf/head.jspf" %>
</head>
<body>
	<%@ include file="../WEB-INF/jspf/header.jspf" %>
	<p>Login</p>
	<form action="Login" method="POST">
		<label for="">Username:</label>
		<input type="text" name="username">
		<label for="">Password:</label>
		<input type="password" name="password">
		<input type="submit" value="Login">
	</form>
	<%= request.getAttribute("login_logger") %>
	<%@ include file="../WEB-INF/jspf/footer.jspf" %>
</body>
</html>