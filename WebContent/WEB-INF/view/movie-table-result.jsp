<%@ include file="header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
	<table class="table table-bordered table-hover">
		<thead>
			<tr>
				<th>id</th>
				<th>title</th>
				<th>year</th>
				<th>director</th>
				<th>list of genres</th>
				<th>list of stars</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="movie" items="${listMovies}" varStatus="status">
				<tr>
					<td>${movie.id}</td>
					<td>${movie.title}</td>
					<td>${movie.year}</td>
					<td>${movie.director}</td>
					<td>${listGenres.get(movie.id)}</td>
					<td>${listStars.get(movie.id)}</td>

				</tr>
			</c:forEach>
		</tbody>

	</table>
</div>