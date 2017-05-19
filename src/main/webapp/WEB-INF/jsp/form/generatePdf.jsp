 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>s

<h2> ${form.getName()}</h2>

<form:form modelAttribute="formmapping"  class="formmapping" >

<form:select path="userId">
<c:forEach items="${users}" var="user" varStatus="myIndex">
<c:if test="${user.getRole() == 'ROLE_USER'}">
<option> ${user.getFirstName()} </option>  
	 </c:if>
</c:forEach>
</form:select>

<form:select path="pdfName">
<c:forEach items="${files}" var="file" varStatus="myIndex">
<option> ${file.getName()} </option>  
</c:forEach>
</form:select>


<form:select path="appForm">
<c:forEach items="${forms}" var="form" varStatus="myIndex">
<option> ${form.getName()} </option>  

</c:forEach>
</form:select>


<input type="submit"  value="generate"/>
</form:form>

  