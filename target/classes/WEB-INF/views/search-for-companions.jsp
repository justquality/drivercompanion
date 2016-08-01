<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search trips</title>
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-slider/7.0.2/css/bootstrap-slider.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.1/css/bootstrap-datepicker.css"
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
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.1/js/bootstrap-datepicker.min.js"></script>
<script src="${contextPath}/resources/js/functions.js"></script>
</head>
<body>
	<div class="container">

		<%@include file="components/header.jsp"%>

		<div class="row">
			<div class="container-fluid">
				<div class="page-header">
					<h1>Search for companions</h1>
				</div>
				<div class="col-sm-7 col-md-8">
					<c:choose>
						<c:when test="${!empty trips}">
							<h3 class="text-center">Filter Result</h3>
							<br>
							<div class="table-responsive">
								<table class="table table-striped">
									<tr>
										<th>#</th>
										<th>Departure</th>
										<th>Arrival</th>
										<th>Date</th>
										<th>Price</th>
										<th colspan="2">Companions</th>
									</tr>
									<c:forEach var="trip" items="${trips}">
										<tr>
											<td>${trip.id}</td>
											<td>${trip.departure}</td>
											<td>${trip.arrival}</td>
											<td><fmt:formatDate var="date" pattern="dd/MM/yyyy"
													value="${trip.date}" /> ${date}</td>
											<td>${trip.price}</td>
											<td><ul>
													<c:choose>
														<c:when test="${!empty trip.companions}">
															<c:forEach var="companion" items="${trip.companions}">
																<li><a
																	href="${contextPath}/companion-${companion.user.username}">
																		${companion.user.firstName}&nbsp;${companion.user.lastName}
																</a></li>
															</c:forEach>
														</c:when>
														<c:otherwise>
															<li>Currently no companions</li>
														</c:otherwise>
													</c:choose>
												</ul></td>
											<td><c:if test="${trip.driver == null}">
													<form:form method="POST"
														action="${contextPath}/companion-${companion.user.username}/become-driver-trip-${trip.id}">
														<button class="btn btn-info" type="submit">Become
															Driver</button>
													</form:form>
												</c:if></td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</c:when>
						<c:otherwise>
							<h3 class="text-center">No matching trips found</h3>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="col-sm-5 col-md-4">
					<form:form method="POST" modelAttribute="tripForm"
						class="search-form">
						<h4 class="text-center">
							<span class="glyphicon glyphicon-filter"></span> Filters
						</h4>

						<spring:bind path="departure">
							<div class="form-group ${status.error ? 'has-error' : ''}">
								<form:input type="text" path="departure" class="form-control"
									placeholder="Departure"></form:input>
								<form:errors path="departure"></form:errors>
							</div>
						</spring:bind>

						<spring:bind path="arrival">
							<div class="form-group ${status.error ? 'has-error' : ''}">
								<form:input type="text" path="arrival" class="form-control"
									placeholder="Arrival"></form:input>
								<form:errors path="arrival"></form:errors>
							</div>
						</spring:bind>

						<spring:bind path="date">
							<div class="input-group date ${status.error ? 'has-error' : ''}"
								id="datepicker" data-provide="datepicker"
								data-date-format="dd/mm/yyyy" data-date-start-date="0d">
								<form:input path="date" id="date-form" name="date" type="text"
									class="form-control"></form:input>
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-th"></span>
								</div>
								<form:errors path="date"></form:errors>
							</div>
						</spring:bind>

						<br>
						<label for="price-filter">Max Price: </label>
						<br>
						<form:input path="maxPrice" id="priceFilter" type="text" value=""
							data-slider-min="1" data-slider-max="500" data-slider-step="1"
							data-slider-value="${tripForm.maxPrice}"></form:input>
						&nbsp;
						<span id="priceFilterCurrentSliderValLabel"><span
							id="priceFilterSliderVal">${tripForm.maxPrice}</span>&nbsp;MDL </span>
						<br>
						<br>
						<button type="submit" class="btn btn-block btn-info">Search
							for companions</button>

						<script>
							$('#datepicker').datepicker({
								autoclose : true,
								todayHighlight : true
							});

							$('#priceFilter').slider();

							$('#priceFilter').on(
									'slide',
									function(slideEvt) {
										$('#priceFilterSliderVal').text(
												slideEvt.value);
									});
						</script>
					</form:form>
					<br>
				</div>
			</div>
		</div>

		<%@include file="components/footer.jsp"%>
	</div>
</body>
</html>