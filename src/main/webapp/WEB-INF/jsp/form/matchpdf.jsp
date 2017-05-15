 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="formbuilder" uri="http://formbuilder.com/formbuilder"%>

<html>
<head>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" />
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<style>
  .left {width:30%; float: left; margin: 1em;}
  .right {width: 30%; float: left; margin: 1em;}
  .yellow {background: yellow;}
</style>
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
<body dropzone="">

<div class="row">
<div class="col-md-offset-0 col-md-6">

	<h1 align=center> Match online form with PDF file</h1>

	<H2 align=center>FORM : ${form.name}</H2>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h4 class="panel-title">PAGE ${param.pageNum}</h4>
		</div>
		<div class="panel-body">
			<c:choose>
				<c:when test="${empty questionsPage}">
					<h3 class="text-center">There is no question on this page.</h3>
				</c:when>
				<c:otherwise>
				<table border="1" >
				<thead> <tr><th> Question id </th> <th> Question </th> <th> Answer </th></tr>
					<c:forEach items="${questionsPage}" var="question">
						 	<tr><td><span> ${question.getId()} </span></td>
						 	<td> ${question.getDescription()}</td>
						  <td><span> ${question.getAnswers()} </span></td></tr>
					</c:forEach>
					</table>
				</c:otherwise>
			</c:choose>
			
			<form:form modelAttribute="pdffield"  class="pdffield" >
			
			<table style="bodrder:1px">
			<c:forEach items="${allPDF}"  var="field">
			<tr><td><label> ${field.getName()} </label></td>
			<td> <form:hidden path="name" id="drop" value="${field.getName()}" />
			<td> <form:input path="questionId" id="drop"  /> </td></tr>
			</c:forEach>
			 
			</table>
			<input type="submit" name="submit">
			<input type="hidden" value="${pdfname}" />
			</form:form> 
		</div>
	</div>

	<div class="text-center">
		<nav aria-label="Page navigation">
			<ul class="pagination">
				<li><a href="#" aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>
				<c:forEach begin="1" end="${form.totalPages}" step="1" var="i">
					<c:choose>
						<c:when test="${param.pageNum eq i }">
							<li class="active"><a href="" onclick="return false">${i} </a></li>
						</c:when>
						<c:otherwise>
							<li><a href="viewPage.html?id=${param.id}&pageNum=${i}">${i} </a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>
		</nav>
	</div>
</div>

<!--   ################## the uploaded pdf from server #################-->

<%-- <div class="col-md-offset-6 col-md-6">

<H2 align=center>FORM : </H2>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h4 class="panel-title">PAGE ${param.pageNum}</h4>
		</div>
		
		
		<div class="panel-body">
		<form action="matchpdf.html" method="post" >
		<table>
			<c:forEach items="${fields}"  var="field">
			  <label><c:out value="${field.getFullyQualifiedName()}" /></label>
			   <c:if test =  "'${field.getFieldType()}' == 'Tx'"> 
			 <input id="drop" name="answers"  type="text" />
			  </c:if> 
			 <c:if test="'${field.getFieldType() == 'Btn'}">
			 <input id="drop" name="answers"  type="button" />
			 </c:if> 
			 
			 <c:choose>
    			<c:when test="${field.getFieldType()=='Tx'}">
        			<input id="drop" name="answers" draggable='true' type="text" />
        			<br />
    			</c:when> 
    			<c:when test="${field.getFieldType() =='Btn'}">
    			<input id="drop" name="answers"  type="button" />
    			</c:when>   
    			<c:otherwise>
        			<br />
    				</c:otherwise>
					</c:choose>
					<br />
			
			</c:forEach>
			</table>
			<input type="submit" name="submit">
			</form> 
		</div>
	</div>

	<div class="text-center">
		<nav aria-label="Page navigation">
			<ul class="pagination">
				<li><a href="#" aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>
				<c:forEach begin="1" end="${form.totalPages}" step="1" var="i">
					<c:choose>
						<c:when test="${param.pageNum eq i }">
							<li class="active"><a href="" onclick="return false">${i} </a></li>
						</c:when>
						<c:otherwise>
							<li><a href="viewPage.html?id=${param.id}&pageNum=${i}">${i} </a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>
		</nav>
	</div>
</div> --%>
</div>

</body>
</html> 