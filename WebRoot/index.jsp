<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String cxt = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JFinal-OAuth2.0</title>
</head>
<body>
<a href="<%=cxt%>/oauth/token.jsp">Token</a>
<a href="<%=cxt%>/oauth/password.jsp">Password</a>
<a href="<%=cxt%>/oauth/client.jsp">Client</a>
</body>
</html>