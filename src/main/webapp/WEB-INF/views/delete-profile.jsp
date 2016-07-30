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
<title>Delete profile</title>
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

		<div class="container-fluid">
			<h1 class="page-header">Delete profile</h1>

			<form:form method="POST" modelAttribute="user" class="form-delete-user">
				<font color="red">${error}</font>
				<spring:bind path="passwordConfirm">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:input type="password" path="passwordConfirm"
							class="form-control"
							placeholder="Enter current password to CONFIRM DELETION"></form:input>
					</div>
				</spring:bind>

				<form:hidden path="password" />

				<button type="submit" class="btn btn-danger">Delete profile</button>
			</form:form>
			<br>
		</div>

		<%@include file="components/footer.jsp"%>

	</div>
</body>
</html>