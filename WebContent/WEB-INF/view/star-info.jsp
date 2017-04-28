
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<body>
	<!-- Page Content -->
	<div class="container">

		<!-- Page Header -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">
					Star Information 
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
						<img class="img-responsive" src="${star.photo_url}" alt="">
					</center>
				</a>

			</div>
			<div class="col-md-7">
				<h3>${star.first_name} ${star.last_name}</h3>
				<ul class="star-info">
					<li>First name: ${star.first_name}</li>
					<li>Last name: ${star.last_name}</li>
					<li>Date of Birth: ${star.sDOB}</li>
					<li>List of Movies: </li>

				</ul>

				<hr>
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