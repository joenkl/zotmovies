<!DOCTYPE html>
<html lang="en">

<%@ include file="header.jsp"%>

<body>
    <!-- Page Content -->
    <div class="container">

        <!-- Page Header -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Order Confirmation
                    <!-- <small>Secondary Text</small> -->
                </h1>
            </div>
        </div>
        <!-- /.row -->

        <!-- Projects Row -->
        <div class="row">
            <h4> Your order has placed successfully</h4>
            <h4> Here is your invoice</h4>
        </div>
        
        <div class="row">
            <h4>${cusName}</h4>
            <h5>${cusEmail}</h5>
            <h5>${cusAddr}</h5>
        </div>
        
        <div class="row">
            <table class="table table-bordered table-inverse">
				  <thead>
				    <tr>
				    	<th>Order ID: </th>
				      	<th>Movie ID: </th>
				      	<th>Date: </th>
				    </tr>
				  </thead>
				  
				  <tbody>
				  <c:forEach items="${order}" var="o">
				    <tr>
				    	<th>${o.id}</th>
				      	<td>${o.movie_id}</td>
				      	<td>${o.sale_date}</td>
				    </tr>
			     </c:forEach>
			    </tbody>
	    	</table>

        </div>
        <!-- /.row -->
       <%@ include file="footer.jsp"%>
</body>

</html>
