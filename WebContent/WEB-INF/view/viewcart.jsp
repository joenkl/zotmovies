<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Cart</title>

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
                	$("#failUpdate").html("Cannot update quantity with movie: "+ movieid);
                }               
			});
		}
		
		var deleteItem = function(movieid){
			$.ajax({
				url: "./shopping-cart/deleteItem?movieId=" + movieid,
				method: "POST",
                success: function (response) {
                	$("#successUpdate").html(response);
                },
			
                error: function(){
                	$("#failUpdate").html("Cannot delete movieID: "+ movieid);
                }               
			});
		}
</script>

</head>

<body>
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
	    	<div style="float: right; margin-top: 20px"><h3>Total: $${totalCost}</h3></div>
        </div>
</body>
</html>