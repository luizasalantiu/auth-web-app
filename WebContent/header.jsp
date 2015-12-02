<%
	if (session.getAttribute("username") != null) {
%>
Hola ${username}!
<a href="${pageContext.request.contextPath}/logout">Logout</a>
<%
	} else {
%>
<a href="login">Login</a>
<%
	}
%>