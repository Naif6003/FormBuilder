<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ tag body-content="scriptless"%>

<form:form  modelAttribute="question" class = "form" >
	<div class="form-group">
		<div><label>Description</label></div>			
		<form:input path="description" cssClass="form-control" required="required"/>
	</div>
	
	<button type="submit" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-floppy-disk"></span> SAVE</button>						
</form:form>

