<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>产品输入页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/main.css">

  </head>
  
  <body>
    <div id="global">
    	<c:if test="${requestScope.errors != null }">
    		<div id="errors">
    			Error(s)!
    			<ul>
    				<c:forEach var="error" items="${requestScope.errors }">
    					<li>${error }</li>
    				</c:forEach>
    			</ul>
    		</div>
    	</c:if>
    	<%--DispatcherServlet接收所有请求，去掉.action --%>
    	<form action="product_save" method="post">
    		<fieldset>
    			<legend>Add a product</legend>
    			<p>
    				<label for="name">Product Name: </label>
    				<input type="text" id="name" name="name" tabindex="1" value="${form.name }" />
    			</p>
    			<p>
    				<label for="description">Description: </label>
    				<input type="text" id="description" name="description" tabindex="2" value="${form.description }" />
    			</p>
    			<p>
    				<label for="price">Price: </label>
    				<input type="text" id="price" name="price" tabindex="3" value="${form.price }" />
    			</p>
    			<p id="buttons">
    				<input id="reset" type="reset" tabindex="4" value="reset" />
    				<input id="submit" type="submit" tabindex="5" value="Add Product" />
    			</p>
    		</fieldset>
    	</form>
    </div>
  </body>
</html>
