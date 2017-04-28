<%@ include file="header.jsp"%>
<c:catch var="exception">
	<c:set var="tryCatch" value="${param.column}" />
</c:catch>
<c:if test="${empty exception && not empty tryCatch}">
	<c:set var="thisUrl" value="${thisUrl}&column=${param.column}" />
</c:if>

<c:catch var="exception">
	<c:set var="tryCatch" value="${param.sort}" />
</c:catch>
<c:if test="${empty exception && not empty tryCatch}">
	<c:set var="thisUrl" value="${thisUrl}&sort=${param.sort}" />
</c:if>

<c:set var="next" value="${thisUrl}&page=${activePage+1}" />
<c:set var="previous" value="${thisUrl}&page=${activePage-1}" />

<c:catch var="exception">
	<c:set var="tryCatch" value="${currentPage}" />
</c:catch>
<c:if
	test="${empty exception && not empty tryCatch && tryCatch == \"index\"}">

	<c:set var="next" value="./index?page=${activePage+1}" />
	<c:set var="previous" value="./index?page=${activePage-1}" />
</c:if>



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