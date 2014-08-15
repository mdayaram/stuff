<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		</div>
		<div id="topten">
			<h4>Top Ten Users</h4>
			<ol>
			<c:forEach var="u" items="${userList}">
				<li> ${u.firstName} ${u.lastName} (${u.points}) </li>
			</c:forEach>
			</ol>
		</div>
	</div>

	<div id="footer">
		Copyright Manoj Dayaram (c) 2010.
	</div>

</div>
</body>
</html>