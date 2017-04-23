<!DOCTYPE html>
<html>
<head>
<title>Login - input form</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<form action="processLoginForm" method="POST">
			<div class="form-group">
				<label for="InputEmail">Email address</label> 
				<input autofocus
					type="email" class="form-control" id="exampleInputEmail1"
					aria-describedby="emailHelp" name="email" placeholder="Enter email"> <small
					id="emailHelp" class="form-text text-muted">We'll never
					share your email with anyone else.</small>
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">Password</label> 
				<input
					type="password" class="form-control" name="password" id="exampleInputPassword1"
					placeholder="Password">
			</div>

			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
</body>
</html>