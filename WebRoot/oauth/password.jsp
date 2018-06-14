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
username : <input type="text" value="" name="username"/> <br/>
password :<input type="text" value="" name="password"/> <br/>
client_id :　<input type="text" value="" name="client_id"/> <br/>
client_secret : <input type="text" value="" name="client_secret"/>
<input type="hidden" value="stateasadas" name="state"/>
<input type="submit" value="token"/>
</form>
</body>
</html>