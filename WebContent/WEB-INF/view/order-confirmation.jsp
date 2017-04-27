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
            <h4> Your order has been succusfull placed: </h4>
                <table class="table table-bordered table-inverse">
				  <thead>
				    <tr>
				    	<th>Movie: </th>
				      	<th>Order ID:</th>
				      	<th></th>
				      	<th></th>
				    </tr>
				  </thead>
				  
				  <tbody>
				    <tr>
				    	<th>${msg}</th>
				      	<td></td>
				      	<td></td>
				      	<td></td>
				    </tr>
			    </tbody>
	    	</table>
        </div>
        <!-- /.row -->
       <%@ include file="footer.jsp"%>
</body>

</html>
