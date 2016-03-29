<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<html>
<head>
<title>Login page</title>
</head>
<body>
<p>LOGIN PAGE</p>
	<form:form>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Name</th>
					<th>Age</th>
				</tr>
			</thead>		
			<c:forEach var="u" items='${users}'>
				<tr>
					<td> ${u.name }</td>
					<td> ${u.age }</td>
				</tr>
			</c:forEach>
		
		</table>	
	</form:form>


</body>
</html>