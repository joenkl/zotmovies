<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html lang="en">
<%@ include file="_dashboard-header.jsp"%>
<body>
    <!-- Page Content -->
    <div class="container">

        <!-- Page Header -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">
                    <!-- <small>Secondary Text</small> -->
                </h1>
            </div>
        </div>
        <!-- /.row -->

        <!-- Projects Row -->
        <div class="row">
            <div class="col-md-6">
                <a href ="${pageContext.request.contextPath}/_add-new-star" class="btn btn-danger btn-lg btn-block">Add New Star</a>
            </div>
            <div class="col-md-6">
                <a href ="${pageContext.request.contextPath}/_add-new-movie" class="btn btn-danger btn-lg btn-block">Add New Movie</a>
            </div>
        </div>
        <!-- /.row -->
        <hr>
        <!-- Projects Row -->
        <div class="row">
            <div class="col-md-6">
                <a href ="${pageContext.request.contextPath}/_edit-movie" class="btn btn-danger btn-lg btn-block">Edit Movie</a>
            </div>
            <div class="col-md-6">
                <a href ="${pageContext.request.contextPath}/_metadata" class="btn btn-danger btn-lg btn-block">Retrieving MetaData</a>
            </div>
        </div>
        
        <hr>
        <div class="row">
            <div class="col-md-12">
              	<center><img src="https://c1.staticflickr.com/5/4187/33846839094_776a72214d.jpg" class="img-fluid" alt="Responsive image">
            </center>
            </div>
        </div>

        <!-- Footer -->
       <%@ include file="footer.jsp" %>
    </div>
</body>
</html>
