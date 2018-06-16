<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Password</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/oauth2/secure_access_token" method="post">
<input type="hidden" value="password" name="grant_type"/>
username : <input type="text" value="name" name="username"/> <br/>
password :<input type="text" value="pwd" name="password"/> <br/>
state :<input type="text" value="stateasadas" name="state"/><br/>
scope :<input type="text" value="sssssssope" name="scope"/><br/>
<input type="submit" value="token"/>
</form>
</body>
</html>