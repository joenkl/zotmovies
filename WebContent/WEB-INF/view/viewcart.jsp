<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Cart</title>

</head>

<body>
	<div id=successUpdate>
        	<h3> Your Cart: </span></h3>
        	<div id="failUpdate" style="color:#FF0000; padding-bottom:10px"></div>
	        <table class="table table-bordered table-inverse">
				  <thead>
				    <tr>
				    	<th>Quantity: </th>
				    	<th>Price: </th>
				      	<th>Movie ID:</th>
				      	<th>Title: </th>
				      	<th></th>
				    </tr>
				  </thead>
				  
				  <tbody>
				  <c:set var="totalQ" value="0"></c:set>
				  <c:forEach var="c"  items='<%= session.getAttribute("cart") %>' varStatus="status">
				    <tr>
				    	<c:set var="totalQ" value="${totalQ +  c.quantity}"/>
				    	<th>${c.quantity}</th>
				    	<th>$3.0</th>
				      	<td>${c.movieId}</td>
				      	<td>${c.movieTitle}</td>
				      	<td><input class ="control-label" id="item${status.index}" type="text" style="width:30px"> 
				      		<button onclick="updateCart(${c.movieId}, 'item${status.index}')" class='btn btn-primary btn-sm' id ="update-quantity-checkout" type='submit'>
				      			Update</button>
		      				<button  onclick="deleteItem(${c.movieId}, 'item${status.index}')" 
		      					class='btn btn-danger btn-sm' id ="update-quantity-checkout" type='submit'><span class="glyphicon glyphicon-trash"></span></button>
		      			</td>
				    </tr>
			     </c:forEach>
			    </tbody>
	    	</table>
	    	<h3>Total Cost: $${totalQ * 3.0}</h3>
        </div>
</body>
</html>