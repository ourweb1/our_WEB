<%@ page language="java" contentType="text/html;charset=GB18030" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>二手信息发布平台</title>
	<link rel="stylesheet" href=".\css\bootstrap.min.css">
	<script src=".\js\jquery-1.11.2.js"></script>
	<script src=".\js\bootstrap.min.js"></script>
	<script>
	function chooseMajor(){
		var choice=document.form1.department;
		var chooseMajor=document.form1
		var index=0;
		// alert(choice.options[1].value);
		var i;
		for(i=1; i<choice.options.length;i++){
			if(choice.options[i].selected){
				// alert(choice.options[i].value);
				// choice.options[i].className="show";
				index=i;
			}
			else{
				// choice.options[i].className="hidden"
			}
		}
		if(index==0){
			document.getElementById("major_00").disabled="true";
			document.getElementById("major_00").className="form-control show"
		}
		else{
			document.getElementById("major_00").disabled="true";
			document.getElementById("major_00").className="form-control hidden"
		}		
		for(i=1; i<choice.options.length; i++){
			var name="major_"
			if(i<10){
				name=name+"0"+i;
			}
			else{
				name=name+i;
			}
			if(i==index){
				document.getElementById(name).className="form-control show"
				document.getElementById(name).disabled=false
			}
			else{
				document.getElementById(name).className="form-control hidden"
				document.getElementById(name).disabled=true
			}
		}

	}
	</script>
	<style>
	body{background-color:rgb(240,240,240);}
	.row{padding:100px;}
	.book-thumbnail{margin-bottom:20px;}
	.btn-detail{width:49%;}
	.caption-detail{text-align:center; font-weight:bold;}
	a.signin{float:right;background-color:#222222;color:#9d9d9d;font-size:20px;height:50px;line-height:50px;padding-right:20px; text-align:center;text-decoration:none;}
	a.signin:visited{color:#e0e0e0;}
	a.signin:hover{color:#ffffff;}
	a.signin:active{color:#ffffff;}
	div.body_left.col-md-2{position:fixed; top:100px; left:85px;}
	form#form1{
		width:300px;
		margin:0 auto;
	}
	h2{
		text-align:center;
		font-weight:bold;
	}
	label{margin-left:-25px;}
	.keyword{padding:0px;}
	.price.form-control{padding:0px; width:430px; margin-left:15px; margin-right:15px; position:relative;}
	/*input.form-control{padding-right:30px; margin-right:30px;}*/
	</style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="navbar-header">
		<a href="" class="navbar-brand">二手商品</a>
	</div>
	<div>
		<ul class="nav navbar-nav" >
			<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown">旧书<b class="caret"></b>
				</a>
				<ul class="dropdown-menu">
					<li><a href="#">计算机</a></li>
					<li><a href="#">考研</a></li>
					<li><a href="#">出国</a></li>
				</ul>
			</li>
			<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown">生活用品<b class="caret"></b>
				</a>
				<ul class="dropdown-menu">
					<li><a href="#">衣服</a></li>
					<li><a href="#">化妆品</a></li>
					<li><a href="#">杂货</a></li>
				</ul>
			</li>
			<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown">电子产品<b class="caret"></b>
				</a>
				<ul class="dropdown-menu">
					<li><a href="#">电脑</a></li>
					<li><a href="#">手机/ipad</a></li>
					<li><a href="#">剃须刀</a></li>
				</ul>
			</li>
			<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown">家居用品
				</a>
			</li>
			<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown">电脑配件
				</a>
			</li>
			<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown">影视游戏
				</a>
			</li>
			<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown">其他
				</a>
			</li>
		</ul>
		<!-- <a href="#Login" data-toggle="modal" class="signin">登录</a> -->
		<a href="login.jsp" class="signin">登录</a>
		<a href="register.jsp" class="signin">注册</a>
	</div>
</nav>

<!-- <div class="container"> -->
	<div class="row">
		<div class="body_left col-md-2" style="background-color:#eee" >
			<!-- <img src="./image/001.jpg" width="100%" alt=""> -->
			<div class="panel panel-info">
				<!-- <div class="panel-heading">
					<h3 class="panel-title">简介</h3>
				</div> -->
				<div class="panel-body">
				<p class="text-center lead" style="font-weight:bolder ">你可以在这里发布闲置的二手信息</p>
				<p class="text-muted text-center lead" style="font-weight:bold">点击下面的按钮尝试一下</p>
				<!-- <button class="btn btn-info btn-block btn-lg" data-toggle="modal" data-target="#newBook">我要卖书</button> -->
				<button class="btn btn-info btn-block btn-lg" onclick="javascript:window.location.href='publish.jsp'">发布</button>
			<!-- <p class="text-info">本行内容带有一个 info class</p> -->
				</div>
			</div>
		</div>
		<div class="col-md-10" style="margin-left:240px">
			<div class="rowtest">
			<c:forEach items="${goods}" var="g">
			   <div class="col-sm-6 col-md-3 book-thumbnail">
				    <div class="thumbnail">
				    	<img src="${g.imgSrc} }" alt="通用的占位符缩略图">
			    	</div>
			    	<div class="caption">
			    		<h3 class="caption-detail">${g.name}</h3>
			    		<p>${g.content}</p>
			    		<button class="btn btn-info btn-detail" onclick="location='/goodhome/detail!view.action?id=${g.id}'">详情</button>
			    		<button class="btn btn-primary btn-detail" disabled>收藏</button>
			    	</div>
			   </div>
			</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>