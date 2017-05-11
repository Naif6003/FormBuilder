<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="formbuilder" uri="http://formbuilder.com/formbuilder"%>

<div class="row  col-md-offset-1">
	<div class="col-md-4">
		<div class="panel panel-info">
			<div class="panel-heading">
				<h4 class="panel-title">QUESTION CONTROL</h4>
			</div>
			<div class="panel-body">
				<p class="text-center">Edit the field here.</p>
				<c:if test="${question.type eq 'TEXT'}">
					<formbuilder:editText></formbuilder:editText>
				</c:if>
				<c:if test="${question.type eq 'CHOICE'}">
					<formbuilder:editChoice question="${question }"></formbuilder:editChoice>
				</c:if>
				<c:if test="${question.type eq 'FILE'}">
					<formbuilder:editFile></formbuilder:editFile>
				</c:if>

				<a href="editPage.html?id=${id}&pageNum=${pageNum}"><button type="button" class="btn btn-warning btn-sm">
						<span class="glyphicon glyphicon-arrow-left"></span> RETURN TO PAGE
					</button></a>
			</div>
		</div>
	</div>

	<div class="col-md-7" style="position: fixed; right: 0; height: 500px; overflow: auto;">
		<H3>QUESTION LIVE PREVIEW</H3>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h4 class="panel-title">QUESTION ${question.questionNumber}</h4>
			</div>
			<div class="panel-body">
				<formbuilder:fieldDisplay question="${question }"></formbuilder:fieldDisplay>
			</div>
		</div>
	</div>
</div>

