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
	href="${pageContext.request.contextPath}/resources/css/.css"
	rel="stylesheet">
	
<link
	href="${pageContext.request.contextPath}/resources/css/login.css"
	rel="stylesheet">
	
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
		<div class="row">
			<div class="col-lg-12">
				<div class="login-container">
					<h3>Login to your account</h3>
					<form id="login-form" action="" method="post" role="form">
						<input type="text" name="email" placeholder="Email" required>
						<input type="password" name="password" placeholder="Password"
							required> </br> <input type="submit" name="login-submit"
							class="login login-submit" value="Login">
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- /.container -->

	<!-- jQuery -->


	<script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

</body>

</html>