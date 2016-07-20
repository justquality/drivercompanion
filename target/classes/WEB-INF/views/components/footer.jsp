<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="${contextPath}/home">
				<img alt="Logo" src="${contextPath}/resources/img/logo.png">
			</a>
		</div>
		<ul class="nav navbar-nav">
			<li><a href="${contextPath}/home"> <span
					class="glyphicon glyphicon-home"></span> Home
			</a></li>
			<li><a href="${contextPath}/trips"> <span
					class="glyphicon glyphicon-random"></span> Trips
			</a></li>
			<li><a href="${contextPath}/drivers"> <span
					class="glyphicon glyphicon-road"></span> Drivers
			</a></li>
			<li><a href="${contextPath}/about"> <span
					class="glyphicon glyphicon-question-sign"></span> About Us
			</a></li>
		</ul>
	</div>
</nav>