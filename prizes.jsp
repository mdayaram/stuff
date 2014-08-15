<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template-top.jsp" />

<h2 align="center"> Redeemable Prizes! </h2>

<c:if test="${user.id == 1}">
<a href="addprize.do">Add Prize</a>
<br><br>
</c:if>

<c:forEach var="prize" items="${prizesList}">
<table width="100%" cellpadding="10px" cellspacing="0px" border="0px">
<tr>
	<td><h2>${prize.name}</h2> <b>Points Required:</b> ${prize.points} </td>
	<td align="center" valign="center" width="250px">
	
		<c:choose><c:when test="${empty user}">
		<a href="login.do?from=prizes.do">login</a> to redeem this prize.
		</c:when><c:otherwise>
		<form method="post" action="redeem.do">
			<input type="hidden" name="id" value="${prize.id}" />
			<input type="submit" name="button" value="Redeem Prize!" />
		</form>
		</c:otherwise></c:choose>
	</td>
</tr><tr>
	<td colspan="2"><p id="item">${prize.action}</p></td>
</tr>
</table>
<br>
<hr>
</c:forEach>

<br><br>
<jsp:include page="template-bottom.jsp" />