<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:url value="/" var="docroot" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Form View</title>
</head>
<body>
<table border=1>
<tr><th>ID</th><td>${form.id}</td></tr>
<tr><th>Name</th><td>${form.name}</td></tr>
<tr><th>Description</th><td>${form.description}</td></tr>
<tr><th>Available</th><td>
<c:choose>
  <c:when test="${form.available==true}">
  <input type="checkbox" name="available" value="true" checked/> Available
  <input type="checkbox" name="available" value="false"/> Unavailable
  </c:when>
  <c:otherwise>
  <input type="checkbox" name="available" value="true" /> Available
  <input type="checkbox" name="available" value="false" checked/> Unavailable
  </c:otherwise>
</c:choose>
<tr><th>User</th><td><a href="user/view/${form.user.id}.html">${form.user.id}</a></td></tr>
<tr><th>Page Number</th><td>Total: ${fn:length(form.pages)} 
<c:forEach begin="1" end="${fn:length(form.pages)}" varStatus="loop">
	<a href="${docroot}form/page_view.html?id=${form.id}&p=${loop.index}">${loop.index}</a>
</c:forEach>
</td></tr>

</table>
<a href="${docroot}form/edit_form.html?id=${form.id}">Edit Form</a><br/>
<a href="${docroot}form/add_page.html?id=${form.id}">Add Page</a><br/>
</body>
</html>