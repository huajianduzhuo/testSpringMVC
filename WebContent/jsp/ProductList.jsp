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
    
    <title>产品列表页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">
	<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/js/product.js"></script>

  </head>
  
  <body>
  	<div id="main">
	    <div id="left">
	    	<ul id="catUl">
	    		<c:forEach items="${categorys }" var="cat">
	    			<li value="${cat.id }" onclick="catSelect(this.value,this)" onmouseover="limouseover(this)" onmouseout="limouseout(this)">${cat.name }</li>
	    		</c:forEach>
	    	</ul>
	    </div>
	    <div id="top">
	    	<input type="hidden" id="cat_id" name="cat_id" value="-1" />
	    	<input type="text" id="productName" name="productName" />
	    	<input type="button" id="queryBtn" value="查  询" />
	    </div>
	    <div id="proList">
	    	<table id="proListTable" border="0" cellpadding="0" cellspacing="0">
	    		<thead>
	    			<tr>
		    			<th style="width: 180px;">商品名称</th>
		    			<th style="width: 300px;">描述</th>
		    			<th>价格</th>
	    			</tr>
	    		</thead>
	    		<tbody id="proListTableBody">
	    		</tbody>
	    	</table>
	    </div>
    </div>
  </body>
</html>
