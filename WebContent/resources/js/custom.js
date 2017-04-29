var addToCart = function(movieid, title){
			$.ajax({
				url: "./shopping-cart/addcart?movieId=" + movieid +"&name=" + title,
				method: "POST",
                success: function (response) {
                	$("#successAdd").html(response);
                	$("#successAdd").addClass("alert alert-success offset4 span4");
                	$("#successMsg").fadeIn(100);
                	$("#successMsg").html("Successfuly add movie into cart");
                	$("#successMsg").fadeOut(1000);
                }
			});
		}






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


