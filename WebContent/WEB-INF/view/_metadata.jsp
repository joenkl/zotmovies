<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="_dashboard-header.jsp"%>
<body>
    <!-- Page Content -->
    <div class="container">

        <!-- Page Header -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">MetaData
                    <!-- <small>Secondary Text</small> -->
                </h1>
            </div>
        </div>
        <!-- /.row -->

  
		<p>
			<c:out value ="${metaData}"/>
		</p>

        <!-- Footer -->
        <%@ include file="footer.jsp" %>

    </div>
    <!-- /.container -->

 

</body>

</html>
