<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="pragma" content="no-cache">
	<title>
		<c:choose>
			<c:when test="${empty title}">
				Stuff to Get Noj
			</c:when>
			<c:otherwise>
				${title}
			</c:otherwise>
		</c:choose>
	</title>
	<link rel="stylesheet" href="style.css" />
</head>

<body bgcolor="#ffffff">
<div id="top">


	<div id="header">
		<div id="title">
			<img src="title.png" alt="Stuff to Get Noj" />
		</div>
		<c:choose>
			<c:when test="${empty user}">
				<div id="loginreg">
					[<a href="login.do">login</a> / <a href="register.do">register</a>]
				</div>
			</c:when>
			<c:otherwise>
				<div id="loginreg">
					My Points: ${user.points} &nbsp;&nbsp;&nbsp;[<a href="logout.do">logout</a>]
				</div>
			</c:otherwise>
		</c:choose>
	</div>

	<div id="menu">
		<span class="menu"> <a href="view.do">Featured Item</a> </span>
		<span class="menu"> <a href="available.do">Available Items</a> </span>
		<span class="menu"> <a href="purchased.do">Purchased Items</a> </span>
		<span class="menu"> <a href="prizes.do">Redeem Prizes</a> </span>
		<span class="menu"> <a href="add.do">Suggest Item</a> </span>
	</div>
	
	<div id="mid">
		<div id="body">