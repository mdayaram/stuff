<jsp:include page="template-top.jsp" />

<h2 align="center">
Suggest An Item to Get Noj
</h2>

<form method="post" action="add.do" enctype="multipart/form-data">

<table width="100%" cellpadding="2px" cellspacing="0" border="0">
	<tr>
		<td>Item Name:</td>
		<td><input type="text" name="name" value="${name}" /></td>
	</tr><tr>
		<td>Item URL:</td>
		<td><input type="text" name="url" value="${url}" /></td>
	</tr><tr>
		<td>Item Price:</td>
		<td><input type="text" name="price" value="${price}" /></td>
	</tr><tr>
		<td>Item Image:</td>
		<td><input type="file" name="imgFile" value="${imgFile}" /></td>
	</tr><tr>
		<td>Comment:</td>
		<td><input type="text" name="comment" value="${comment}" /></td>
	</tr><tr>
		<td>Tags:</td>
		<td><input type="text" name="tags" value="${tags}" /></td>
	</tr><tr>
		<td colspan="2" align="center"><br>
			<input type="submit" name="button" value="Add Item" />
		</td>
	</tr>
</table>
</form>

<jsp:include page="template-bottom.jsp" />