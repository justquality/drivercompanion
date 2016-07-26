<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create New Trip</title>
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
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
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.1/js/bootstrap-datepicker.min.js"></script>
<script src="${contextPath}/resources/js/functions.js"></script>
</head>
<body>
	<div class="container">

		<%@include file="components/header.jsp"%>

		<form:form method="POST" modelAttribute="trip" class="form-new-trip">
			<h2 class="form-heading">Create new trip</h2>

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

			<spring:bind path="price">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:input type="text" path="price" class="form-control"
						placeholder="Price (for 1 person) in MDL"></form:input>
					<form:errors path="price"></form:errors>
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
			<button type="submit" class="btn btn-info">Create</button>
			<br>
			<br>
		</form:form>
		<script>
			$('#datepicker').datepicker({
				autoclose : true,
				todayHighlight : true
			});
		</script>

		<%@include file="components/footer.jsp"%>

	</div>
</body>
</html>