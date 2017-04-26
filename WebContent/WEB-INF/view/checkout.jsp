<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<script>
		var updateCart = function(movieid, itemNum){
			
			var newq = document.getElementById(itemNum).value;
			$.ajax({
				url: "./shopping-cart/updatecart?movieId=" + movieid +"&quantity=" + newq,
				method: "POST",
                success: function (response) {
                	$("#successUpdate").html(response);
                },
			
                error: function(){
                	$("#failUpdate").html("Cannot update quantity with movieID: "+ movieid);
                }               
			});
		}
</script>

<%@ include file="header.jsp"%>
<body>
    <!-- Page Content -->
    <div class="container">

        <!-- Page Header -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Checkout
                    <!-- <small>Secondary Text</small> -->
                </h1>
            </div>
        </div>
        <!-- /.row -->

        <!-- Projects Row -->
        <div id=successUpdate>
        	<h3> Your Cart: </h3>
        	<div id="failUpdate" style="color:#FF0000; padding-bottom:10px"></div>
	        <table class="table table-bordered table-inverse">
				  <thead>
				    <tr>
				    	<th>Quantity: </th>
				      	<th>Movie ID:</th>
				      	<th>Title: </th>
				      	<th></th>
				    </tr>
				  </thead>
				  
				  <tbody>
				  <c:forEach items='<%= session.getAttribute("cart") %>' var="c" varStatus="status">
				    <tr>
				    	<th>${c.quantity}</th>
				      	<td>${c.movieId}</td>
				      	<td>${c.movieTitle}</td>
				      	<td><input class ="control-label" id="item${status.index}" type="text" style="width:30px" value=""> 
				      		<button onclick="updateCart(${c.movieId}, 'item${status.index}')" class='btn btn-primary btn-sm' id ="update-quantity-checkout" type='submit'>Update</button>
		      				<button onclick="updateCart(${c.movieId}, 'item${status.index}')" class='btn btn-primary btn-sm' id ="update-quantity-checkout" type='submit'>Update</button>
		      			</td>
				    </tr>
			     </c:forEach>
			    </tbody>
	    	</table>
        </div>
        
        
        

		
        <!-- /.row -->
         <div class="row">
            
            <h4> Payment Information: </h4>
            <div class="col-md-5" required>
                <form accept-charset="UTF-8" action="/" class="require-validation" data-cc-on-file="false" id="payment-form" method="post"><div style="margin:0;padding:0;display:inline"><input name="utf8" type="hidden" value="âœ“" /><input name="_method" type="hidden" value="PUT" /><input name="authenticity_token" type="hidden" value="" /></div>
                
                <div class='form-row'>
                    <div class='col-xs-12 form-group required'>
                    <label class='control-label'>Name on Card</label>
                    <input class='form-control' size='4' type='text'>
                    </div>
                </div>

                <div class='form-row'>
                    <div class='col-xs-12 form-group card required'>
                        <label class='control-label'>Card Number</label>
                        <input autocomplete='off' class='form-control card-number' size='20' type='text'>
                    </div>
                </div>

                <div class='form-row'>

                    <div class='col-xs-4 form-group expiration required'>
                        <label class='control-label'>Expiration</label>
                        <input class='form-control card-exp-day' placeholder='DD' size='2' type='text'>
                    </div>

                    <div class='col-xs-4 form-group expiration required'>
                        <label class='control-label'>Month</label>
                        <input class='form-control card-expi-month' placeholder='MM' size='2' type='text'>
                    </div>

                    <div class='col-xs-4 form-group expiration required'>
                        <label class='control-label'>Year</label>
                        <input class='form-control card-exp-year' placeholder='YYYY' size='4' type='text'>
                    </div>
                </div>

                <div class='form-row'>
                    <div class='col-xs-12 form-group'>
                        <button class='form-control btn btn-primary place-order-button' type='submit'>Place Order »</button>
                    </div>
                </div>           
            </div>                   
        </div>




        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; Fabflix - UCI - CS122B Spring 2017- Group 01: Thuc Huyen, Joseph L. Nguyen, Lan Tran</p>
                </div>
            </div>
            <!-- /.row -->
        </footer>

    </div>
    <!-- /.container -->

</body>

</html>
