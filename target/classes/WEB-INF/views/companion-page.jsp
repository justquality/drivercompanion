<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${pageContext.request.userPrincipal.name}&nbsp;-&nbsp;MyProfile</title>
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-slider/7.0.2/css/bootstrap-slider.min.css"
	rel="stylesheet" type="text/css">
<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,700,400italic,300italic&subset=latin,cyrillic'
	rel='stylesheet' type='text/css'>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-slider/7.0.2/bootstrap-slider.min.js"></script>
<script src="${contextPath}/resources/js/functions.js"></script>
</head>
<body>
	<div class="container">

		<%@include file="components/header.jsp"%>

		<div class="row">
			<div class="container-fluid">
				<div class="col-md-6">
					<div class="companion-passport">
						<div class="media">
							<span class="media-left"> <img
								class="media-object companion-prof-img img-thumbnail"
								src="img/driver-thumb.jpg" alt="Profile image"><br>
							</span>
							<div class="media-body">
								<h4 class="media-heading">Personal Data</h4>
								<div class="table-responsive">
									<table class="table table-striped">
										<tr>
											<td>First Name</td>
											<td>${companion.user.firstName}</td>
										</tr>
										<tr>
											<td>Last name</td>
											<td>${companion.user.lastName}</td>
										</tr>
										<tr>
											<td>Phone Number</td>
											<td>${companion.user.phone}</td>
										</tr>
										<tr>
											<td>Rating</td>
											<td><fmt:parseNumber var="stars" integerOnly="true"
													type="number" value="${companion.rating}" /> <c:forEach
													begin="1" end="${stars}" var="i">
													<span class="glyphicon glyphicon-star"></span>
												</c:forEach> <c:forEach begin="1" end="${5 - stars}" var="i">
													<span class="glyphicon glyphicon-star-empty"></span>
												</c:forEach> ${companion.rating}</td>
										</tr>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<a href="${contextPath}/user-${companion.user.username}/add-review"
						class="btn btn-lg btn-block btn-success">Add Review</a><br>
				</div>
			</div>
		</div>

		<!-- Trip list -->
		<c:if test="${!empty trips}">
			<div class="row row-highlited">
				<h3 class="text-center">Closed Trips</h3>
				<br>
				<div class="table-responsive">
					<table class="table table-striped">
						<tr>
							<th>#ID</th>
							<th>Date</th>
							<th>From</th>
							<th>To</th>
							<th>Price</th>
							<th>Driver</th>
						</tr>
						<c:forEach var="trip" items="${trips}">
							<tr>
								<td>${trip.id}</td>
								<td><fmt:formatDate var="date" pattern="dd/MM/yyyy"
										value="${trip.date}" /> ${date}</td>
								<td>${trip.departure}</td>
								<td>${trip.arrival}</td>
								<td>${trip.price}</td>
								<td><c:choose>
										<c:when test="${trip.driver != null}">
											<a href="${contextPath}/user-${trip.driver.user.username}">
												${trip.driver.user.firstName}&nbsp;${trip.driver.user.lastName}
											</a>&nbsp;(${trip.driver.user.phone})
										</c:when>
										<c:otherwise>
											Currently no driver
										</c:otherwise>
									</c:choose></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</c:if>

		<div class="row">
			<div class="col-md-12">
				<h3 class="text-center">${companion.user.firstName}'s reviews</h3>
				<c:choose>
					<c:when test="${!empty companion.user.authorReviews}">
						<ul class="reviews">
							<c:forEach var="review" items="${companion.user.authorReviews}">
								<%@include file="components/review.jsp"%>
							</c:forEach>
						</ul>
					</c:when>
					<c:otherwise>
						<p class="text-center">${companion.user.firstName} did not write any reviews yet.</p>
					</c:otherwise>
				</c:choose>
			</div>
		</div>

		<hr>
		<div class="row">
			<div class="col-md-12">
				<h3 class="text-center">Recent reviews</h3>
				<c:choose>
					<c:when test="${!empty companion.user.targetReviews}">
						<ul class="reviews">
							<c:forEach var="review" items="${companion.user.targetReviews}">
								<%@include file="components/review.jsp"%>
							</c:forEach>
						</ul>
					</c:when>
					<c:otherwise>
						<p class="text-center">There are no reviews about ${companion.user.firstName}.</p>
						<br>
					</c:otherwise>
				</c:choose>
			</div>
		</div>

		<%@include file="components/footer.jsp"%>

	</div>
</body>
</html>