<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<!-- Login Form CSS -->
<link href="${pageContext.request.contextPath}/resources/css/login.css"
	rel="stylesheet">

<!-- menu-list -->
<link
	href="${pageContext.request.contextPath}/resources/css/menu-list.css"
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
			<a class="navbar-brand" href="./index">Fabflix</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="navbar-collapse">
			<ul class="nav navbar-nav">
				<li><a href="./searchForm">Search</a></li>
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

