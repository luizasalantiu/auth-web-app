<%-- <jsp:forward page="/jsp/login.jsp"/> --%>

<!-- TODO move this outside jsp folder? -->

<%@ include file="../header.jsp" %>
<ul>
	<li><a href="secured/page1.jsp">Page 1</a></li>
	<li><a href="secured/page2.jsp">Page 2</a></li>
	<li><a href="secured/page3.jsp">Page 3</a></li>
</ul>


<%-- <%
//allow access only if session exists
String user = null;
if(session.getAttribute("user") == null){
    response.sendRedirect("login.jsp");
}else user = (String) session.getAttribute("user");
String userName = null;
String sessionID = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
    if(cookie.getName().equals("user")) userName = cookie.getValue();
    if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
}
}else{
    sessionID = session.getId();
}
%>
<h3>Hi <%=userName %>, Login successful. Your Session ID=<%=sessionID %></h3>
<br>
User=<%=user %>
<br> --%>