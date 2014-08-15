<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template-top.jsp" />

<h2 align="center"> Available Items </h2>

<c:if test="${empty itemsList}">
<br><br>
	<p align="center">There are no Available Items at this time.</p>
</c:if>

<c:forEach var="item" items="${itemsList}">
<table width="100%" cellpadding="10px" cellspacing="0px" border="0px">
<tr>
	<td width="110px" align="center">
		<a href="view.do?id=${item.id}"><img src="image.do?id=${item.imageId}" width="100px" border="0px" /></a>
	</td>
	<td>
		<p class="item">
		<a href="view.do?id=${item.id}">${item.name}</a> <br />
		<b>Rank:</b> ${rankMap[item.rank]} <br />
		<b>Price:</b> $${item.price} <br />
		<b>Points:</b> ${item.points}<br />
		<b>Added By:</b> ${userMap[item.addedById].firstName} ${userMap[item.addedById].lastName}<br />
		</p>
	</td>
	<td width="160px">
		<form method="post" action="purchase.do">
			<input type="hidden" name="id" value="${item.id}" />
			<input type="submit" name="button" value="Get it for Noj!" />
		</form>
	</td>
</tr><tr>
	<td colspan="3">
		<p id="item">
		<b>Comment:</b> ${item.comment}
		</p>
	</td>
</tr>
</table>
<hr>
</c:forEach>

<br><br>
<jsp:include page="template-bottom.jsp" />