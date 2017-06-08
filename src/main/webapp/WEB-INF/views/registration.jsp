<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page session="false"%>
<!DOCTYPE html lang="en">
<html>
<head>
<title>Register Page</title>
</head>
<body onload='document.registrationForm.username.focus();'>

	<h1>Spring Security Register Form (Database Authentication)</h1>

	<div id="login-box">

		<h3>Register</h3>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>

		<form name='registrationForm' action="<c:url value='/registration' />" method='POST'>

			<table>
				<tr>
					<td>Username:</td>
					<td><input type='text' name='username'></td>
				</tr>
				<tr>
				    <td>Entire name:</td>
				    <td><input type='text' name='name'></td>
				</tr>
				<tr>
				    <td>Email:</td>
				    <td><input type='text' name='email'></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='password' /></td>
				</tr>
				<tr>
					<td colspan='2'><input name="submit" type="submit"
						value="submit" onclick="register()"/></td>
				</tr>
			</table>

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

		</form>
	</div>
</body>
</html>
