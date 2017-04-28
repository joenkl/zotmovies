<!DOCTYPE html>
<html lang="en">

<%@ include file="header.jsp"%>

<body>

    <!-- Page Content -->
    <div class="container">

        <!-- Page Header -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Movies Titles:
                    <!-- <small>Secondary Text</small> -->
                </h1>
            </div>
        </div>
        <!-- /.row -->

        <!-- Projects Row -->
        <div class="row">
            <div class="col-md-3">
                <ul class="genres-list">
                    <li><a href="./browseTitle?startWith=[0-9]">#</a></li>
                    <li><a href="./browseTitle?startWith=A">A</a></li>
                    <li><a href="./browseTitle?startWith=B">B</a></li>
                    <li><a href="./browseTitle?startWith=C">C</a></li>
                    <li><a href="./browseTitle?startWith=D">D</a></li>
                    <li><a href="./browseTitle?startWith=E">E</a></li>
                    <li><a href="./browseTitle?startWith=F">F</a></li>
                    <li><a href="./browseTitle?startWith=G">G</a></li>
                    <li><a href="./browseTitle?startWith=H">H</a></li>
                    <li><a href="./browseTitle?startWith=I">I</a></li>
                    <li><a href="./browseTitle?startWith=J">J</a></li>
                    <li><a href="./browseTitle?startWith=K">K</a></li>
                    <li><a href="./browseTitle?startWith=L">L</a></li>
                    <li><a href="./browseTitle?startWith=M">M</a></li>
                </ul>
            </div>
            <div class="col-md-3">
                <ul class="genres-list">
                    <li><a href="./browseTitle?startWith=N">N</a></li>
                    <li><a href="./browseTitle?startWith=O">O</a></li>
                    <li><a href="./browseTitle?startWith=P">P</a></li>
                    <li><a href="./browseTitle?startWith=Q">Q</a></li>
                    <li><a href="./browseTitle?startWith=R">R</a></li>
                    <li><a href="./browseTitle?startWith=S">S</a></li>
                    <li><a href="./browseTitle?startWith=T">T</a></li>
                    <li><a href="./browseTitle?startWith=U">U</a></li>
                    <li><a href="./browseTitle?startWith=V">V</a></li>
                    <li><a href="./browseTitle?startWith=W">W</a></li>
                    <li><a href="./browseTitle?startWith=X">X</a></li>
                    <li><a href="./browseTitle?startWith=Y">Y</a></li>
                    <li><a href="./browseTitle?startWith=Z">Z</a></li>
                </ul>
            </div>
        </div>
        <!-- /.row -->

       
        <hr>

        <!-- Footer -->
       <%@ include file="footer.jsp" %>

    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>