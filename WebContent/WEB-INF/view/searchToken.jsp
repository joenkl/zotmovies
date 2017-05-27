<%@ include file="header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
#tokenSearchResult{
	width: 70%;
}
</style>
<body>
	<div class="container">
		<ul class="list-group" id="tokenSearchResult">
			<c:forEach var="movie" items="${listMovies}" varStatus="status">
				<li class='list-group-item movie-item' movieid="${movie.id}"><a
					href="./movie-id=${movie.id}">${movie.title}</a></li>
			</c:forEach>

		</ul>
	</div>
	<%@ include file="footer.jsp"%>
</body>

<script>

$('document').ready(function(){
	$('.movie-item').on("click", function(){
		 window.location.assign($(this).attr('href'));
	});
	$('.movie-item').on("mouseover", hooverMovieDetails());
});
</script>
