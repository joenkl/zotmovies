
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Fabflix - Group 01 -CS122B - Spring 2017</title>

<!-- Bootstrap Core CSS -->
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link
	href="${pageContext.request.contextPath}/resources/css/3-col-portfolio.css"
	rel="stylesheet">

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="./index.html">Fabflix</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="navbar-collapse">
			<ul class="nav navbar-nav">
				<li><a href="./search">Search</a></li>
				<li><a href="./genres">Genres</a></li>
				<li><a href="./titles">Titles</a></li>
				<li><a href="#">Checkout Cart</a></li>
				<li><a href="./login">Login</a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container -->
</nav>

<!-- Navigation -->

</head>

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
		<c:forEach var="movie" items="${listMovies}" varStatus="status">

			<c:if test="${status.index == 0 or status.index % 3 == 0}">
				<div class="row" name="${status.index}">
			</c:if>

			<div class="col-md-4 movie-post">
				<a href="./movie-info.html"> <img class="img-responsive"
					src="${movie.banner_url}" alt="banner_url">
				</a>
				<h3>
					<a href="./movie-info.html">${movie.title} - ${movie.year}</a>
				</h3>
			</div>

			<c:if test="${(status.index + 1) % 3 == 0}">
	</div>
	</c:if>

	</c:forEach>
	<!-- /.row -->



	<hr>

	<!-- Footer -->
	<footer>
		<div class="row">
			<div class="col-lg-12">
				<p>Copyright &copy; Fabflix - UCI - CS122B Spring 2017- Group
					01: Thuc Huyen, Joseph L. Nguyen, Lan Tran</p>
			</div>
		</div>
		<!-- /.row -->
	</footer>

	</div>
	<!-- /.container -->

	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

</body>

</html>
