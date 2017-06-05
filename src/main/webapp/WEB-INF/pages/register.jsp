<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>
<!DOCTYPE html lang="en">
<html>
<head>
<title>Register Page</title>
</head>
<body onload='document.loginForm.username.focus();'>

	<h1>Spring Security Register Form (Database Authentication)</h1>

	<div id="login-box">

		<h3>Register</h3>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
		<c:if test="${same password}">
		    <div class="same">${same}</div>
		</c:if>


		<form name='registerForm' action="<c:url value='/register' />" method='POST'>

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
					<td>Confirm password:</td>
					<td><input type='password' name='cpassword' /></td>
				</tr>
				<tr>
					<td colspan='2'><input name="submit" type="submit"
						value="submit" /></td>
				</tr>
			</table>

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

		</form>
	</div>
<scipt type="application/javascript">
    if(
</script>
</body>
</html>
