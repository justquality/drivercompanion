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

		<div class="modal fade" id="edit-profile-modal" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Edit Profile
							<a href="${contextPath}/delete-profile">Delete profile</a>
						</h4>
					</div>
					<div class="modal-body">

						<form:form method="POST" modelAttribute="companion"
							action="${contextPath}/my-companion" class="form-companion-edit">
							<form:hidden path="user.id" />
							<form:hidden path="user.username" />
							<spring:bind path="user.firstName">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<form:input type="text" path="user.firstName"
										class="form-control" placeholder="First Name"></form:input>
									<form:errors path="user.firstName"></form:errors>
								</div>
							</spring:bind>

							<spring:bind path="user.lastName">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<form:input type="text" path="user.lastName"
										class="form-control" placeholder="Last Name"></form:input>
									<form:errors path="user.lastName"></form:errors>
								</div>
							</spring:bind>

							<spring:bind path="user.phone">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<form:input type="text" path="user.phone" class="form-control"
										placeholder="Phone (0XX-XXX-XXX)"></form:input>
									<form:errors path="user.phone"></form:errors>
								</div>
							</spring:bind>

							<spring:bind path="user.email">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<form:input type="email" path="user.email" class="form-control"
										placeholder="Email"></form:input>
								</div>
							</spring:bind>

							<spring:bind path="user.passwordConfirm">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<form:input type="password" path="user.passwordConfirm"
										class="form-control"
										placeholder="Enter current password to confirm changes"></form:input>
									<form:errors path="user.passwordConfirm"></form:errors>
								</div>
							</spring:bind>

							<form:hidden path="user.password" />

							<button type="submit" class="btn btn-info">Submit
								changes</button>
						</form:form>
					</div>
				</div>
			</div>
		</div>
		<script>
			$(document).ready(function() {
				if (window.location.href.indexOf('#edit-profile-modal') != -1) {
					$('#edit-profile-modal').modal('show');
				}
			});
		</script>

		<div class="row">
			<div class="container-fluid">
				<div class="col-md-6">
					<div class="companion-passport">
						<div class="media">
							<span class="media-left"> <img
								class="media-object companion-prof-img img-thumbnail"
								src="${contextPath}/resources/img/user-thumb.png" alt="Profile image"><br>
								<button type="button" data-toggle="modal"
									data-target="#edit-profile-modal"
									class="btn btn-sm btn-block btn-info" name="edit-profile">Edit</button>
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
					<a href="${contextPath}/my-companion/new-trip"
						class="btn btn-lg btn-block btn-success">New Trip</a><br> <a
						href="${contextPath}/my-companion/search"
						class="btn btn-lg btn-block btn-info">Search for Drivers</a><br>
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
							<th colspan="2">Driver</th>
						</tr>
						<c:forEach var="trip" items="${openTrips}">
							<tr>
								<td>${trip.id}</td>
								<td><fmt:formatDate var="date" pattern="dd/MM/yyyy"
										value="${trip.date}" /> ${date}</td>
								<td>${trip.departure}</td>
								<td>${trip.arrival}</td>
								<td>${trip.price}</td>
								<td><c:choose>
										<c:when test="${trip.driver != null}">
											<a href="${contextPath}/driver-${trip.driver.user.username}">
												${trip.driver.user.firstName}&nbsp;${trip.driver.user.lastName}
											</a>&nbsp;(${trip.driver.user.phone})
										</c:when>
										<c:otherwise>
											Currently no driver
										</c:otherwise>
									</c:choose></td>
								<td><c:if test="${trip.driver == null}">
										<form:form method="POST"
											action="${contextPath}/my-companion/delete-trip-${trip.id}">
											<button class="btn btn-danger" type="submit">Delete</button>
										</form:form>
									</c:if></td>
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
							<th>Driver</th>
						</tr>
						<c:forEach var="trip" items="${closedTrips}">
							<tr>
								<td>${trip.id}</td>
								<td><fmt:formatDate var="date" pattern="dd/MM/yyyy"
										value="${trip.date}" /> ${date}</td>
								<td>${trip.departure}</td>
								<td>${trip.arrival}</td>
								<td>${trip.price}</td>
								<td><c:choose>
										<c:when test="${trip.driver != null}">
											<a href="${contextPath}/driver-${trip.driver.user.username}">
												${trip.driver.user.firstName}&nbsp;${trip.driver.user.lastName}
											</a>&nbsp;(${trip.driver.user.phone})
										</c:when>
										<c:otherwise>
											No driver
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
				<h3 class="text-center">My reviews</h3>
				<c:choose>
					<c:when test="${!empty companion.user.authorReviews}">
						<ul class="reviews">
							<c:forEach var="review" items="${companion.user.authorReviews}">
								<%@include file="components/review-about-driver.jsp"%>
							</c:forEach>
						</ul>
					</c:when>
					<c:otherwise>
						<p class="text-center">You did not write any reviews yet.</p>
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
								<%@include file="components/review-about-companion.jsp"%>
							</c:forEach>
						</ul>
					</c:when>
					<c:otherwise>
						<p class="text-center">There are no reviews about You.</p>
						<br>
					</c:otherwise>
				</c:choose>
			</div>
		</div>

		<%@include file="components/footer.jsp"%>

	</div>
</body>
</html>