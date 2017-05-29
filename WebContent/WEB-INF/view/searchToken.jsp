<%@ include file="header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
#tokenSearchResult {
	width: 80%;
}

.items {
	padding: 35px;
}
</style>
<body>
	<div class="container">
		<c:choose>
			<c:when test="${empty listMovies}">
				<div class="row"><center>No results found!</center></div>
			</c:when>
			<c:otherwise>
				<ul class="list-group" id="tokenSearchResult">
					<c:forEach var="movie" items="${listMovies}" varStatus="status">
						<li class='list-group-item movie-item items' movieid="${movie.id}"><a
							href="./movie-id=${movie.id}">${movie.title}</a></li>
					</c:forEach>

				</ul>
			</c:otherwise>
		</c:choose>
	</div>
	<%@ include file="footer.jsp"%>
</body>

<script>
	$('document').ready(function() {
		$('.movie-item').on("click", function() {
			window.location.assign($(this).attr('href'));
		});
		$('.movie-item').on("mouseover", hooverMovieDetails());
	});
</script>
