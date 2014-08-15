<jsp:include page="template-top.jsp" />

<h2 align="center">
Edit the Given Item
</h2>

<form method="post" action="edit.do" enctype="multipart/form-data">

<table width="100%" cellpadding="2px" cellspacing="0" border="0">
	<input type="hidden" name="id" value="${item.id}" />
	<tr>
		<td>Item Name:</td>
		<td><input type="text" name="name" value="${item.name}" /></td>
	</tr><tr>
		<td>Item URL:</td>
		<td><input type="text" name="url" value="${item.url}" /></td>
	</tr><tr>
		<td>Item Price:</td>
		<td><input type="text" name="price" value="${item.price}" /></td>
	</tr><tr>
		<td>Item Points:</td>
		<td><input type="text" name="points" value="${item.points}" /></td>
	</tr><tr>
		<td>Item Image: <br><img src="image.do?id=${item.imageId}" width="75px"></td>
		<td><input type="file" name="imgFile" value="" /></td>
	</tr><tr>
		<td>Item Action Image:<br><img src="image.do?id=${item.actionImageId}" width="75px"></td>
		<td><input type="file" name="actionFile" value="" /></td>
	</tr><tr>
		<td>Comment:</td>
		<td><input type="text" name="comment" value="${item.comment}" /></td>
	</tr><tr>
		<td>Tags:</td>
		<td><input type="text" name="tags" value="${item.tags}" /></td>
	</tr><tr>
		<td>Rank:</td>
		<td><input type="text" name="rank" value="${item.rank}" /></td>
	</tr><tr>
		<td>Status:</td>
		<td><input type="text" name="status" value="${item.status}" /></td>
	</tr><tr>
		<td colspan="2" align="center"><br>
			<input type="submit" name="button" value="Edit Item" />
		</td>
	</tr>
</table>
</form>

<jsp:include page="template-bottom.jsp" />