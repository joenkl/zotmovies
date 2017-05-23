var addToCart = function(movieid, title){
			$.ajax({
				url: "./shopping-cart/addcart?movieId=" + movieid +"&name=" + title,
				method: "POST",
                success: function (response) {
                	$("#successAdd").html(response);
                	$("#successAdd").addClass("alert alert-success offset4 span4");
                	$("#successMsg").fadeIn(100);
                	$("#successMsg").html("Your order has fully added to cart");
                	$("#successMsg").fadeOut(1000);
                }
			});
		}


$(document).ready(function() {
	$(".add-to-cart").on("click", function(e)
			{
		 		var button =  $(this);
		 		var movieID = button.attr("movieid")
		 		var data = JSON.stringify(button.attr("data"));
		 		addToCart(movieID, data);
		 		
		 		$(this).addClass("glyphicon glyphicon-ok").delay(1000).queue(function(){
				    $(this).removeClass("glyphicon glyphicon-ok").dequeue();
				});
			})
}
);


var updateCart = function(movieid, itemNum){
			var newq = document.getElementById(itemNum).value;
			$.ajax({
				url: "./shopping-cart/updatecart?movieId=" + movieid +"&quantity=" + newq,
				method: "POST",
                success: function (response) {
                	$("#successUpdate").html(response);
                },
			
                error: function(){
                	$("#failUpdate").html("Cannot update quantity with movie: "+ movieid);
                }               
			});
		}
		
var deleteItem = function(movieid){
			$.ajax({
				url: "./shopping-cart/deleteItem?movieId=" + movieid,
				method: "POST",
                success: function (response) {
                	$("#successUpdate").html(response);
                },
			
                error: function(){
                	$("#failUpdate").html("Cannot delete movieID: "+ movieid);
                }               
			});
		}

var changeNperPage = function(url,n){
		var thisUrl = url + "&n=" + n;
		//console.log(thisUrl);
		//window.location = "./index?n=" + n +"&page="+ pageNumber;
		window.location = thisUrl;
}

var hooverMovieDetails = function(){
	$('.movie-item').popover({
    	trigger: 'hover',
		title: getMovie,
    	placement: "right",
        html: true,
    	container: $('.movie-item')
    });
	
	
	function getMovie(){
		var movie =  $(this);
		var movieID = movie.attr("movieid");
		var returnData =" ";
		$.ajax({
			url :"./tool-movie-id=" + movieID,
			method: "GET",
			async : false,
			success:function(data)
			{
				returnData = data;
			},
			error: function()
			{
				returnData ="Error! Cannot get movie data"
			}
		});
		
		return returnData;
	};
}

