<<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
                <h1 class="page-header">
                    <!-- <small>Secondary Text</small> -->
                </h1>
            </div>
        </div>
        <!-- /.row -->

        <!-- Projects Row -->
        <div class="row">
            <h3 style="color: green">${success-msg}</h3>
            <br>
            <h3 style="color: orange">${warning-msg}</h3>
            <br>
            <h3 style="color: red">${error-msg}<h3>
        </div>

        <%@ include file="footer.jsp"%>

    </div>
    <!-- /.container -->

</body>

</html>
