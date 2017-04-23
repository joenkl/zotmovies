<!DOCTYPE html>
<html lang="en">



<%@ include file="header.jsp"%>

<body>

	<!-- Page Content -->
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="login-container">
					<h3>Login to your account</h3>
					<form action="processLoginForm" method="POST" id="login-form" role="form">
						<input type="text" name="email" placeholder="Email" required>
						<input type="password" name="password" placeholder="Password"
							required> </br> <input type="submit" name="login-submit"
							class="login login-submit" value="Login">
					</form>
				</div>
			</div>
		</div>
		<hr>
		<%@ include file="footer.jsp"%>
	</div>
	<!-- /.container -->

	<!-- jQuery -->


	<script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

</body>

</html>