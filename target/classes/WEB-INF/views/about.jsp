<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>About us</title>
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
</head>
<body>
	<div class="container">

		<%@include file="components/header.jsp"%>

		<div class="container-fluid">
			<div class="page-header">
				<h1>About Us</h1>
			</div>
			<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed
				nisi neque, blandit ac lectus quis, pharetra venenatis neque.
				Quisque quis est pharetra, egestas ligula eget, ultricies lectus.
				Morbi nec tincidunt odio. Cras lobortis mauris est, ac maximus felis
				mattis sed. Cras et velit a augue convallis sodales. Donec eu
				interdum ligula, a ullamcorper erat. Etiam urna nulla, cursus vel
				nisi ac, tincidunt maximus sapien. Proin at nulla pellentesque,
				consectetur dolor sed, tempus est. Nam tellus mauris, rutrum a risus
				ac, tempor volutpat odio. Fusce dolor est, sagittis eu turpis at,
				dapibus tincidunt diam. Duis nec odio dolor.</p>

			<p>Nulla dictum velit a nisl auctor interdum. Nunc sem felis,
				viverra id urna tempor, viverra fringilla orci. Suspendisse
				tristique neque a tortor lobortis, et maximus odio posuere. Aenean
				aliquet venenatis lorem ullamcorper ultrices. Sed et velit eget
				mauris ultricies posuere vitae sed justo. Cras ut sodales magna, nec
				vestibulum ipsum. Aliquam erat volutpat.</p>

			<p>Nunc et leo ac velit lobortis consequat. Proin posuere massa
				sit amet lectus vulputate, vitae elementum augue elementum. Aliquam
				eget dignissim libero. Vestibulum nec risus tempus, lacinia massa
				dictum, placerat tellus. Donec interdum placerat nunc eu egestas.
				Etiam quis egestas odio. Nulla facilisi. Cras fringilla ligula vitae
				felis venenatis pellentesque. Duis cursus arcu eu auctor semper.
				Proin pellentesque sodales nulla. Morbi mattis orci sapien, sit amet
				pretium enim sagittis in. Morbi dapibus auctor elementum. Fusce id
				augue dolor.</p>

			<p>Fusce vitae erat pharetra, vestibulum odio sit amet, aliquet
				sem. Suspendisse sit amet lectus quis massa aliquet posuere in vitae
				mauris. Ut varius eget nunc sed pharetra. Curabitur luctus, justo et
				pharetra aliquet, mauris ipsum dapibus libero, nec iaculis odio
				nulla a nunc. Nam nisl tellus, dictum id libero nec, tincidunt
				mattis sapien. Curabitur eu blandit mi. Praesent purus nunc, commodo
				vitae nulla eget, tristique placerat turpis. Aliquam eget leo
				tortor. Aliquam et interdum ex. Pellentesque a maximus felis.</p>

			<p>Nullam interdum ex lorem, in facilisis orci malesuada vitae.
				Nam at ex nec est commodo dignissim non non velit. Suspendisse
				malesuada maximus ipsum, quis gravida magna cursus sed. Pellentesque
				habitant morbi tristique senectus et netus et malesuada fames ac
				turpis egestas. Maecenas mollis at mauris ac iaculis. Class aptent
				taciti sociosqu ad litora torquent per conubia nostra, per inceptos
				himenaeos. Ut lobortis sollicitudin risus, in ornare enim. Nam
				tincidunt dolor vel risus semper molestie. Ut aliquam dui at metus
				mollis rutrum. Sed nec tellus eros. Nunc pharetra dignissim tortor a
				accumsan. Sed eget est et leo mattis tempus.</p>
			<br>
		</div>

		<%@include file="components/footer.jsp"%>

	</div>
</body>
</html>