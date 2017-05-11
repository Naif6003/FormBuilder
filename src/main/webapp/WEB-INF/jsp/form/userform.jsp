<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
table, th, td {

border: 1px solid black;

}
</style>


<table>
<thead> <tr><th> User name </th><th> Forms assign to user </th></tr>
</thead>
<tbody>
	<c:forEach items="${users}" var="user">
	<c:if test="${user.getRole() == 'ROLE_USER'}">
	<tr>
		<td><c:out value="${user.getFirstName()}" /></td>
		<td><c:out value="${user.getForms()}" /></td>
	</tr>
	</c:if>
	</c:forEach>
</tbody>



</table>