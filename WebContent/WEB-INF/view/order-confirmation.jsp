<!DOCTYPE html>
<html lang="en">

<%@ include file="header.jsp"%>

<body>
    <!-- Page Content -->
    <div class="container">

        <!-- Page Header -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Order Comfirmation
                    <!-- <small>Secondary Text</small> -->
                </h1>
            </div>
        </div>
        <!-- /.row -->

        <!-- Projects Row -->
        <div class="row">
            <h4> Your order has been placed: </h4>
            <h2>Customer: ${cusName}</h2>
                <table class="table table-bordered table-inverse">
				  <thead>
				    <tr>
				    	<th>Quantity: </th>
				      	<th>Movie ID:</th>
				      	<th>Title: </th>
				      	<th>Order ID</th>
				    </tr>
				  </thead>
			
				  <tbody>
				  <c:forEach items="${shoppingCartList}" var="c">
				    <tr>
				    	<th>${c.quantity}</th>
				      	<td>${c.movieId}</td>
				      	<td>${c.movieTitle}</td>
				      	<td>
				      		<c:forEach items="${saleList}" var="s">
				      			<c:if test="${c.movieId == s.movie_id}">
									<p>${s.id}</p><br>				      			
				      			</c:if>
				      		</c:forEach>
				      	</td>
				    </tr>
			     </c:forEach>
			    </tbody>
	    	</table>
        </div>
        <!-- /.row -->
       <%@ include file="footer.jsp"%>
</body>

</html>
