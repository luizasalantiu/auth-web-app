
<%
	if (session.getAttribute("username") != null) {
%>
Hola ${username}!
<a href="logout">Logout</a>
<%
	} else {
%>
<a href="login">Login</a>
<%
	}
%>