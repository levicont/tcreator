<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<title>TCreator page</title>
		<spring:url value="/files/css/main.css" var="mainCSS"/>
		<spring:url value="/files/css/bootstrap-3.3.6-dist/css/bootstrap.min.css" var="bootStrapCSS"/>
		<spring:url value="/files/js/bootstrap-3.3.6-dist/js/bootstrap.min.js" var="bootstrapStrapJS"/>
		<spring:url value="/files/js/jquery-2.2.2.min.js" var="jqueryJS"/>
		
		<script src="${bootstrapStrapJS }"></script>		
		<script src="${jqueryJS }"></script>
		<link rel="stylesheet" href="${bootStrapCSS }" type="text/css">
		<link rel="stylesheet" href="${mainCSS }" type="text/css">		

</head>
<body>
	
		<div id="header" class="page-header" >
			<tiles:insertAttribute name="header" />
		</div>
		<div class="container">
		<div>
			<tiles:insertAttribute name="menu" />
		</div>
		<div>
			<tiles:insertAttribute name="body" />
		</div>
	</div>
	<footer id="footer" class="footer">
		<tiles:insertAttribute name="footer" />
	</footer>
	
</body>
</html>