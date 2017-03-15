<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>产品输入页</title>
    
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">
	<script type="text/javascript" src="<%=path%>/laydate-master-141126210932/laydate.js"></script>
	<script type="text/javascript">
		laydate.skin('molv');
	</script>
  </head>
  
  <body>
    <div id="global">
    	<%--DispatcherServlet接收所有请求，去掉.action --%>
    	<form:form commandName="product" action="product_save.do" method="post" enctype="multipart/form-data">
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
    				<form:input id="productionDate" path="productionDate" cssClass="laydate-icon" 
    					onclick="laydate({formate: 'yyyy-MM-DD', max: laydate.now()})" tabindex="4"/>
    			</p>
    			<p>
    				<label for="picture">Picture:</label>
    				<input type="file" name="file" multiple="multiple"/>
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
