
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

	

</body>

</html>
