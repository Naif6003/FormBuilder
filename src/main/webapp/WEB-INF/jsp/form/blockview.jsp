<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/" var="docroot" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Page View</title>
</head>
<body>
<a href="${docroot}form/add_item.html?id=${block.id}">Add New Item</a>
<table border=1>
<tr><th>Form: </th><td>${block.page.form.name}</td></tr>
<tr><th>Page: </th><td>${block.page.pageNumber}</td></tr>
<tr><th>Name: </th><td>${block.name}</td></tr>
<tr><th>Description: </th><td>${block.description}</td></tr>
<tr><th>Status</th><td>
<c:choose>
  <c:when test="${block.available==true}">Available</c:when>
  <c:otherwise>Unavailable</c:otherwise>
</c:choose>
</td></tr>
</table>
</body>
</html>