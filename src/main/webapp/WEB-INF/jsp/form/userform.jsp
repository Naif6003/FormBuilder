<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<style>
table, th, td {

border: 1px solid black;

}
</style>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" />
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<title> Fill Forms</title>

<script>
$(function(){
    $("span").draggable({
        revert: "invalid"
    });
    $("#drop:empty").droppable({
        classes: {
            "ui-droppable-hover" : "yellow"
        },
        drop: function(event, ui){
          this.value = $(ui.draggable).text();
           $(this).droppable({ disabled: true });
          ui.draggable.parent().remove(); 
          
        }
    });
});

</script>
</head>
<body>

<table>
<thead> <tr><th> User name </th><th> Forms assign to user </th></tr>
</thead>
<tbody>
	<c:forEach items="${users}" var="user">
	<c:if test="${user.getRole() == 'ROLE_USER'}">
	<tr>
		<td><c:out value="${user.getFirstName()}" /></td>
		
		<c:forEach items="${user.getForms()}" var="form">
		<%--  <td><a  href="matchpdf.html?id=${form.getId()}&pageNum=1" ><c:out value="${form.getName()}" /></a></td>  --%>
		<td><span>${form.getId()}</span> </td>
		<td><span>${form.getName()}</span></td>
		</c:forEach>
	</tr>
	</c:if>
	</c:forEach>
</tbody>
</table>


<table>
<thead> <tr><th> file name </th></tr> </thead>
<tbody>


<tr><c:forEach items="${files}" var="file">
		<td><span>${file.getName()}</span></td>
	</c:forEach></tr>
	
	
</tbody>
</table>

<!-- <label> drag and drop the name of the file you want to fill </label>
<input id="drop" type="text" name="fileName"  />

 -->
<form:form action="matchpdf.html" method="get">
<!-- <table>

<thead> <tr> <th> Users </th><th> forms </th><th> PDF files </th> </tr> 
<tbody> -->
<%-- <c:forEach items="${users}" var="user">
	<c:if test="${user.getRole() == 'ROLE_USER'}">
	<tr>
		<td><c:out value="${user.getFirstName()}" /></td>
		
		<td><select name="selectForm">
		<c:forEach items="${user.getForms()}" var="form">
		 <td><a  href="matchpdf.html?id=${form.getId()}&pageNum=1" ><c:out value="${form.getName()}" /></a></td> 
		<option> </option>
		<option> ${form.getName()} </option>
		</c:forEach>
		</select></td>
		
		
		<td><select name="selectPDF">
		<c:forEach items="${files}" var="file">
			<option> </option>
			<option>${file.getName()}</option>
		</c:forEach>
		</select></td>
	</tr>
	</c:if>
	</c:forEach> --%>
	
 <label> drag and drop the name of the file you want to fill </label>
<input id="drop" type="text" name="fileName"  />
<br />

<label> PDF </label>
<input id="drop" type="text" name="PDF"  />
	
<!-- 	
</tbody>
</table> -->
<br />
<input type="submit" />
</form:form>

</body>
</html>