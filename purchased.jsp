<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template-top.jsp" />

<h2 align="center"> Purchased Items </h2>

<c:if test="${empty itemsList}">
<br><br>
	<p align="center">There are no Purchased Items at this time.</p>
</c:if>

<c:forEach var="item" items="${itemsList}">
<table width="100%" cellpadding="10px" cellspacing="0px" border="0px">
<tr>
	<td width="130px">
		<a href="view.do?id=${item.id}"><img src="image.do?id=${item.imageId}" width="100px" border="0px" /></a>
	</td>
	<td>
		<p class="item">
		<a href="view.do?id=${item.id}">${item.name}</a> <br />
		<b>Price:</b> $${item.price} <br />
		<b>Purchased by:</b><br/> ${userMap[item.purchasedById].firstName} ${userMap[item.purchasedById].lastName}<br />
		<b>Date Purchased:</b><br/> ${item.datePurchased} <br />
		<b>Status:</b> ${statusMap[item.status]} <br />
		</p>
	</td>
	<td width="130px">
		<c:choose>
		<c:when test="${item.status == 2}">
			<a href="view.do?id=${item.id}"><img src="image.do?id=${item.actionImageId}" width="100px" border="0px" /></a>
		</c:when><c:otherwise>
			<p width="100px">No Action Shot available yet.</p>
		</c:otherwise></c:choose>
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