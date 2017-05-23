
<c:url var="home" value="/" scope="request" />

<style>
#searchResult{
	width: 40%;
}
</style>

<div class="container">
	<div class="row">
		<form class="form-group has-feedback" id="search-box">
			<input type="text" class="form-control" name="search-bar"
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
						$('[name=search-bar]')
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
																	if (i == 10)
																		return false;
																	$(
																			"#searchResult")
																			.append(
																					"<li class='list-group-item'>"
																							+ "<a class='movie-item' movieid="
																							+ data[i]['id']
																							+ " href='./movie-id=" 
																							+ data[i]['id'] + "'>"
																							+ "<p>"
																							+ data[i]['title']
																							+ "</p>"
																							+ "</li></a>");
																}
															}
														});
											} else
												$("#searchResult").empty();
										});
						
						$('.movie-item').on("mouseenter", hooverMovieDetails());
						
						//hooverMovieDetails();
					});
</script>
