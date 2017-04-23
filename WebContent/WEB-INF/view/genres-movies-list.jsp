<!DOCTYPE html>
<html lang="en">

<%@ include file="header.jsp"%>

<body>

    <!-- Page Content -->
    <div class="container">

        <!-- Page Header -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Genres: Classic
                    <small style="float: right">Sort by: 
                        <select>
                            <option value="AZ">A to Z</option>
                            <option value="ZA">Z to A</option>
                            <option value="recent">Most recent</option>
                        </select>
                    </small>
                </h1>
            </div>
        </div>
        <!-- /.row -->

        <!-- Projects Row -->
        <div class="row">
             <div class="col-md-4 movie-post">
                <a href="./movie-info.html">
                    <img class="img-responsive" src="https://images-na.ssl-images-amazon.com/images/M/MV5BNDczNzg4OTM3MV5BMl5BanBnXkFtZTcwOTQzMTEzMw@@._V1_SY1000_CR0,0,676,1000_AL_.jpg" alt="" >
                </a>
                <h3>
                    <a href="./movie-info.html">The Phantom of the Opera - 2004</a>
                </h3>
            </div>
            <div class="col-md-4 movie-post">
                <a href="#">
                    <img class="img-responsive" src="https://images-na.ssl-images-amazon.com/images/M/MV5BZTYzMjA2M2EtYmY1OC00ZWMxLThlY2YtZGI3MTQzOWM4YjE3XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_.jpg" alt="">
                </a>
                <h3>
                    <a href="#">The Aviator - 2004</a>
                </h3>
            </div>
        </div>

        <!-- Pagination -->
        <div class="row text-center">
            <div class="col-lg-12">
                <ul class="pagination">
                    <li>
                        <a href="#">&laquo;</a>
                    </li>
                    <li class="active">
                        <a href="#">1</a>
                    </li>
                    <li>
                        <a href="#">&raquo;</a>
                    </li>
                </ul>
            </div>
        </div>
        <!-- /.row -->

       
        <hr>

        <!-- Footer -->
        <%@ include file="header.jsp"%>

    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>