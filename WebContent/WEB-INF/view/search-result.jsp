<!DOCTYPE html>
<html lang="en">

<%@ include file="header.jsp"%>

<body>

    <!-- Page Content -->
    <div class="container">

        <!-- Page Header -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Search
                     <small style="float: right"> and: 
                        <select>
                            <option value="mostRecent">Most recent</option>
                            <option value="old">Old</option>
                        </select>
                    </small>

                    <small style="float: right">Sort by: 
                        <select>
                            <option value="AZ">A to Z</option>
                            <option value="ZA">Z to A</option>
                        </select>
                    </small>
                </h1>
            </div>
        </div>
        <!-- /.row -->

        <!-- Projects Row -->
        <%@ include file="movies-list-with-full-info.jsp"%>

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
                     <li class="">
                        <a href="#">2</a>
                    </li>
                     <li class="">
                        <a href="#">3</a>
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
        <%@ include file="footer.jsp"%>

    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>