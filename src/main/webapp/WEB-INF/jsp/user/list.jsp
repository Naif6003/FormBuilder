<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User list</title>
</head>
<body>
<table border=1>
<tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Operations</th><th>Operations1</th></tr>
<c:forEach items="${users}" var="user">
<tr><td>${user.id}</td><td>${user.firstName}</td><td>${user.lastName}</td>
<td><a href='view.html?id=${user.id}'>View</a></td>
<!-- <td><a href='view/${user.id}.html'>View1</a></td> -->
<td><a href='edit.html?id=${user.id}'>Edit</a></td>
<td><a href='delete.html?id=${user.id}'>Delete</a></td>
</tr>
</c:forEach>
</table>
<a href="add.html">Add new User</a>
</body>
</html>