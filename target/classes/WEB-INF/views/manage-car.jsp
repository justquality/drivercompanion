<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:useBean id="date" class="java.util.Date" />
<fmt:formatDate var="now" value="${date}" pattern="yyyy" />

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${carTitle}</title>
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

		<form:form method="POST" modelAttribute="car" class="form-car">
			<h2>${carTitle}</h2>

			<spring:bind path="brand.brand">
				<div class="form-group">
					<form:label path="brand.brand">Brand: </form:label>
					&nbsp;
					<form:select class="form-control" path="brand.brand" items="${carBrands}" />
				</div>
			</spring:bind>

			<spring:bind path="year">
				<div class="form-group">
					<form:label path="year">Year: </form:label>
					&nbsp;
					<form:input id="car-year" data-slider-id="car-year-slider"
						type="text" data-slider-min="1920" data-slider-max="${now}"
						data-slider-step="1" data-slider-value="${car.year}" path="year" />
					<script>
						$('#car-year').slider({
							formatter : function(value) {
								return value + ' year';
							}
						});
					</script>
				</div>
			</spring:bind>

			<spring:bind path="type">
				<div class="form-group">
					<form:label path="type">Type: </form:label>
					&nbsp;
					<form:select class="form-control" path="type">
						<form:option value="Saloon">Saloon</form:option>
						<form:option value="Estate">Estate</form:option>
						<form:option value="SUV">SUV</form:option>
						<form:option value="Convertible">Convertible</form:option>
						<form:option value="Hatchback">Hatchback</form:option>
						<form:option value="Van">Van</form:option>
						<form:option value="Coupe">Coupe</form:option>
						<form:option value="Others">Others</form:option>
					</form:select>
				</div>
			</spring:bind>

			<spring:bind path="places">
				<div class="form-group">
					<form:label path="places">Places: </form:label>
					&nbsp;
					<form:input id="car-places" data-slider-id="car-places-slider"
						type="text" data-slider-min="1" data-slider-max="10"
						data-slider-step="1" data-slider-value="${car.places}" path="places" />
					<script>
						$('#car-places').slider({
							formatter : function(value) {
								return value + ' places';
							}
						});
					</script>
				</div>
			</spring:bind>

			<button type="submit" class="btn btn-info">Submit</button>
			<br>
			<br>
		</form:form>

		<%@include file="components/footer.jsp"%>

	</div>
</body>
</html>