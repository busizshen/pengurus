<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Login Page</title>
<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}

body {
	text-align: center;
}

div.container {
	border: 1px solid;
	border-color: #99BBE8;
	background-color: #DFE8F6;
	position: relative;
	display: inline-block;
	margin: 80px;
}

div.logo {
	height: 120px;
	width: 400px;
	background: url('resources/images/logo.png') no-repeat;
	border-bottom: 1px solid;
	border-color: #99BBE8;
}

td {
	text-align: left;
	font: normal 12px tahoma, arial, helvetica, sans-serif;
}
</style>

</head>
<body onload='document.f.j_username.focus();'>
	<div class="container">
		<div class="logo">
		</div>
		<form style="margin: 15px;" name='f' action="<c:url value='j_spring_security_check' />"
			method='POST'>
			<table style="margin: 0px auto;">
				<tr>
					<td>User:</td>
				</tr>
				<tr>
					<td><input type='text' name='j_username' value=''></td>
				</tr>
				<tr>
					<td>Password:</td>
				</tr>
				<tr>
					<td><input type='password' name='j_password' /></td>
				</tr>
				<tr>
					<td style='text-align: right'><input name="submit"
						type="submit" value="login" /></td>
				</tr>
			</table>

		</form>
	</div>
	<c:if test="${not empty error}">
		<div class="errorblock">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
</body>
</html>
