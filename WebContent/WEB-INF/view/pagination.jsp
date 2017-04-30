<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="container">
	<div class="row text-center">
		<ul class="pagination">
			<c:if test="${(activePage - 1) > 0}">
				<li><a href="${previous}">«</a></li>
			</c:if>
			<li class="active"><a href="#">${activePage}</a></li>

			<c:if test="${!lastPage}">
				<li><a href="${next}">»</a></li>
			</c:if>
		</ul>
	</div>
</div>