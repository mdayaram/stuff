
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="template-top.jsp" />

<p style="font-size:medium">
    To register, enter the following information. (All fields required.)
</p>

<jsp:include page="error-list.jsp" />

<p>
<form method="post" action="register.do">
	
	<table>
		<tr>
			<td>Email:</td>
			<td><input type="text" name="userEmail" value="${form.userEmail}"/></td>
		</tr>
		<tr>
			<td>First Name: </td>
			<td>
			<input type="text" name="firstName" value="${form.firstName}"/>
			</td>
		</tr>
		<tr>
			<td>Last Name: </td>
			<td>
			<input type="text" name="lastName" value="${form.lastName}"/>
			</td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="password" name="password" value="${form.password}"/></td>
		</tr>
		<tr>
			<td>Confirm Password: </td>
			<td>
			<input type="password" name="confirmedPassword" value=""/>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<input type="submit" name="button" value="Register"/>
			</td>
		</tr>
	</table>
</form>
</p>

<jsp:include page="template-bottom.jsp" />

