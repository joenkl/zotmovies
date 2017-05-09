<!DOCTYPE html>
<html lang="en">

<%@ include file="header.jsp"%>
<body>

	<!-- Page Content -->
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="login-container">
					<h3>Admin Login Panel</h3>
					<p align="center" style="color: red;">${message}</p>
					<form id="login-form" action="_process-dashboard-login" method="post"
						role="form">
						<input type="text" name="email" placeholder="Email" required autofocus>
						<input type="password" name="password" placeholder="Password"
							required> </br> 
						<button type="submit" name="login-submit">Log In</button>
					</form>
				</div>
			</div>
		</div>
		<hr>
		<%@ include file="footer.jsp"%>
	</div>
	<!-- /.container -->

	<!-- jQuery -->
</body>

</html>