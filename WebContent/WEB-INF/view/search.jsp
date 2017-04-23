<!DOCTYPE html>
<html lang="en">

<%@ include file="header.jsp"%>

<body>
    <!-- Page Content -->
    <div class="container">

        <!-- Page Header -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Advanced Search
                    <!-- <small>Secondary Text</small> -->
                </h1>
            </div>
        </div>
        <!-- /.row -->

        <!-- Projects Row -->
        <!-- /.row -->
         <div class="row" >
            <h4>Search Movies by: </h4>
            <div class="col-md-5">
                 <form class="movie-advanced-search" id = "advanceSearch" style="margin:0;padding:0;display:inline">
                    
                    <div class='form-row'>
                        <div class='col-xs-12 form-group'>
                            <label class='control-label'>Title</label>
                            <input class='form-control' type='text'>
                        </div>
                    </div>

                    <div class='form-row'>
                        <div class='col-xs-12 form-group'>
                            <label class='control-label'>Star's First Name</label>
                            <input class='form-control' type='text'>
                        </div>
                    </div>

                    <div class='form-row'>
                        <div class='col-xs-12 form-group'>
                            <label class='control-label'>Star's Last Name</label>
                            <input class='form-control' type='text'>
                        </div>
                    </div>
                    
                     <div class='form-row'>
                        <div class='col-xs-4 form-group'>
                            <label class='control-label'>Year</label>
                            <input class='form-control' type='text' size="4">
                        </div>
                    </div>

                    <div class='form-row'>
                        <div class='col-xs-12 form-group'>
                            <label class='control-label'>Director: </label>
                            <input class='form-control' type='text'>
                        </div>
                    </div>
                    
                    <div class='form-row'>
                        <div class='col-xs-12 radio'>
                            <label class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input">
                                <span class="custom-control-indicator"></span>
                                <span class="custom-control-description">Fuzzy Search (Will match spelling error to some externt)</span>
                            </label>
                        </div>
                    </div>

                     <div class='form-row'>
                        <div class='col-xs-12 radio'>
                            <label class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input">
                                <span class="custom-control-indicator"></span>
                                <span class="custom-control-description">Match Substring (Will match portions of a larger string)</span>
                            </label>
                        </div>
                    </div>

                    <div class='form-row'>
                        <div class='col-xs-12 form-group'>
                            <button class='form-control btn btn-primary place-order-button' type='submit'>Search</button>
                        </div>
                    </div>           

                </form>
            </div>                   
        </div>
        <!-- Footer -->
       <hr>
       <%@ include file="footer.jsp"%>

    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>
