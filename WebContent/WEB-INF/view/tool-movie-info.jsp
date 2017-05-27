
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Page Content -->
<!-- Projects Row -->
<div class="container-fluid">

	<h5>
		<strong>${movie.title}- ${movie.year}</strong>
	</h5>
	<center><button class="btn btn-success btn-md add-to-cart"
		data="${movie.title}" movieid="${movie.id}" style="margin: 10px">
		Add to cart<span class="glyphicon glyphicon-shopping-cart"></span>
	</button></center>
	<div id="successMsg" style="font-weight: bold"></div>


	<ul class="movie-info list-inline">
		<li><center><img class="img-responsive" src="${movie.banner_url}" alt=""
			width="100" height="100"></center></li>
		<li>ID: ${movie.id}</li>
		<li>Price: $3.0</li>
		<li>Director:${movie.director}</li>
		<li>Stars: <c:forEach var="star" items="${listStars}">
				<a
					href="./getStarInfo?id=${star.id}&fn=${star.first_name}
									&ln=${star.last_name}&dob=${star.dob}&photo_url=${star.photo_url}">
					${star.first_name} ${star.last_name}</a>
			</c:forEach>
		</li>
		<li>Genres:<c:forEach var="genre" items="${listGenres}">
				<a href="./browseGenre?genre=${genre.name}"> ${genre.name} </a>
			</c:forEach>
		</li>

	</ul>

	<center><a class="btn btn-primary btn-sm" href="${movie.trailer_url}">Watch
		Trailer <span class="glyphicon glyphicon-chevron-right"></span>
	</a></center>




</div>
<!-- /.row -->
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>


<script src="${pageContext.request.contextPath}/resources/js/custom.js"></script>



