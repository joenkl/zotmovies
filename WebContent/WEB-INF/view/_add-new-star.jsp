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
                <h1 class="page-header">New Star's Information
                    <!-- <small>Secondary Text</small> -->
                </h1>
            </div>
        </div>
        <!-- /.row -->

        <!-- Projects Row -->
        <div class="row">
            <form id="newStar" action="_new-star-confirmation" method="post">
                <div class="form-group row">
                    <div class="col-md-5 ">
                        <label>First Name</label>
                        <input class="form-control" type="text"  name="first_name"/>
                    </div>
                    <div class="col-md-5">
                        <label>Last Name</label>
                        <input class="form-control" type="text"  name="last_name"/>
                    </div>
                </div>
                <div class ="form-group row">
                     <div class="col-xs-2 ">
                        <label>Date of Birth</label>
                        <input class="form-control" type="date" data-date-format="yyyy-mm-dd" name="dob"/>
                    </div>
                    <div class="col-lg-8 ">
                        <label>Photo URL</label>
                        <input type="text" class="form-control" name="photo_url" placeholder="http://"/>
                    </div>
                </div>

      
                 <button type="submit" class="btn btn-danger btn-lg">Submit</button>
            </form>
        </div>


	<%@ include file="footer.jsp"%>


</body>

</html>
