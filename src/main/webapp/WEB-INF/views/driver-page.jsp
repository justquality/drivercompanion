<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${driver.user.firstName}&nbsp;-&nbsp;Driver</title>
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
				<div class="col-md-7">
					<c:choose>
						<c:when test="${driver.car == null}">
							<h4>This driver did not add any car yet.</h4>
						</c:when>
						<c:otherwise>
							<img class="car-thumb" src="img/car-thumb.svg" alt="Car" />
							<div class="table-responsive car-table">
								<table class="table table-striped">
									<tr>
										<th>Brand</th>
										<td>${driver.car.brand}</td>
									</tr>
									<tr>
										<th>Year</th>
										<td>${driver.car.year}</td>
									</tr>
									<tr>
										<th>Type</th>
										<td>${driver.car.type}</td>
									</tr>
									<tr>
										<th>places</th>
										<td>${driver.car.places}</td>
									</tr>
								</table>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="col-md-5">
					<div class="driver-passport">
						<div class="media">
							<span class="media-left"> <img
								class="media-object driver-prof-img img-thumbnail"
								src="img/driver-thumb.jpg" alt="Profile image"><br>
							</span>
							<div class="media-body">
								<h4 class="media-heading">Personal Data</h4>
								<div class="table-responsive">
									<table class="table table-responsive table-striped">
										<tr>
											<td>First Name</td>
											<td>${driver.user.firstName}</td>
										</tr>
										<tr>
											<td>Last Name</td>
											<td>${driver.user.lastName}</td>
										</tr>
										<tr>
											<td>Phone Number</td>
											<td>${driver.user.phone}</td>
										</tr>
										<tr>
											<td>Driving Experience</td>
											<td>${driver.experience}&nbsp;years</td>
										</tr>
										<tr>
											<td>Rating</td>
											<td><fmt:parseNumber var="stars" integerOnly="true"
													type="number" value="${driver.rating}" /> <c:forEach
													begin="1" end="${stars}" var="i">
													<span class="glyphicon glyphicon-star"></span>
												</c:forEach> <c:forEach begin="1" end="${5 - stars}" var="i">
													<span class="glyphicon glyphicon-star-empty"></span>
												</c:forEach> ${driver.rating}</td>
										</tr>
									</table>
								</div>
							</div>
						</div>
					</div>
					<br> <a
						href="${contextPath}/driver-${driver.user.username}/add-review"
						class="btn btn-lg btn-block btn-success">Add Review</a><br>
				</div>
			</div>
		</div>

		<!-- Open Trips -->
		<c:if test="${!empty openTrips}">
			<div class="row row-highlited">
				<h3 class="text-center">Open Trips</h3>
				<br>
				<div class="table-responsive">
					<table class="table table-striped">
						<tr>
							<th>#ID</th>
							<th>Date</th>
							<th>From</th>
							<th>To</th>
							<th>Price</th>
							<th colspan="2">Companions</th>
						</tr>
						<c:forEach var="trip" items="${openTrips}">
							<tr>
								<td>${trip.id}</td>
								<td><fmt:formatDate var="date" pattern="dd/MM/yyyy"
										value="${trip.date}" /> ${date}</td>
								<td>${trip.departure}</td>
								<td>${trip.arrival}</td>
								<td>${trip.price}&nbsp;MDL</td>
								<td><ul>
										<c:choose>
											<c:when test="${!empty trip.companions}">
												<c:forEach var="companion" items="${trip.companions}">
													<li><a
														href="${contextPath}/companion-${companion.user.username}">
															${companion.user.firstName}&nbsp;${companion.user.lastName}
													</a>&nbsp;(${companion.user.phone})</li>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<li>Currently no companions</li>
											</c:otherwise>
										</c:choose>
									</ul></td>
								<td><c:if
										test="${fn:length(trip.companions) lt driver.car.places}">
										<form:form method="POST"
											action="${contextPath}/driver-${driver.user.username}/become-companion-trip-${trip.id}">
											<button class="btn btn-info" type="submit">Become
												Companion</button>
										</form:form>
									</c:if>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</c:if>

		<!-- Closed Trips -->
		<c:if test="${!empty closedTrips}">
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
							<th>Companions</th>
						</tr>
						<c:forEach var="trip" items="${closedTrips}">
							<tr>
								<td>${trip.id}</td>
								<td><fmt:formatDate var="date" pattern="dd/MM/yyyy"
										value="${trip.date}" /> ${date}</td>
								<td>${trip.departure}</td>
								<td>${trip.arrival}</td>
								<td>${trip.price}&nbsp;MDL</td>
								<td><ul>
										<c:choose>
											<c:when test="${!empty trip.companions}">
												<c:forEach var="companion" items="${trip.companions}">
													<li><a
														href="${contextPath}/companion-${companion.user.username}">
															${companion.user.firstName}&nbsp;${companion.user.lastName}
													</a>&nbsp;(${companion.user.phone})</li>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<li>No companions</li>
											</c:otherwise>
										</c:choose>
									</ul></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</c:if>

		<div class="row">
			<div class="col-md-12">
				<h3 class="text-center">${driver.user.firstName}'s&nbsp;reviews</h3>
				<c:choose>
					<c:when test="${!empty driver.user.authorReviews}">
						<ul class="reviews">
							<c:forEach var="review" items="${driver.user.authorReviews}">
								<%@include file="components/review-about-companion.jsp"%>
							</c:forEach>
						</ul>
					</c:when>
					<c:otherwise>
						<p class="text-center">${driver.user.firstName}did&nbsp;not&nbsp;
							write any reviews yet.</p>
					</c:otherwise>
				</c:choose>
			</div>
		</div>

		<hr>
		<div class="row">
			<div class="col-md-12">
				<h3 class="text-center">Recent reviews</h3>
				<c:choose>
					<c:when test="${!empty driver.user.targetReviews}">
						<ul class="reviews">
							<c:forEach var="review" items="${driver.user.targetReviews}">
								<%@include file="components/review-about-driver.jsp"%>
							</c:forEach>
						</ul>
					</c:when>
					<c:otherwise>
						<p class="text-center">There are no reviews about
							${driver.user.firstName}.</p>
						<br>
					</c:otherwise>
				</c:choose>
			</div>
		</div>

		<%@include file="components/footer.jsp"%>

	</div>
</body>
</html>