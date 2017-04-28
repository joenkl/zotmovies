<%@ include file="header.jsp"%>
<fmt:requestEncoding value="UTF-8" />
<body>
	<!-- Page Content -->
	<div class="container">

		<!-- Page Header -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">
					Genres
					<!-- <small>Secondary Text</small> -->
				</h1>
			</div>
		</div>
		<!-- /.row -->
		<div class="row">
			<c:forEach var="genre" items="${listOfGenres}" varStatus="status">
				<!-- Projects Row -->

				<c:if test="${status.index == 0 or status.index == 15}">
					<div class="col-md-3">
						<ul class="genres-list">
				</c:if>
				
				<li><a href="./browseGenre?genre=${genre.name}"/>${genre.name}</a></li>

				<c:if test="${status.index + 1 == 15}">
					</ul>
		</div>
		</c:if>

		<!-- /.row -->
		</c:forEach>
	</div>


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