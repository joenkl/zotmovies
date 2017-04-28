<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

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
                <p align="center" style="color:red;"> ${message} </p>
                <a style="width: 20%; float:right" class="form-control btn btn-primary place-order-button" href="./shopping-cart/payment-process">Process to Checkout »</a>
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
				      	<td><input class ="control-label" id="item${status.index}" type="text" style="width:30px"> 
				      		<button onclick="updateCart(${c.movieId}, 'item${status.index}')" class='btn btn-primary btn-sm' id ="update-quantity-checkout" type='submit'>Update</button>
		      				<button style="float: right" onclick="deleteItem(${c.movieId}, 'item${status.index}')" class='btn btn-danger btn-sm' id ="update-quantity-checkout" type='submit'>Delete</button>
		      			</td>
				    </tr>
			     </c:forEach>
			    </tbody>
	    	</table>
        </div>
      
        
       
	<%@ include file="footer.jsp"%>

    </div>
    <!-- /.container -->

</body>

</html>
