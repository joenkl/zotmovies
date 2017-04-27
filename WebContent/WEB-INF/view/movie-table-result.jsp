
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

<c:choose>
	<c:when test="${sort == 'a-z'}">
		<c:set var="classForTitle" value="fa fa-sort-alpha-asc" />
		<c:set var="classForYear" value="fa fa-unsorted" />
		<c:set var="nextTitleOrder" value="${thisUrl}&column=title&sort=z-a&page=${page}" />
		<c:set var="nextYearOrder" value="${thisUrl}&column=year&sort=1-9&page=${page}" />
	</c:when>

	<c:when test="${sort == 'z-a'}">
		<c:set var="classForTitle" value="fa fa-sort-alpha-desc" />
		<c:set var="classForYear" value="fa fa-unsorted" />
		<c:set var="nextTitleOrder" value="${thisUrl}&column=title&sort=a-z&page=${page}" />
		<c:set var="nextYearOrder" value="${thisUrl}&column=year&sort=1-9&page=${page}" />
	</c:when>

	<c:when test="${sort == '1-9'}">
		<c:set var="classForYear" value="fa fa-sort-numeric-asc" />
		<c:set var="classForTitle" value="fa fa-unsorted" />
		<c:set var="nextTitleOrder" value="${thisUrl}&column=title&sort=a-z&page=${page}" />
		<c:set var="nextYearOrder" value="${thisUrl}&column=year&sort=9-1&page=${page}" />
	</c:when>

	<c:when test="${sort == '9-1'}">
		<c:set var="classForYear" value="fa fa-sort-numeric-desc" />
		<c:set var="classForTitle" value="fa fa-unsorted" />
		<c:set var="nextTitleOrder" value="${thisUrl}&column=title&sort=a-z&page=${page}" />
		<c:set var="nextYearOrder" value="${thisUrl}&column=year&sort=1-9&page=${page}" />
	</c:when>

</c:choose>

<div class="container">
	<div class="table-responsive">
		<table class="table table-hover table-bordered">
			<thead>
				<tr>
					<th>id</th>
					<th>title <a href="${nextTitleOrder}" style="float: right">
							<i class="
					<c:out value="${classForTitle}"/>
					"></i>
					</a>
					</th>
					<th>year <a href="${nextYearOrder}" style="float: right">
							<i class="
					<c:out value="${classForYear}"/>
					"></i>
					</a>
					</th>
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
						<td>
							<ul>
								<c:forEach var="star" items="${listGenres.get(movie.id)}">
									<li>${star}</li>
								</c:forEach>
							</ul>
						</td>
						<td>
							<ul>
								<c:forEach var="star" items="${listStars.get(movie.id)}">
									<li>${star}</li>
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