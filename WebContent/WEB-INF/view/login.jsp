<!DOCTYPE html>
<html lang="en">

<%@ include file="header.jsp"%>
<script src='https://www.google.com/recaptcha/api.js'></script>
<script>
function onSubmit(token) {
	document.getElementById("login-form").submit();
}
</script>
<body>

	<!-- Page Content -->
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="login-container">
					<h3>Login to your account</h3>
					<p align="center" style="color: red;">${message}</p>
					<form action="${pageContext.request.contextPath}/processLoginForm" id="login-form"  method="post"
						role="form">
						<input type="text" name="email" placeholder="Email" required autofocus>
						<input type="password" name="password" placeholder="Password"
							required> </br> 
						<button type="submit" name="login-submit"
							class="login login-submit g-recaptcha"
							data-sitekey="6LdIOyAUAAAAAOszyEIV1ZR8-6NsErjsaMhKipoY"
							data-callback="onSubmit">Log In</button>
					</form>
				</div>
			</div>
		</div>
		<hr>
		<%@ include file="footer.jsp"%>
	</div>
	<!-- /.container -->
</body>

</html>