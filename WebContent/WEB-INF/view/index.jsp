<!DOCTYPE html>
<html lang="en">


<%@ include file="header.jsp"%>

<body>
	<!-- Page Content -->
	<div class="container">

		<div class="row" id = "page-header">
			<div class="col-lg-12">
				<h1 class="page-header"> Movies <!-- <small>Secondary Text</small> -->
				</h1>
			</div>
		</div>
		<!-- /.row - page-header -->

		<!-- Movies Row -->
		<%@ include file="movies-list-in-index.jsp"%>
		<!-- /.row -->

		<hr>

		<!-- Footer -->	
		<%@ include file="footer.jsp" %>
	</div>
	<!-- /.container -->

	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

</body>

</html>
