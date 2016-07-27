<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Trips</title>
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
			<h1>Latest Trips</h1>
		</div>

		<div class="table-responsive">
			<table class="table table-striped">
				<tr>
					<th>#</th>
					<th>Driver</th>
					<th>Departure</th>
					<th>Arrival</th>
					<th>Date</th>
					<th>Price</th>
				</tr>
				<c:forEach var="trip" items="${trips}">
					<tr>
						<td>${trip.id}</td>
						<td><a
							href="${contextPath}/driver-${trip.driver.user.username}">
								${trip.driver.user.firstName}&nbsp;${trip.driver.user.lastName}</a></td>
						<td>${trip.departure}</td>
						<td>${trip.arrival}</td>
						<td><fmt:formatDate var="date" pattern="dd/MM/yyyy"
								value="${trip.date}" /> ${date}</td>
						<td>${trip.price}</td>
					</tr>
				</c:forEach>

			</table>
		</div>

		<%@include file="components/footer.jsp"%>

	</div>
</body>
</html>