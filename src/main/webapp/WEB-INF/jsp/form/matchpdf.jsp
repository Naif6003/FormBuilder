 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="formbuilder" uri="http://formbuilder.com/formbuilder"%>
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
<div class="col-md-offset-3 col-md-6">

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
				<table class="table table-striped table-bordered-2" style="text-align= center; vertical-align:middle;" >
				<thead> <tr><th> Question id </th> <th> Question </th> <th> Answer </th></tr> </thead>
					<c:forEach items="${questionsPage}" var="question">
						 	<tr><td><span> ${question.getId()} </span></td>
						 	<td> ${question.getDescription()}</td>
						 	<c:if test="${question.getTagAttribute().getType() == 'text' }">
						 	<td><span> ${question.getAnswers()} </span></td>
						 	</c:if>
						  <c:if test="${question.getTagAttribute().getType() == 'checkbox' }">
						  <c:forEach items="${choices}" var="choice">
						  <td><span> ${choice.getSelections().toString()} </span></td>
						  </c:forEach>
						  </c:if></tr>  
	
					</c:forEach>
					</table>
				</c:otherwise>
			</c:choose>
			</div>

			
			
			
			<div>
			<form:form modelAttribute="pdffield"  class="pdffield" >
			<table class="table table-striped table-bordered-2" style="text-align= center; vertical-align:middle;">
			
		<c:forEach items="${fields}"  var="field">
			
			<tr>
				
			  
			  <c:if test="${field.getFieldType() == 'Tx' || field.getFieldType() == 'text'}">
			  <td><label> ${field.getFullyQualifiedName()} :</label></td>
					<td><form:input path="questionId" id="drop" type="text"/></td>
				</c:if>

			 <form:hidden path="name" id="drop" value="${field.getFullyQualifiedName()}" />
				
			 <c:if test="${field.getFieldType() == 'Btn' || field.getFieldType() == 'checkbox'}">
			  <td><label> ${field.getPartialName()} :</label></td> 
			 		 <td><form:input class="checkbox" path="questionId"  id="drop" type="Checkbox" /></td>

				</c:if>
				</c:forEach>
			</table>
			
			<input type="submit" name="submit"  class="btn btn-success btn-raised" >
			<input type="hidden" value="${pdfname}" />
			 
			</form:form>
			</div> 
		</div>
		
<script>

$(".checkbox").each(function () { 
	if ($(this).is(':checked')){
		$(this).val("Off");
	}else{
		$(this).val("Yes");
	}
});
</script>









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
		</div>
		</div>

</body>
</html> 