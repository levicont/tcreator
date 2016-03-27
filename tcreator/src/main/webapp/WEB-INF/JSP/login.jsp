<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>Login page</title>
</head>
<body>
<p>LOGIN PAGE</p>
	<form:form>
		<table>
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