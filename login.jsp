<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="template-top.jsp" />

<p style="font-size:medium">
	Login below.
</p>

<jsp:include page="error-list.jsp" />

<p>
	<form method="post" action="login.do">
	<input type="hidden" name="from" value="${from}" />
	<table>
		<tr>
			<td> Email: </td>
			<td>
				<input type="text" name="userEmail" value="${form.userEmail}"/>
			</td>
		</tr>
		<tr>
			<td> Password: </td>
			<td><input type="password" name="password" value=""/></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" name="button" value="Login"/>
			</td>
		</tr>
		</table>
	</form>
</p>
<p> Or you could <a href="register.do">register</a>.</p>

<jsp:include page="template-bottom.jsp" />
