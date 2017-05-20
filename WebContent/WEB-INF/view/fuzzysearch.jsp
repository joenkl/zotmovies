<%@ include file="header.jsp"%>
<c:url var="home" value="/" scope="request" />
<style> 
#search-box, #searchResult{
	width:30%;
}
</style>
<body>
	<div class="container">
		<form class="form-group has-feedback" id="search-box">
			<input type="text" class="form-control" name="search-bar" placeholder="Search...." />
			<i class="glyphicon glyphicon-search form-control-feedback"></i>
		</form>
		
		<ul class="list-group" id="searchResult">
			
		</ul>
	</div>
</body>

<script>
$(document).ready(function() {
    $('[name=search-bar]').on('keyup keypress', function() {
        var query = $("input").val();
        if (query) {
            $.ajax({
            	url: "${home}api/search?title=" + query,
            	method:"GET",
                success: function(data) {
                   $("#searchResult").empty();
                   for(var i in data){
                	   if(i == 5) return false;
                	   $("#searchResult").append("<li class='list-group-item'>" +
                               "<p>" + data[i]['title'] + "</p>" +
                               "</li>");
                       }
                }
                });
        } else
            $("ul").empty();
    });
});
</script>
