<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <% String basePath=request.getScheme()+"://"+request.getServerName()+":"+
    request.getServerPort()+request.getContextPath()+"/";
    %>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>我是登录页</h1>
	<form action="user/login.do" method="post">
		username:<input type="text" name="username"><br>
		password:<input type="text" name="password"><br> <input
			type="submit" value="登录">

	</form>
	<a href="/ServletProject/register.html">回到注册</a>
</body>
</html>