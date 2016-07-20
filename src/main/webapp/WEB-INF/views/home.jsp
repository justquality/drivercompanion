<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
							<p>Your trip is automatically added to the list of trips, and
								as soon as there will be a companion for You, he will contact
								You.</p>
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
						<li class="list-group-item">Create&close trips</li>
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
						<li class="list-group-item">Create&close trips</li>
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
		
		<section>Latest trips</section>
		
		<section>Some reviews</section>
		
		<section>Top drivers</section>

		<%@include file="components/footer.jsp"%>

	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>