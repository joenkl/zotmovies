<!DOCTYPE html>
<html lang="en">

<%@ include file="_dashboard-header.jsp"%>
<body>

	<!-- Page Content -->
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="login-container">
					<h3 style="color: red">Admin Login Panel</h3>
					<p align="center" style="color: #c0392b;">${message}</p>
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