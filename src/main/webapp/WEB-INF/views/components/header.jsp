<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<nav class="navbar navbar-default">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${contextPath}/home">
				<img alt="Logo" src="${contextPath}/resources/img/logo.png">
			</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
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

			<c:choose>
				<c:when test="${pageContext.request.userPrincipal.name != null}">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="${contextPath}/my-profile">
							<span class="glyphicon glyphicon-user"></span> ${pageContext.request.userPrincipal.name}
						</a></li>
						<li><a onclick="document.forms['logoutForm'].submit()" style="cursot: pointer;">
							<span class="glyphicon glyphicon-log-out"></span> Sign Out
						</a></li>
					</ul>
					<form id="logoutForm" method="POST" action="${contextPath}/logout">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>
				</c:when>
				<c:otherwise>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="${contextPath}/login">
							<span class="glyphicon glyphicon-user"></span> Sign In
						</a></li>
						<li><a href="${contextPath}/registration">
							<span class="glyphicon glyphicon-log-in"></span> Sign Up
						</a></li>
					</ul>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</nav>