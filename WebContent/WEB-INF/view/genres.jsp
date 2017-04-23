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
				<li><a href="./search.html">Search</a></li>
				<li><a href="./genres.html">Genres</a></li>
				<li><a href="./titles.html">Titles</a></li>
				<li><a href="#">Checkout Cart</a></li>
				<li><a href="./login.html">Login</a></li>
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
					Genres
					<!-- <small>Secondary Text</small> -->
				</h1>
			</div>
		</div>
		<!-- /.row -->
		<div class="row">
			<c:forEach var="genre" items="${listOfGenres}" varStatus="status">
				<!-- Projects Row -->

				<c:if test="${status.index == 0 or status.index == 15}">
					<div class="col-md-3">
						<ul class="genres-list">
				</c:if>

				<li><a href="#">${genre.name}</a></li>

				<c:if test="${status.index + 1 == 15}">
					</ul>
		</div>
		</c:if>

		<!-- /.row -->
		</c:forEach>
	</div>


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