<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Review</title>
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

		<h4 class="text-center">${error}</h4>

		<c:if test="${error == null}">
			<form:form method="POST" modelAttribute="review"
				class="form-add-review">
				<form:hidden path="authorName"
					value="${author.firstName} ${author.lastName}" />

				<spring:bind path="mark">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:label path="mark">Mark: </form:label>
						&nbsp;
						<form:input id="rev-mark" data-slider-id="rev-mark-slider"
							type="text" data-slider-min="1" data-slider-max="5"
							data-slider-step="1" data-slider-value="${mark}" path="mark" />
						<script>
							$('#rev-mark').slider({
								formatter : function(value) {
									return value + ' stars';
								}
							});
						</script>
						&nbsp;
						<form:errors path="mark"></form:errors>
					</div>
				</spring:bind>

				<spring:bind path="comment">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:textarea path="comment" class="form-control"
							placeholder="Enter review message here..." rows="10"
							style="width: 100%;" />
						<form:errors path="comment"></form:errors>
					</div>
				</spring:bind>

				<button type="submit" class="btn btn-success">Add Review</button>

			</form:form>
		</c:if>
		<br>

		<%@include file="components/footer.jsp"%>

	</div>
</body>
</html>