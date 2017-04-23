<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Fabflix - Group 01 -CS122B - Spring 2017</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/3-col-portfolio.css" rel="stylesheet">
    
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="./index.html">Fabflix</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="navbar-collapse">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="./search.html">Search</a>
                    </li>
                    <li>
                        <a href="./genres.html">Genres</a>
                    </li>
                    <li>
                        <a href="./titles.html">Titles</a>
                    </li>
                     <li>
                         <a href="#">Checkout Cart</a>
                    </li>
                     <li>
                        <a href="./login.html">Login</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Navigation -->

</head>

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
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; Fabflix - UCI - CS122B Spring 2017- Group 01: Thuc Huyen, Joseph L. Nguyen, Lan Tran</p>
                </div>
            </div>
            <!-- /.row -->
        </footer>

    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>
