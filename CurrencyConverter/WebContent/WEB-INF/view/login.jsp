<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>


<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
<title>Login Page</title>
<style>
	
.error {
	width: 20%
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}


</style>
</head>
<body onload='document.loginForm.username.focus();'>

	<h2>Currency Converter</h2>

	<h3>Login</h3>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if> 
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if> 

		<form name='loginForm'
		  method="POST" action="${contextPath}/login">

		<table>
			<tr class="">
				<td>Email:</td>
				<td><input type='text' name='username'></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
				  value="submit" /></td>
			</tr>
		  </table>

		  <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		
		</form>
		
	<a href="${contextPath}/register/form">register</a>

</body>
</html>