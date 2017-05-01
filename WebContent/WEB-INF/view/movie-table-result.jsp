
<%@ include file="header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${not empty path && path == 'browseTitle'}">
<%@ include file="title-list.jsp"%>
</c:if>

<!-- show n per page -->
<%@ include file="Npages.jsp"%>

<!-- prepare for sorting style and which column to sort -->

<!-- if sort and column on url is empty, set it to default value passed from model -->
<c:if test="${empty param.sort || empty param.column}">
	<c:set var="currentUrl"
		value="${currentUrl}&sort=${sort}&column=${column }" />
</c:if>

<c:set var="currentSortColumn" value="sort=${sort}&column=${column}" />
<!--  <p>test currN => ${currN}</p>
<p>test current url => ${ currentUrl}</p>
<p>test currentSortColumn => ${currentSortColumn }</p> -->

<c:set var="classForTitle" value="" />
<c:set var="nextTitleOrder" value="" />
<c:set var="classForYear" value="" />
<c:set var="nextYearOrder" value="" />

<c:choose>
	<c:when test="${sort == 'a-z'}">
		<c:set var="classForTitle"
			value="glyphicon glyphicon-sort-by-alphabet" />
		<c:set var="classForYear" value="glyphicon glyphicon-sort" />
		<c:set var="nextTitleOrder" value="sort=z-a&column=title" />



		<c:set var="nextYearOrder" value="sort=1-9&column=year" />




	</c:when>

	<c:when test="${sort == 'z-a'}">
		<c:set var="classForTitle"
			value="glyphicon glyphicon-sort-by-alphabet-alt" />
		<c:set var="classForYear" value="glyphicon glyphicon-sort" />
		<c:set var="nextTitleOrder" value="sort=a-z&column=title" />
		<c:set var="nextYearOrder" value="sort=1-9&column=year" />
	</c:when>

	<c:when test="${sort == '1-9'}">
		<c:set var="classForYear" value="glyphicon glyphicon-sort-by-order" />
		<c:set var="classForTitle" value="glyphicon glyphicon-sort" />
		<c:set var="nextTitleOrder" value="sort=a-z&column=title" />
		<c:set var="nextYearOrder" value="sort=9-1&column=year" />
	</c:when>

	<c:when test="${sort == '9-1'}">
		<c:set var="classForYear"
			value="glyphicon glyphicon-sort-by-order-alt" />
		<c:set var="classForTitle" value="glyphicon glyphicon-sort" />
		<c:set var="nextTitleOrder" value="sort=a-z&column=title" />
		<c:set var="nextYearOrder" value="sort=1-9&column=year" />
	</c:when>

</c:choose>

<c:set var="nextTitleOrder"
	value="${fn:replace(currentUrl, currentSortColumn, nextTitleOrder)}" />
<c:set var="nextYearOrder"
	value="${fn:replace(currentUrl, currentSortColumn, nextYearOrder)}" />

<!--  <p>nextTitleOrder => ${nextTitleOrder }</p>
<p>nextYearOrder => ${nextYearOrder }</p> -->


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
						<button class="btn btn-success add-to-cart" data="${movie.title}" movieid="${movie.id}" >
							<span class="glyphicon glyphicon-shopping-cart"></span>
						</button>
					</td>
					<td><a href="./movie-id=${movie.id}"> <img
							class="img-responsive" width="200pt" height="200pt"
							src="${movie.banner_url}" alt="${movie.title}">
					</a></td>
					<td>${movie.id}</td>
					<td><a href="./movie-id=${movie.id}">${movie.title}</a></td>
					<td>${movie.year}</td>
					<td>${movie.director}</td>
					<td>
						<ul>
							<c:forEach var="genre" items="${listGenres.get(movie.id)}">
								<li><a href="./browseGenre?genre=${genre.name}">
										${genre.name} </a></li>
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
	
	<%@ include file="footer.jsp"%>
</div>