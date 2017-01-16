<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>产品展示页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/main.css">

  </head>
  
  <body>
    <div id="global">
    	<h4>${message }.</h4>
   		<fieldset>
   			<legend>Details</legend>
   			<p>
   				<label for="name">Product Name: </label>${product.name }<br>
   			</p>
   			<p>
   				<label for="description">Description: </label>${product.description }<br>
   			</p>
   			<p>
   				<label for="price">Price: </label>$${product.price }<br>
   			</p>
   		</fieldset>
    </div>
  </body>
</html>
