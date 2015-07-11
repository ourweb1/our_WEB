<%@ page language="java" contentType="text/html;charset=GB18030"
	pageEncoding="GB18030"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>登录</title>
<link rel="stylesheet" href="..\static\css\bootstrap.min.css">
<script src="..\static\js\jquery-1.11.2.js"></script>
<script src="..\static\js\bootstrap.min.js"></script>

<style>
.signin {
	width: 220px;
	margin: auto;
	margin-top: 200px;
}

input {
	margin-bottom: 10px;
}

h2 {
	text-align: center;
}

button {
	width: 49%;
}
</style>
</head
<body>
	<div class="container">
		<form action="<%=basePath%>user/login" class="signin" method="post">
			<h2>登录</h2>
			<c:if test="${redirectUrl != null}">
				<input type="hidden" name="redirectUrl" value="${redirectUrl}"/>
			</c:if>
			<input type="text" name="name" class="form-control" placeholder="用户名" style="margin-bottom:10px"required autofocus value="${user.name}">
			<input type="password" name="password" class="form-control" placeholder="Password" style="margin-bottom:10px"required>
			<div class="checkbox">
		          <label>
		            <input type="checkbox" value="true" name="autoLogin"> 自动登录
		          </label>
        	</div>
			<button class="btn btn-success submit">登录</button>
			<button class="btn btn-default">忘记密码？</button>
		</form>
	</div>
</body>

</html>