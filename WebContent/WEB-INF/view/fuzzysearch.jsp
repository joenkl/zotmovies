
<c:url var="home" value="/" scope="request" />

<style>
#searchResult{
	width: 30%;
}
</style>

<div class="container">
	<div class="row">
		<form class="form-group has-feedback" action="./tokenSearch" id="search-box">
			<input type="text" class="form-control" name="title" id="search-bar"
				placeholder="Search...." /> <i
				class="glyphicon glyphicon-search form-control-feedback"></i>
		</form>

		<ul class="list-group" id="searchResult" style="position:absolute; z-index:1">
		</ul>
	</div>
</div>

<script>
	$(document)
			.ready(
					function() {
						$('#search-bar')
								.on(
										'keyup keypress',
										function() {
											var query = $("input").val();
											if (query) {
												$
														.ajax({
															url : "${home}api/search?title="
																	+ query,
															method : "GET",
															success : function(
																	data) {
																$(
																		"#searchResult")
																		.empty();
																for ( var i in data) {
																	if (i == 5)
																		return false;
																	$(
																			"#searchResult")
																			.append(
																					"<li class='list-group-item movie-item' movieid="+data[i]['id']+">"  
																							+ "<a "
																							+ "  href='./movie-id=" + data[i]['id'] + "'>"
																							+ "<p>"
																							+ data[i]['title']
																							+ "</p>"
																							+ "</li></a>");
																	
																	$('.movie-item').on("click", function(){
																		 window.location.assign($(this).attr('href'));
																	});
																	$('.movie-item').on("mouseover", hooverMovieDetails());
																}
															}
														});
											} else
												$("#searchResult").empty();
										});
					});
</script>
