<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
	<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/js/picture.js"></script>
  </head>
  
  <body>
  	<div id="img">
  	<c:if test="${product.pictures!=null && fn:length(product.pictures)>0 }" >
  		<span style="display: none;" id="sum">${fn:length(product.pictures) }</span>
  		<c:forEach items="${product.pictures }" var="pic" varStatus="status">
  			<img id="img${status.index }" alt="${pic.picName }" src="/file/${pic.picPath }" 
  				<c:if test="${status.index!=0 }">style="display: none;"</c:if> />
  		</c:forEach>
  		<br>
  		<span style="text-align: left;" onclick="beforePicture()">上一张</span>
  		<span style="text-align: right;" onclick="nextPicture()">下一张</span>
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
