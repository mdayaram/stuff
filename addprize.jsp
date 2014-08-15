<jsp:include page="template-top.jsp" />

<h2 align="center">
Add a Prize
</h2>

<form method="post" action="addprize.do">

<table width="100%" cellpadding="2px" cellspacing="0" border="0">
	<tr>
		<td>Prize Name:</td>
		<td><input type="text" name="name" value="${name}" /></td>
	</tr><tr>
		<td>Points Worth:</td>
		<td><input type="text" name="points" value="${points}" /></td>
	</tr><tr>
		<td>Description:</td>
		<td><input type="text" name="action" value="${action}" /></td>
	</tr><tr>
		<td colspan="2" align="center"><br>
			<input type="submit" name="button" value="Add Prize" />
		</td>
	</tr>
</table>
</form>

<jsp:include page="template-bottom.jsp" />