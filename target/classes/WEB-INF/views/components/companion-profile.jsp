<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="modal fade" id="edit-profile-modal" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Edit Profile</h4>
			</div>
			<div class="modal-body">

				<form:form method="POST" modelAttribute="profile"
					action="${contextPath}/my-profile/edit-companion-${user.id}"
					class="form-companion-edit">
					<form:hidden path="user.username" />
					<spring:bind path="user.firstName">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="text" path="user.firstName"
								class="form-control" placeholder="First Name"></form:input>
							<form:errors path="user.firstName"></form:errors>
						</div>
					</spring:bind>

					<spring:bind path="user.lastName">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="text" path="user.lastName" class="form-control"
								placeholder="Last Name"></form:input>
							<form:errors path="user.lastName"></form:errors>
						</div>
					</spring:bind>

					<spring:bind path="user.email">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="email" path="user.email" class="form-control"
								placeholder="Email"></form:input>
						</div>
					</spring:bind>
					
					<spring:bind path="newPassword">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="password" path="newPassword" class="form-control"
								placeholder="New Password (optional)"></form:input>
							<form:errors path="newPassword"></form:errors>
						</div>
					</spring:bind>
					
					<spring:bind path="newPasswordConfirm">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="password" path="newPasswordConfirm" class="form-control"
								placeholder="Repeate New Password (optional)"></form:input>
							<form:errors path="newPasswordConfirm"></form:errors>
						</div>
					</spring:bind>
					
					<spring:bind path="user.passwordConfirm">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="password" path="user.passwordConfirm"
								class="form-control" placeholder="Enter current password to confirm changes"></form:input>
							<form:errors path="user.passwordConfirm"></form:errors>
						</div>
					</spring:bind>

					<button type="submit" class="btn btn-info">Submit changes</button>
				</form:form>
			</div>
		</div>
	</div>
</div>

<div class="row">
	<div class="container-fluid">
		<div class="col-md-6">
			<div class="companion-passport">
				<div class="media">
					<span class="media-left"> <img
						class="media-object companion-prof-img img-thumbnail"
						src="img/driver-thumb.jpg" alt="Profile image"><br>
						<button type="button" data-toggle="modal"
							data-target="#edit-profile-modal"
							class="btn btn-sm btn-block btn-info" name="edit-profile">Edit</button>
					</span>
					<div class="media-body">
						<h4 class="media-heading">Personal Data</h4>
						<div class="table-responsive">
							<table class="table table-striped">
								<tr>
									<td>Name</td>
									<td>${user.firstName}</td>
								</tr>
								<tr>
									<td>Surname</td>
									<td>${user.lastName}</td>
								</tr>
								<tr>
									<td>Rating</td>
									<td><c:set var="rating" value="${companion.rating}" /> <fmt:parseNumber
											var="stars" integerOnly="true" type="number"
											value="${rating}" /> <c:forEach begin="1" end="${stars}"
											var="i">
											<span class="glyphicon glyphicon-star"></span>
										</c:forEach> <c:forEach begin="1" end="${5 - stars}" var="i">
											<span class="glyphicon glyphicon-star-empty"></span>
										</c:forEach> ${companion.rating}</td>
								</tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <a href="new-trip.jsp"
				class="btn btn-lg btn-block btn-success">New Trip</a><br>
            <a href="search.jsp" class="btn btn-lg btn-block btn-info">Search for Drivers</a><br>
        </div>
    </div>
</div>

<!-- Trip list -->
<div class="row row-highlited">
    <h3 class="text-center">Closed Trips</h3><br>
    <div class="table-responsive">
        <table class="table table-striped">
            <tr>
                <th>#</th>
                <th>Date</th>
                <th>From</th>
                <th>To</th>
                <th>Price</th>
                <th>Driver</th>
            </tr>
            <tr>
                <td>1</td>
                <td>10.06.2016</td>
                <td>Chisinau</td>
                <td>Orhei</td>
                <td>40</td>
                <td>Michael Schumacher</td>
            </tr>
        </table>
    </div>
</div>

<!-- Recent reviews -->
<div class="row">
	<div class="col-md-12">
		<h3 class="text-center">My reviews</h3>
		<ul class="reviews">
			<%
				for (int i = 0; i < 3; ++i) {
			%>
			<%@include file="review.jsp"%>
			<%
				}
			%>
		</ul>
	</div>
</div>

<hr>
<!-- My reviews -->
<div class="row">
	<div class="col-md-12">
		<h3 class="text-center">Recent reviews</h3>
		<ul class="reviews">
			<%
				for (int i = 0; i < 3; ++i) {
			%>
			<%@include file="review.jsp"%>
			<%
				}
			%>
		</ul>
	</div>
</div>