
<%@ include file="header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="classForTitle" value="" />
<c:set var="nextTitleOrder" value="" />
<c:set var="classForYear" value="" />
<c:set var="nextYearOrder" value="" />

<!-- if the path is searching then get all these info: -->
<c:set var="title" value="<%=request.getParameter(\"title\")%>" />
<c:set var="first_name"
	value="<%=request.getParameter(\"first_name\")%>" />
<c:set var="last_name" value="<%=request.getParameter(\"last_name\")%>" />
<c:set var="year" value="<%=request.getParameter(\"year\")%>" />
<c:set var="director" value="<%=request.getParameter(\"director\")%>" />
<c:set var="page" value="<%=request.getParameter(\"page\")%>" />

<c:set var="thisUrl"
	value="search?title=${title}&first_name=${first_name}&last_name=${last_name}&year=${year}&director=${director}" />

<!-- if it's a browseGenre page -->
<c:catch var="exception">
	<c:set var="tryCatch" value="${param.genre}" />
</c:catch>
<c:if test="${empty exception && not empty tryCatch}">
	<c:set var="thisUrl" value="./${currentPage}" />
</c:if>

<!-- if it's a browseTitle page -->
<c:catch var="exception">
	<c:set var="tryCatch" value="${param.startWith}" />
</c:catch>
<c:if test="${empty exception && not empty tryCatch}">
	<c:set var="thisUrl" value="./${currentPage}" />
</c:if>


<c:choose>
	<c:when test="${sort == 'a-z'}">
		<c:set var="classForTitle"
			value="glyphicon glyphicon-sort-by-alphabet" />
		<c:set var="classForYear" value="glyphicon glyphicon-sort" />
		<c:set var="nextTitleOrder"
			value="${thisUrl}&column=title&sort=z-a&page=${page}" />
		<c:set var="nextYearOrder"
			value="${thisUrl}&column=year&sort=1-9&page=${page}" />
	</c:when>

	<c:when test="${sort == 'z-a'}">
		<c:set var="classForTitle"
			value="glyphicon glyphicon-sort-by-alphabet-alt" />
		<c:set var="classForYear" value="glyphicon glyphicon-sort" />
		<c:set var="nextTitleOrder"
			value="${thisUrl}&column=title&sort=a-z&page=${page}" />
		<c:set var="nextYearOrder"
			value="${thisUrl}&column=year&sort=1-9&page=${page}" />
	</c:when>

	<c:when test="${sort == '1-9'}">
		<c:set var="classForYear" value="glyphicon glyphicon-sort-by-order" />
		<c:set var="classForTitle" value="glyphicon glyphicon-sort" />
		<c:set var="nextTitleOrder"
			value="${thisUrl}&column=title&sort=a-z&page=${page}" />
		<c:set var="nextYearOrder"
			value="${thisUrl}&column=year&sort=9-1&page=${page}" />
	</c:when>

	<c:when test="${sort == '9-1'}">
		<c:set var="classForYear"
			value="glyphicon glyphicon-sort-by-order-alt" />
		<c:set var="classForTitle" value="glyphicon glyphicon-sort" />
		<c:set var="nextTitleOrder"
			value="${thisUrl}&column=title&sort=a-z&page=${page}" />
		<c:set var="nextYearOrder"
			value="${thisUrl}&column=year&sort=1-9&page=${page}" />
	</c:when>

</c:choose>

<div><p align="center" id="successMsg" style="color:green;"></p></div>





<div class="container">
	<div class="table-responsive">
		<table class="table table-hover table-bordered">
			<thead>
				<tr>
					<th></th>
					<th>Poster</th>
					<th>Id</th>
					<th>Title <a href="${nextTitleOrder}" style="float: right">
							<i class="
					<c:out value="${classForTitle}"/>
					"></i>
					</a>
					</th>
					<th>Year <a href="${nextYearOrder}" style="float: right">
							<i class="
					<c:out value="${classForYear}"/>
					"></i>
					</a>
					</th>
					<th>Director</th>
					<th>List of genres</th>
					<th>List of stars</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="movie" items="${listMovies}" varStatus="status">
						<td>
							<button id="add-to-cart" data="${movie.title}"
										 class="btn btn btn-success">
										<span class="glyphicon glyphicon-shopping-cart"></span>
							</button>
						</td>
						<td>
							<a href="./movie-id=${movie.id}">
	                			<img class="img-responsive" width = "200pt" height = "200pt" src="${movie.banner_url}" alt="${movie.title}" >
                			</a>
               			</td>
						<td>${movie.id}</td>
						<td><a href="./movie-id=${movie.id}">${movie.title}</a></td>
						<td>${movie.year}</td>
						<td>${movie.director}</td>
						<td>
							<ul>
								<c:forEach var="genre" items="${listGenres.get(movie.id)}">
									<li><a href="./browseGenre?genre=${genre.name}"> ${genre.name}
									</a></li>
								</c:forEach>
							</ul>
						</td>
						<td>
							<ul>
								<c:forEach var="star" items="${listStars.get(movie.id)}">
									<li><a
										href="./getStarInfo?id=${star.id}&fn=${star.first_name}
									&ln=${star.last_name}&dob=${star.dob}&photo_url=${star.photo_url}">
											${star.first_name} ${star.last_name}</a></li>
								</c:forEach>
							</ul>
						</td>

					</tr>
				</c:forEach>
			</tbody>

		</table>
	</div>
	<%@ include file="pagination.jsp"%>
</div>