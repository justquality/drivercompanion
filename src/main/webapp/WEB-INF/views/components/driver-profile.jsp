<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="modal fade" id="edit-profile-modal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Edit Profile</h4>
            </div>
            <div class="modal-body">
            
            	<form:form method="POST" modelAttribute="user" class="form-driver-edit">
            		<form:hidden path="username"/>
            		<spring:bind path="firstName">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="text" path="firstName" class="form-control"
								placeholder="First Name"></form:input>
							<form:errors path="firstName"></form:errors>
						</div>
					</spring:bind>
					
					<spring:bind path="lastName">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="text" path="lastName" class="form-control"
								placeholder="Last Name"></form:input>
							<form:errors path="lastName"></form:errors>
						</div>
					</spring:bind>
					
					<spring:bind path="email">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="email" path="email" class="form-control"
								placeholder="Email"></form:input>
						</div>
					</spring:bind>
					
					<%-- <spring:bind path="password">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="password" path="password" class="form-control"
								placeholder="New Password"></form:input>
							<form:errors path="password"></form:errors>
						</div>
					</spring:bind>
		
					<spring:bind path="passwordConfirm">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="password" path="passwordConfirm"
								class="form-control" placeholder="Repeat new password"></form:input>
							<form:errors path="passwordConfirm"></form:errors>
						</div>
					</spring:bind> --%>
					
					<button type="submit" class="btn btn-info">Submit changes</button>
            	</form:form>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="container-fluid">
        <div class="col-md-7">
            <img class="car-thumb" src="img/car-thumb.svg" alt="Car" />
            <h4>Cars: <button type="button" class="btn btn-link" name="button">Edit</button></h4>
            <div class="table-responsive">
                <table class="table table-striped">
                    <tr>
                        <th>#</th>
                        <th>Brand</th>
                        <th>Description</th>
                        <th>Places</th>
                    </tr>
                    <tr>
                        <td>car id</td>
                        <td>car brand</td>
                        <td>car description</td>
                        <td>car places</td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="col-md-5">
            <div class="driver-passport">
                <div class="media">
                    <span class="media-left">
                        <img class="media-object driver-prof-img img-thumbnail" src="img/driver-thumb.jpg" alt="Profile image"><br>
                        <button type="button" data-toggle="modal" data-target="#edit-profile-modal" class="btn btn-sm btn-block btn-info" name="edit-profile">Edit</button>
                    </span>
                    <div class="media-body">
                        <h4 class="media-heading">Personal Data</h4>
                        <div class="table-responsive">
                            <table class="table table-responsive table-striped">
                                <tr>
                                    <td>First Name</td>
                                    <td>${user.firstName}</td>
                                </tr>
                                <tr>
                                    <td>Last Name</td>
                                    <td>${user.lastName}</td>
                                </tr>
                                <tr>
                                    <td>Experience</td>
                                    <td>${driver.experience} years</td>
                                </tr>
                                <tr>
                                    <td>
                                        Rating
                                    </td>
                                    <td>
                                    	<c:set var="rating" value="${driver.rating}"/>
                                    	<fmt:parseNumber var="stars" integerOnly="true" type="number" value="${rating}" />
                                    	
                                    	<c:forEach begin="1" end="${stars}" var="i">
                                    		<span class="glyphicon glyphicon-star"></span>
                                    	</c:forEach>
                                    	<c:forEach begin="1" end="${5 - stars}" var="i">
                                    		<span class="glyphicon glyphicon-star-empty"></span>
                                    	</c:forEach>
                                    	${driver.rating}
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <br>
            <a href="new-trip.jsp" class="btn btn-lg btn-block btn-success">New Trip</a><br>
            <a href="search.jsp" class="btn btn-lg btn-block btn-info">Search for Companions</a><br>
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
            </tr>
            <tr>
                <td>1</td>
                <td>10.06.2016</td>
                <td>Chisinau</td>
                <td>Orhei</td>
                <td>40</td>
            </tr>
        </table>
    </div>
</div>

<!-- Recent reviews -->
<div class="row">
    <div class="col-md-12">
        <h3 class="text-center">My reviews</h3><br>
        Some reviews
    </div>
</div>

<hr>
<!-- My reviews -->
<div class="row">
    <div class="col-md-12">
        <h3 class="text-center">Recent reviews</h3><br>
        Some reviews
    </div>
</div>