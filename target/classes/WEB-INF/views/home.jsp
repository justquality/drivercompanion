<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home page</title>
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

		<c:if test="${pageContext.request.userPrincipal == null}">
			<div class="jumbotron text-center">
				<h1>Find Your Companion</h1>
				<p>Become smarter</p>

				<div class="row">
					<div class="col-sm-12 col-md-4">
						<div class="thumbnail">
							<img src="${contextPath}/resources/img/easy-thumb.png"
								alt="Easy-Thumb">
							<div class="caption">
								<h3>Easy</h3>
								<br>
								<p>You can easily make a command anywhere. All You need is
									the access to the Internet.</p>
							</div>
						</div>
					</div>

					<div class="col-sm-12 col-md-4">
						<div class="thumbnail">
							<img src="${contextPath}/resources/img/fast-thumb.png"
								alt="Fast-Thumb">
							<div class="caption">
								<h3>Fast</h3>
								<br>
								<p>Your trip is automatically added to the list of trips,
									and as soon as there will be a companion for You, he will
									contact You.</p>
							</div>
						</div>
					</div>

					<div class="col-sm-12 col-md-4">
						<div class="thumbnail">
							<img src="${contextPath}/resources/img/cheap-thumb.png"
								alt="Cheap-Thumb">
							<div class="caption">
								<h3>Cheap</h3>
								<br>
								<p>It is much cheaper to use our service than to use a taxi.
									And it is also more comfortable than a public transport.</p>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- Account descriptions -->
			<div class="row">
				<div class="col-sm-0 col-md-1"></div>
				<div class="col-sm-6 col-md-4">
					<div class="caption">
						<h3 class="text-center">Driver's possibilities</h3>
						<br>
						<ul class="list-group">
							<li class="list-group-item">Link car to profile</li>
							<li class="list-group-item">Create and close trips</li>
							<li class="list-group-item">Manage trips</li>
							<li class="list-group-item">Search for companions</li>
							<li class="list-group-item">Leave feedback about companions</li>
						</ul>
						<a class="btn btn-block btn-md btn-info"
							href="${contextPath}/registration">Registration</a>
					</div>
				</div>
				<div class="col-sm-0 col-md-2"></div>
				<div class="col-sm-6 col-md-4">
					<div class="caption">
						<h3 class="text-center">Companion's possibilities</h3>
						<br>
						<ul class="list-group">
							<li class="list-group-item">Create and close trips</li>
							<li class="list-group-item">Manage trips</li>
							<li class="list-group-item">Search for drivers</li>
							<li class="list-group-item">Leave feedback about drivers</li>
						</ul>
						<a class="btn btn-block btn-md btn-success"
							href="${contextPath}/registration">Registration</a>
					</div>
				</div>
				<div class="col-sm-0 col-md-1"></div>
			</div>
		</c:if>

		<div class="container-fluid">
			<div class="row row-highlited">
				<h3 class="text-center">Upcoming trips</h3>
				<br>
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

			</div>

			<div class="row">
				<div class="col-md-12">
					<h3 class="text-center">Recent reviews</h3>
					<c:if test="${!empty reviews}">
						<ul class="reviews">
							<c:forEach var="review" items="${reviews}">
								<li>
									<div class="review">
										<img src="img/user-thumb.jpg" class="img-circle review-photo"
											alt="" />
										<div class="review-body">
											<h4>
												${review.authorName}&nbsp;
												<c:forEach begin="1" end="${review.mark}" var="i">
													<span class="glyphicon glyphicon-star"></span>
												</c:forEach>
												<c:forEach begin="1" end="${5 - review.mark}" var="i">
													<span class="glyphicon glyphicon-star-empty"></span>
												</c:forEach>
												&nbsp;(${review.mark})
											</h4>
											<p>
												<fmt:formatDate var="date" pattern="dd-MM-yyyy"
													value="${review.date}" />
												${date}, About&nbsp;
												${review.userTarget.firstName}&nbsp;${review.userTarget.lastName}
											</p>
											<br>
											<p>${review.comment}</p>
										</div>
									</div>
								</li>
							</c:forEach>
						</ul>
					</c:if>
				</div>
			</div>

			<div class="row row-highlited">
				<h3 class="text-center">Top drivers</h3>
				<br>
				<div class="col-xs-0 col-sm-1 col-md-2"></div>
				<c:set var="end" value="${fn:length(drivers)}" />
				<div class="col-xs-6 col-sm-4 col-md-3">
					<ol class="top10">
						<c:forEach begin="0" end="4" var="i">
							<li><a
								href="${contextPath}/driver-${drivers[i].user.username}">
									${drivers[i].user.firstName}&nbsp;${drivers[i].user.lastName}&nbsp;</a>
								${drivers[i].rating}</li>
						</c:forEach>
					</ol>
				</div>
				<div class="col-xs-0 col-sm-1 col-md-1"></div>
				<div class="col-xs-0 col-sm-1 col-md-1"></div>
				<div class="col-xs-6 col-sm-4 col-md-3">
					<ol class="top10">
						<c:forEach begin="5" end="9" var="i">
							<li><a
								href="${contextPath}/driver-${drivers[i].user.username}">
									${drivers[i].user.firstName}&nbsp;${drivers[i].user.lastName}&nbsp;</a>
								${drivers[i].rating}</li>
						</c:forEach>
					</ol>
				</div>
				<div class="col-xs-0 col-sm-1 col-md-2"></div>
			</div>
		</div>

		<%@include file="components/footer.jsp"%>

	</div>
</body>
</html>