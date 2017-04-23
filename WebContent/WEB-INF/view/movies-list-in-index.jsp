<c:forEach var="movie" items="${listMovies}" varStatus="status">

			<c:if test="${status.index == 0 or status.index % 3 == 0}">
				<div class="row" name="${status.index}"> </c:if>

					<div class="col-md-4 movie-post">
						<a href="./movie-info.html"> <img class="img-responsive"
							src="${movie.banner_url}" alt="banner_url">
						</a>
						<h3>
							<a href="./movie-info.html">${movie.title} - ${movie.year}</a>
						</h3>
					</div>
					
					<c:if test="${(status.index + 1) % 3 == 0}">
			</div> <!-- Close ROW -->
					</c:if>
</c:forEach>