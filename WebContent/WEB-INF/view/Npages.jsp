<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- get current query string -->
<c:set var="queryString" value="${requestScope['javax.servlet.forward.query_string']}"/>

<!-- if there is no query string (index) then set to default of page 1 -->
<c:if test="${empty queryString || queryString == ''}">
	<c:set var="queryString" value="page=1"/>
</c:if>

<!-- if there is no query string for page -->
<c:if test="${(empty param.page || param.page == '') && currentPage != 'index'}">
	<c:set var="queryString" value="${queryString}&page=${activePage}"/>
</c:if>

<!-- if there is no query string for n per page -->
<c:if test="${empty param.n || param.n == ''}">
	<c:set var="queryString" value="${queryString}&n=${minPage}"/>
</c:if>

<!-- get current url -->
<c:set var="currentUrl" value="./${path}?${queryString}"/>


<!--  get current page -->
<c:set var="currPage" value="page=${activePage}"/>

<!--  prepare for pagination-->
<c:set var="nextPage" value="page=${activePage + 1}"/>
<c:set var="prevPage" value="page=${activePage -1}"/>
<c:set var="next" value="${fn:replace(currentUrl, currPage, nextPage)}" />
<c:set var="previous" value="${fn:replace(currentUrl, currPage, prevPage)}" />

<!-- prepare for n per page -->
<!--  get current page -->
<c:set var="currN" value="n=${n}"/>


<div class="row">
	<div class="col-md-3 col-md-offset-9">
		<select id="view-n-per-page" class="form-control">
			<option value="${minPage}"
				<c:if test="${(minPage) == n}">
					<c:out value="selected"/> 
				</c:if>>
				View ${minPage} movies per page</option>
			<option value="${minPage*2}"
				<c:if test="${(minPage*2) == n}">
					<c:out value="selected"/> 
				</c:if>>
				View ${minPage * 2} movies per page</option>
			<option value="${minPage*3}"
				<c:if test="${(minPage*3) == n}">
					<c:out value="selected"/> 
				</c:if>>
				View ${minPage * 3} movies per page</option>
			<option value="${minPage*4}"
				<c:if test="${(minPage*4) == n}">
					<c:out value="selected"/> 
				</c:if>>
				View ${minPage * 4 } movies per page</option>
		</select>
	</div>
</div>
<hr>

<script>
	$(document).ready(function(){
		$("#view-n-per-page").on("change", function(){
			
			var selectedOption = "n=" +  ($(this)).val();
			
			//var res = str.replace("Microsoft", "W3Schools");
			var replacedUrl = "${currentUrl}".replace("${currN}",selectedOption);
			console.log(replacedUrl);
			
			 window.location = replacedUrl; 
		});
	});
</script>