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
<div class="row">
<div class="col-md-offset-3 col-md-6">
	
	<div class="panel panel-primary">
		<div class="panel-heading">
				<h4> Generate new PDF </h4>
				</div>
<table class="table table-striped table-bordered-2" style="text-align= center; vertical-align:middle;" >
<thead> <tr><th> User name </th><th> Form ID </th><th> Forms assign to user </th></tr>
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
<br />

<table class="table table-striped table-bordered-2" style="text-align= center; vertical-align:middle;">
<thead> <tr><th> File Name </th></tr> </thead>
<tbody>

<c:forEach items="${files}" var="file">
		<tr><td><span>${file.getName()}</span></td></tr>
	</c:forEach>
	
	
</tbody >
</table>
	<div align=center>
							<form:form action="matchpdf.html" method="get" id="my-awesome-dropzone">
								
								
								<div class="form-group">
								 <label style="color:black;"> Form Id </label>
								 <input id="drop" type="text" name="fileName"  />
								</div>
								
								<div class="form-group">
								<label style="color:black;"> File Name </label>
								<input id="drop" type="text" name="PDF"  />
								</div>
								<br />
								
								<input type="submit" class="btn btn-success btn-raised"/>
							</form:form>
</div>
</div>
</div>
</div>
</body>
</html>