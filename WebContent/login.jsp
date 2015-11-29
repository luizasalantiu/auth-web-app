<html>
<head>
<title>Login page</title>
<!-- <link rel="stylesheet" href="css/style.css" /> -->
</head>
<body>
	<div class="content">
		<h1>Login</h1>
		<form id="loginForm" action="login" method="post">
			<label>Username:</label> <input type="text" name="${login_attr}" />
			<label>Password:</label> <input type="password" name="${password_attr}" /> 
			<input type="submit" value="Login" />
		</form>
		
	<%-- TODO save name parameters as constants in application	 <input type="password" name="${password_var}"/> --%>
	<!-- 	 TODO add password_confirm -->
		${message}
<%-- 
		<form method="post" id="loginForm">
			<input type="hidden" name="command" value="login" /> 
			<label
				for="${login_var}"><fmt:message
					key="auth.page.login_form.login" /> :</label> <input type="text"
				name="${login_var}" /> <label for="${password_var}"><fmt:message
					key="auth.page.login_form.password" />:</label> <input type="password"
				name="${password_var}" /> <input type="submit"
				value="<fmt:message key="auth.page.login_form.submit"/>" />
		</form> --%>
	</div>
</body>
</html>