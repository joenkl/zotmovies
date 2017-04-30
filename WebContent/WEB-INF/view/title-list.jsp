<div class="container">
	<div class="row text-center">
		<ul class="pagination">
			<c:forEach var="i" begin="0" end="9">
				<li><a href="./browseTitle?startWith=${i}">${i}</a></li>
			</c:forEach>

		</ul>
	</div>


	<div class="row text-center">
		<ul class="pagination">
			<c:forEach var="i" begin="65" end="90">

				<li><a
					href="./browseTitle?startWith=<%=Character.toChars((Integer) pageContext.getAttribute("i"))%>">
						<%=Character.toChars((Integer) pageContext.getAttribute("i"))%>
				</a></li>
			</c:forEach>

		</ul>
	</div>


	<!-- /.row -->

	<hr>
</div>