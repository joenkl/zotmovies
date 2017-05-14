<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="_dashboard-header.jsp"%>

<body>
	<c:if test="${empty isAdmin}">
	    	<c:redirect url="_dashboard-login"/>
    </c:if>
    <!-- Page Content -->
    <div class="container">

        <!-- Page Header -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Edit Movie's Information
                    <small style="color: #F64747" >* = Required</small>
                </h1>
            </div>
        </div>
        <!-- /.row -->

        <!-- Projects Row -->
        <div class="row">
            <form id="newMovie" action="_movie-confirmation" method="post">
            	<div class="form-group row">
                    <div class="col-md-5 ">
                        <label style="color: #1E824C">Which movie?</label>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-md-7">
                        <label>Movie's Title *</label>
                        <input class="form-control" type="text"  name="title" Required/>
                    </div>
                    <div class="col-md-2">
                        <label>Director *</label>
                        <input class="form-control" type="text"  name="director" Required/>
                    </div>
                    <div class="col-xs-1">
                        <label>Year *</label>
                        <input class="form-control" type="year" name="year" placeholder="YYYY" maxlength="4" Required/>
                    </div>
                </div>
                 
                 <c:set var="banner_url" value=""/>
                 <c:set var="trailer_url" value=""/>
                
                
                <div class ="form-group row">
                      <div class="col-lg-3">
                        <label style="color: #1E824C" >Genre </label>
                        <input type="text" class="form-control" name="genre"/>
                    </div>
                </div>
                
                <div class="form-group row">
                    <div class="col-md-5 ">
                        <label style="color: #1E824C">Star's Information</label>
                        <small style="color: #F64747" >(*If star has single name, enter as Last Name)</small>
                    </div>
                </div>
                
                <div class="form-group row">
                    <div class="col-md-5 ">
                        <label>First Name</label>
                        <input class="form-control" type="text"  name="first_name"/>
                    </div>
                    <div class="col-md-5">
                        <label>Last Name *</label>
                        <input class="form-control" type="text"  name="last_name" Required/>
                    </div>
                </div>
                <div class ="form-group row">
                     <div class="col-xs-2 ">
                        <label>Date of Birth *</label>
                        <input class="form-control" type="date" data-date-format="yyyy-mm-dd" name="dob" Required/>
                    </div>
                    <div class="col-lg-8 ">
                        <label>Photo URL</label>
                        <input type="text" class="form-control" name="photo_url" placeholder="http://"/>
                    </div>
                </div>
                
                
                <div class ="form-group row">
                    <div class="col-md-10">
                         <button type="submit" class="btn btn-danger btn-lg btn-block">Submit</button>
                    </div>
                </div>
            </form>
        </div>

        <%@ include file="footer.jsp"%>
    </div>
</body>

</html>
