<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<head>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">
	
	<title>Fabflix - Group 01 -CS122B - Spring 2017</title>
	
	
	<!-- Bootstrap Core CSS -->
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet">
	
	<!-- Custom CSS -->
	<link href="${pageContext.request.contextPath}/resources/css/3-col-portfolio.css"rel="stylesheet">
	
	<!-- Login Form CSS -->
	<link href="${pageContext.request.contextPath}/resources/css/login.css"rel="stylesheet">
	
	<!-- menu-list -->
	<link href="${pageContext.request.contextPath}/resources/css/menu-list.css"rel="stylesheet">
	
	<!-- jQuery -->
	<script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	
	
	<script src="${pageContext.request.contextPath}/resources/js/custom.js"></script>

		
	<nav class="navbar navbar-inverse navbar-fixed-top admin-dashboard" style="background-color: #c0392b" role="navigation">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${pageContext.request.contextPath}/_dashboard"><span class="glyphicon glyphicon-tasks"></span> Admin Dashboard</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="navbar-collapse">
				<ul class="nav navbar-nav">
					<c:choose>
						<c:when test="${empty isAdmin}">
							<li><a href="#"> <span class="glyphicon glyphicon-log-in"></span> Login</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${pageContext.request.contextPath}/_add-new-star">Add New Star</a></li>
							<li><a href="${pageContext.request.contextPath}/_add-new-movie">Add New Movie</a></li>
							<li><a href="${pageContext.request.contextPath}/_edit-movie">Edit Movie</a></li>
							<li><a href="${pageContext.request.contextPath}/_metadata">MetaData</a></li>
							<li><a href="#" style="color:#FFEB3B"><span class="glyphicon glyphicon-user"></span> Hello, ${adminFN}</a></li>
							<li><a href="${pageContext.request.contextPath}/_dashboard-logout"> <span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
						</c:otherwise>
					</c:choose>
					<li><a style="color:#FFEB3B" href="${pageContext.request.contextPath}/index"><span class="glyphicon glyphicon-home"></span> Fabflix</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container -->
	</nav>
	
	
	<!-- Navigation -->

</head>

