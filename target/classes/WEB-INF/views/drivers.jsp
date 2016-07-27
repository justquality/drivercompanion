<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Drivers</title>
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,700,400italic,300italic&subset=latin,cyrillic'
	rel='stylesheet' type='text/css'>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">

		<%@include file="components/header.jsp"%>

		<div class="page-header">
			<h1>Drivers</h1>
		</div>

		<div class="table-responsive">
			<table class="table table-striped">
				<tr>
					<th></th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Experience&nbsp;(years)</th>
					<th>Rating</th>
				</tr>
				<c:forEach var="driver" items="${drivers}">
					<tr>
						<td><a href="${contextPath}/driver-${driver.user.username}">
								${driver.user.username} </a></td>
						<td>${driver.user.firstName}</td>
						<td>${driver.user.lastName}</td>
						<td>${driver.experience}</td>
						<td>${driver.rating}&nbsp;<fmt:parseNumber var="stars"
								integerOnly="true" type="number" value="${driver.rating}" /> <c:forEach
								begin="1" end="${stars}" var="i">
								<span class="glyphicon glyphicon-star"></span>
							</c:forEach> <c:forEach begin="1" end="${5 - stars}" var="i">
								<span class="glyphicon glyphicon-star-empty"></span>
							</c:forEach>
						</td>
					</tr>
				</c:forEach>

			</table>
		</div>

		<%@include file="components/footer.jsp"%>

	</div>
</body>
</html>