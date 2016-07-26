<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<li>
	<div class="review">
		<img src="img/user-thumb.jpg" class="img-circle review-photo" alt="" />
		<div class="review-body">
			<h4>
				<c:choose>
					<c:when test="${review.userAuthor != null}">
						<a href="${contextPath}/companion-${review.userAuthor.username}">
							${review.authorName}</a>
					</c:when>
					<c:otherwise>
						${review.authorName}
					</c:otherwise>
				</c:choose>
				&nbsp;
				<c:forEach begin="1" end="${review.mark}" var="i">
					<span class="glyphicon glyphicon-star"></span>
				</c:forEach>
				<c:forEach begin="1" end="${5 - review.mark}" var="i">
					<span class="glyphicon glyphicon-star-empty"></span>
				</c:forEach>
				&nbsp;(${review.mark})
			</h4>
			<p>
				<fmt:formatDate var="date" pattern="dd-MM-yyyy"
					value="${review.date}" />
				${date}, About <a
					href="${contextPath}/driver-${review.userTarget.username}">
					${review.userTarget.firstName}&nbsp;${review.userTarget.lastName} </a>
			</p>
			<br>
			<p>${review.comment}</p>
		</div>
	</div>
</li>