<html>
<head>
<title>Login page</title>
</head>
<body>
	<div class="content">
		<h1>Login</h1>
		<form id="loginForm" action="login" method="post">
			<label>Username:</label> <input type="text" name="${login_attr}" />
			<label>Password:</label> <input type="password" name="${password_attr}" /> 
			<input type="submit" value="Login" />
		</form>
	</div>
</body>
</html>