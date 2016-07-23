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
<title>${pageContext.request.userPrincipal.name} - MyProfile</title>
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
						<h4 class="modal-title">Edit Profile</h4>
					</div>
					<div class="modal-body">

						<form:form method="POST" modelAttribute="profile"
							action="${contextPath}/my-driver" class="form-driver-edit">
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

							<spring:bind path="user.email">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<form:input type="email" path="user.email" class="form-control"
										placeholder="Email"></form:input>
								</div>
							</spring:bind>

							<spring:bind path="driver.experience">
								<form:label path="driver.experience">Experience: </form:label>
								<form:input id="driv-exp" data-slider-id="driv-exp-slider"
									type="text" data-slider-min="0" data-slider-max="50"
									data-slider-step="1" data-slider-value="${driver.experience}"
									path="driver.experience" />
								<script>
									$('#driv-exp').slider({
										formatter : function(value) {
											return value + ' years';
										}
									});
								</script>
							</spring:bind>

							<spring:bind path="newPassword">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<form:input type="password" path="newPassword"
										class="form-control" placeholder="New Password (optional)"></form:input>
									<form:errors path="newPassword"></form:errors>
								</div>
							</spring:bind>

							<spring:bind path="newPasswordConfirm">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<form:input type="password" path="newPasswordConfirm"
										class="form-control"
										placeholder="Repeate New Password (optional)"></form:input>
									<form:errors path="newPasswordConfirm"></form:errors>
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
				<div class="col-md-7">
					<img class="car-thumb" src="img/car-thumb.svg" alt="Car" />
					<h4>
						Cars:
						<button type="button" class="btn btn-link" name="button">Edit</button>
					</h4>
					<div class="table-responsive">
						<table class="table table-striped">
							<tr>
								<th>#</th>
								<th>Brand</th>
								<th>Description</th>
								<th>Places</th>
							</tr>
							<tr>
								<td>car id</td>
								<td>car brand</td>
								<td>car description</td>
								<td>car places</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="col-md-5">
					<div class="driver-passport">
						<div class="media">
							<span class="media-left"> <img
								class="media-object driver-prof-img img-thumbnail"
								src="img/driver-thumb.jpg" alt="Profile image"><br>
								<button type="button" data-toggle="modal"
									data-target="#edit-profile-modal"
									class="btn btn-sm btn-block btn-info" name="edit-profile">Edit</button>
							</span>
							<div class="media-body">
								<h4 class="media-heading">Personal Data</h4>
								<div class="table-responsive">
									<table class="table table-responsive table-striped">
										<tr>
											<td>First Name</td>
											<td>${user.firstName}</td>
										</tr>
										<tr>
											<td>Last Name</td>
											<td>${user.lastName}</td>
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
					<br> <a href="${contextPath}/my-driver/new-trip"
						class="btn btn-lg btn-block btn-success">New Trip</a><br> <a
						href="${contextPath}/my-driver/search"
						class="btn btn-lg btn-block btn-info">Search for Companions</a><br>
				</div>
			</div>
		</div>

		<!-- Trip list -->
		<c:if test="${!empty trips}">
			<div class="row row-highlited">
				<h3 class="text-center">My Trips</h3>
				<br>
				<div class="table-responsive">
					<table class="table table-striped">
						<tr>
							<th>#</th>
							<th>Date</th>
							<th>From</th>
							<th>To</th>
							<th>Price</th>
						</tr>
						<c:forEach var="trip" items="${trips}">
							<tr>
								<td>${trip.id}</td>
								<td><fmt:formatDate var="date" pattern="dd/MM/yyyy"
										value="${trip.date}" /> ${date}</td>
								<td>${trip.departure}</td>
								<td>${trip.arrival}</td>
								<td>${trip.price}&nbsp;MDL</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</c:if>

		<!-- Recent reviews -->
		<div class="row">
			<div class="col-md-12">
				<h3 class="text-center">My reviews</h3>
				<ul class="reviews">
					<%
						for (int i = 0; i < 3; ++i) {
					%>
					<%@include file="components/review.jsp"%>
					<%
						}
					%>
				</ul>
			</div>
		</div>

		<hr>
		<!-- My reviews -->
		<div class="row">
			<div class="col-md-12">
				<h3 class="text-center">Recent reviews</h3>
				<ul class="reviews">
					<%
						for (int i = 0; i < 3; ++i) {
					%>
					<%@include file="components/review.jsp"%>
					<%
						}
					%>
				</ul>
			</div>
		</div>

		<%@include file="components/footer.jsp"%>

	</div>
</body>
</html>