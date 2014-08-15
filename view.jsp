<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template-top.jsp" />

<c:choose>
<c:when test="${empty item}">
<br><br>
	<p align="center">There are no Featured Items at this time.</p>
</c:when>
<c:otherwise>

<h2 align="center">
<a href="${item.url}" target="_new"> ${item.name} </a>
</h2>

<table width="100%" cellpadding="0px" cellspacing="0" border="0">
<tr><td width="340" valign="top">
	<a href="${item.url}" target="_new">
	<c:choose>
	<c:when test="${item.status == 2}">
		<img src="image.do?id=${item.actionImageId}" width="300" border="0px" />
	</c:when><c:otherwise>
		<img src="image.do?id=${item.imageId}" width="300" border="0px" />
	</c:otherwise>
	</c:choose>
	</a>
</td><td width="200">
	<p align="left">
	<b>Price:</b> $${item.price} <br/>
	<b>Points:</b> ${item.points} <br/>
	<b>Rank:</b> ${rankMap[item.rank]} <br/>
	<b>Status:</b> ${statusMap[item.status]} <br/>
	<b>Tags:</b> ${item.tags} </br>
	<br/>
	<b>Date Added:</b><br/> ${item.dateAdded} </br>
	<br/>
	<b>Added by:</b><br/> ${userMap[item.addedById].firstName} ${userMap[item.addedById].lastName}</br>
	<br/>
	<c:if test="${item.status > 0}">
	<b>Date Purchased:</b><br/> ${item.datePurchased} </br>
	<br/>
	<b>Purchased by:</b> <br/>${userMap[item.purchasedById].firstName} ${userMap[item.purchasedById].lastName}</br>
	<br/>
	</c:if>
	</p>
	
	<center>
	<c:if test="${item.status == 0}">
	</br></br></br>
	<form method="post" action="purchase.do">
		<input type="hidden" name="id" value="${item.id}" />
		<input type="submit" name="button" value="Get it for Noj!" />
	</form>
	</c:if>
	<c:if test="${user.id == 1}">
	<br /><br />
	<form method="post" action="edit.do">
		<input type="hidden" name="id" value="${item.id}" />
		<input type="submit" name="button" value="Edit Item" />
	</form>
	</c:if>
	</center>
</td></tr>
<tr><td colspan="2">
<br>
<b>Comment:</b> ${item.comment}
</td></tr>
</table>

</c:otherwise>
</c:choose>

<br><br>


<jsp:include page="template-bottom.jsp" />
