<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">

  </head>
  
  <body>
    <div id="global">
    	<%--DispatcherServlet接收所有请求，去掉.action --%>
    	<form:form commandName="product" action="product_save.do" method="post">
    		<fieldset>
    			<legend>Add a product</legend>
    			<p>
    				<label for="category">Category: </label>
    				<form:select id="catId" path="category.id" items="${product.categorys }" itemLabel="name" itemValue="id"></form:select>
    			</p>
    			<p>
    				<label for="name">Product Name: </label>
    				<form:input id="name" path="name" tabindex="1"/>
    			</p>
    			<p>
    				<label for="description">Description: </label>
    				<form:input id="description" path="description" tabindex="2"/>
    			</p>
    			<p>
    				<label for="price">Price: </label>
    				<form:input id="price" path="price" tabindex="3"/>
    			</p>
    			<p>
    				<form:errors path="productionDate" cssClass="error"></form:errors>
    			</p>
    			<p>
    				<label for="productionDate">ProductionDate: </label>
    				<form:input id="productionDate" path="productionDate" tabindex="4"/>
    			</p>
    			<p id="buttons">
    				<input id="reset" type="reset" tabindex="5" value="reset" />
    				<input id="submit" type="submit" tabindex="6" value="Add Product" />
    			</p>
    		</fieldset>
    	</form:form>
    </div>
  </body>
</html>
