<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${pageContext.request.userPrincipal.name}-MyProfile</title>
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
</head>
<body>
	<div class="container">

		<%@include file="components/header.jsp"%>

		<c:choose>
			<c:when test="${driver != null}">
				<%@include file="components/driver-profile.jsp"%>
			</c:when>
			<c:when test="${companion != null}">
				<%@include file="components/companion-profile.jsp"%>
			</c:when>
		</c:choose>

		<%@include file="components/footer.jsp"%>

	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-slider/7.0.2/bootstrap-slider.min.js"></script>
	<script src="${contextPath}/resources/js/functions.js"></script>
</body>
</html>