<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a>List Templates</a>
	<c:forEach var="template" items="${templates}">
		
			<li>${template.name}</li>
			
		</c:forEach>
</body>
</html>