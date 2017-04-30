<!DOCTYPE html>
<html lang="en">

<%@ include file="header.jsp"%>

<body>

	<!-- Page Content -->
	<div class="container">



		<!-- /.row -->

		<!-- Projects Row -->
		<div class="row">
			<h3>By Numbers</h3>
			<div class="col-md-3">
				<ul class="genres-list">
					<c:forEach var="i" begin="0" end="4">
						<li><a href="./browseTitle?startWith=${i}">${i}</a></li>
					</c:forEach>

				</ul>
			</div>
			<div class="col-md-3">
				<ul class="genres-list">
					<c:forEach var="i" begin="5" end="9">
						<li><a href="./browseTitle?startWith=${i}">${i}</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<!-- /.row -->


		<!-- /.row -->

		<!-- Projects Row -->
		<div class="row">
			<h3>By Letters</h3>
			<div class="col-md-3">
				<ul class="genres-list">
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
		<%@ include file="footer.jsp"%>

	</div>
	<!-- /.container -->

	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

</body>

</html>