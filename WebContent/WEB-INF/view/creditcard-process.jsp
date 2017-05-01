<!DOCTYPE html>
<html lang="en">

<%@ include file="header.jsp"%>

<body>
	<!-- Page Content -->
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="login-container">
					
					<h3>Payment Information</h3>
					<p align="center" style="color:red;"> ${message} </p>
					<form id="creditcard-form" action="./credit-card-process" method="post" role="form">
						
						<div class='form-row'>
		                    <div class='col-xs-12 form-group card required'>
		                        <label class='control-label'>Card Number</label>
		                        <input type="text" name="cardnumber" class='form-control card-number' size='20' required>
		                    </div>
		                </div>
						
						<div class='form-row'>
		                    <div class='col-xs-12 form-group expiration required'>
		                        <label class='control-label'>First Name</label>
		                        <input type="text" name="fName" required>
		                    </div>
	                    </div>
	                    
	                    <div class='form-row'>
		                     <div class='col-xs-12 form-group expiration required'>
		                        <label class='control-label'>Last Name</label>
		                        <input type="text" name="lName" required>
		                    </div>
	                    </div>
	                    
						<div class='form-row'>
		                    <div class='col-xs-4 form-group expiration required'>
		                        <label class='control-label'>Day</label>
		                        <input class='form-control card-exp-day' name="dd" placeholder='DD' size='2' type='text' required>
		                    </div>
		
		                    <div class='col-xs-4 form-group expiration required'>
		                        <label class='control-label'>Month</label>
		                        <input class='form-control card-expi-month' name="mm" placeholder='MM' size='2' type='text' required>
		                    </div>
		
		                    <div class='col-xs-4 form-group expiration required'>
		                        <label class='control-label'>Year</label>
		                        <input class='form-control card-exp-year' name="yyyy" placeholder='YYYY' size='4' type='text' required>
                    		</div>
               			 </div>
						<input type="submit" name="cc-submit" class="form-control btn btn-primary place-order-button" value="Submit">
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