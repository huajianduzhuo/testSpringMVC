<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
  	<div id="img">
  	<c:if test="${product.picPath!=null }">
  		<img alt="${product.name }" src="/file/${product.picPath }">
  	</c:if>
  	</div>
    <div id="global">
    	<h4>${message }</h4>
   		<fieldset>
   			<legend>Details</legend>
   			<p>
   				<label for="category">Category: </label>${product.category.name }<br>
   			</p>
   			<p>
   				<label for="name">Product Name: </label>${product.name }<br>
   			</p>
   			<p>
   				<label for="description">Description: </label>${product.description }<br>
   			</p>
   			<p>
   				<label for="price">Price: </label>$${product.price }<br>
   			</p>
   			<p>
   				<label for="productionDate">ProductionDate: </label>
   				<fmt:formatDate value="${product.productionDate }" pattern="yyyy-MM-dd"/><br>
   			</p>
   		</fieldset>
    </div>
  </body>
</html>
