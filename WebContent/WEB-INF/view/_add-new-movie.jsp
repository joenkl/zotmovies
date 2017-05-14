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
                <h1 class="page-header">New Movie's Information
                    <!-- <small>Secondary Text</small> -->
                </h1>
            </div>
        </div>
        <!-- /.row -->

        <!-- Projects Row -->
        <div class="row">
            <form id="newMovie" action="_movie-confirmation" method="post">
                <div class="form-group row">
                    <div class="col-md-5">
                        <label>Movie's Title</label>
                        <input class="form-control" type="text"  name="title"/>
                    </div>
                    <div class="col-md-2">
                        <label>Director</label>
                        <input class="form-control" type="text"  name="director"/>
                    </div>
                    <div class="col-xs-1">
                        <label>Year</label>
                        <input class="form-control" type="year" name="year" placeholder="YYYY" maxlength="4"/>
                    </div>
                </div>
                <div class ="form-group row">
                    <div class="col-lg-8 ">
                        <label>Banner URL</label>
                        <input type="text" class="form-control" name="banner_url" placeholder="http://"/>
                    </div>
                </div>

                <div class ="form-group row">
                      <div class="col-lg-8 ">
                        <label>Trailer URL</label>
                        <input type="text" class="form-control" name="trailer_url" placeholder="http://"/>
                    </div>
                </div>
                
                <div class ="form-group row">
                      <div class="col-lg-3">
                        <label>Genre</label>
                        <input type="text" class="form-control" name="genre"/>
                    </div>
                </div>
                
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
                
                
                <div class ="form-group row">
                    <div class="col-md-8">
                         <button type="submit" class="btn btn-primary btn-lg btn-block">Submit</button>
                    </div>
                </div>
            </form>
        </div>

        <%@ include file="footer.jsp"%>
    </div>
</body>

</html>
