
<c:forEach var="movie" items="${listMovies}" varStatus="status">
	<div class="row" style="padding: 10pt">
		<div class="col-lg-12 movie-post" style="border:1px #d3d3d3 solid; padding:5px">

	        <div class="col-md-4 movie-img">
	            <center><a href="./movie-info.html">
	                <img class="img-responsive" width = "50%" height = "50%" src="${movie.banner_url}" alt="${movie.title}" >
	            </a></center>
	        </div>
	        
	        <div class="col-md-8 movie-info">
	            <h3>${movie.title}</h3>
	            <h4>Year: ${movie.year}</h4>
	            <ul class="movie-info">
	                <li>ID: ${movie.id}</li>
	                <li>Director: <a href="#">${movie.director}</a></li>
	                <li>Stars: 
	                	<c:forEach var="star" items="${listStars.get(movie.id)}">
	                		<a href="#">${star}</a>
	                	</c:forEach>
	                </li>
	                <li>Genres:
	                	<c:forEach var="gen" items="${listGenres.get(movie.id)}">
	                		<a href="#">${gen} </a>
	                	</c:forEach>
	                </li>
	            </ul>
	            <a class="btn btn-primary" href="${movie.trailer_url}">Watch Trailer <span class="glyphicon glyphicon-chevron-right"></span></a>
	        </div>
    	</div>
	</div>
</c:forEach>