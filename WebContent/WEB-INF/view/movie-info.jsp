<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<script>
		var addToCart = function(movieid, title){
			$.ajax({
				url: "./shopping-cart/addcart?movieId=" + movieid +"&name=" + title,
				method: "POST",
                success: function (response) {
                	$("#successAdd").html(response);
                	$("#successAdd").addClass("alert alert-success offset4 span4");
                }
			});
		}
</script>

<body>
	<!-- Page Content -->
	<div class="container">

		<!-- Page Header -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">
					Movies
					<!-- <small>Secondary Text</small> -->
				</h1>
			</div>
		</div>
		<!-- /.row -->

		<!-- Projects Row -->
		<div class="row">
			<div class="col-md-5" style="text-align: center">
				<a href="#">
					<center>
						<img class="img-responsive" src="${movie.banner_url}" alt="">
					</center>
				</a>
				<button onclick="addToCart(${movie.id}, '${movie.title}')" style="margin: 10px" class="btn btn btn-success">
					Add to Cart <span class="glyphicon glyphicon-shopping-cart"></span>
				</button>
			</div>
			<div class="col-md-7">
				<h3>${movie.title}</h3>
				<h4>Year: ${movie.year}</h4>
				<ul class="movie-info">
					<li>ID: ${movie.id}</li>
					<li>Director: <a href="#">${movie.director}</a></li>
					<li>Stars: <c:forEach var="star"
							items="${listStars.get(movie.id)}">
							<a href="#">${star}</a>
						</c:forEach>
					</li>
					<li>Genres: <c:forEach var="gen"
							items="${listGenres.get(movie.id)}">
							<a href="./genre=${gen}">${gen} </a>
						</c:forEach>
					</li>

				</ul>

				<a class="btn btn-primary" href="${movie.trailer_url}">Watch
					Trailer <span class="glyphicon glyphicon-chevron-right"></span>
				</a>
				<hr>
				<div id="successAdd" class="alert alert-success offset4 span4"></div>
			</div>
		</div>
		<!-- /.row -->

		<hr>

		<!-- Footer -->
		<%@ include file="footer.jsp"%>

	</div>
	<!-- /.container -->
</body>

</html>