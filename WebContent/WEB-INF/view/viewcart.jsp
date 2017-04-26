<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Cart</title>

<script>
		var updateCart = function(movieid){
			
			var newq = document.getElementById("quantity").value;
			$.ajax({
				url: "./shopping-cart/updatecart?movieId=" + movieid +"&quantity=" + newq,
				method: "POST",
                success: function (response) {
                	$("#successUpdate").html(response);
                	$("#successUpdate").addClass("table table-bordered table-inverse");
                }
			});
		}
</script>
</head>

<body>
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
			  <c:forEach items='<%= session.getAttribute("cart") %>' var="c">
			    <tr>
			    	<th>${c.quantity}</th>
			      	<td>${c.movieId}</td>
			      	<td>${c.movieTitle}</td>
			      	<td><input class ="control-label" name ="quantity" id="quantity" type="text" style="width:30px" value=""> 
			      		<button id ="#successUpdate" onclick="updateCart(${c.movieId})" class='btn btn-primary btn-sm' id ="update-quantity-checkout" type='submit'>Update</button>
	      			</td>
			    </tr>
		     </c:forEach>
		    </tbody>
    	</table>
</body>
</html>