
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Project Reports</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Lobster|Montserrat|Pacifico" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/override.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<style type="text/css">
	h1{
	color: white;
	text-align: center;}
	h4{
	color: #004040;
	text-align: left;
	}
	h5{
	color: red;
	text-align: left;
	}
	p{
	color: #C0FF20;
	font-size: 18px;}
	body { 
background-image: url(background.jpg); 
 
}
	</style>
</head>

<body>

    <div class="container">
       <div class="row">
            <h1>Project2 - Fabflix</h1>
			<h2 style="color: #FF4060; text-align:center">Group1: Thuc Huyen, Joseph L Nguyen, Lan Tran</h2>
        </div>
		</br>
		</br>
		<p>Our group uses substring matching pattern <span style="color:#FF6000;">'%AN%'</span> in the basecode. when the users search the movie by Title, Star's First Name, Star's Last Name, and Director, the query will return all movies that have string attribute contains the substring pattern, and case is insensitive. Below are some search result in our Fablix website </p>
		</br>
		
        <div class="row">
            <div class="well well-xs">
                <h3 role="button" data-toggle="collapse" data-target="#demo1">Search movie by Movie's Title<span class="caret"></span></h3>
                <div id="demo1" class="collapse">
				
				
				
                    <div class="row">
					<h5> Seaching with substring "ter" in "Terminator"</h5>
                        <div class="col-xs-12 col-md-7 col-lg-8"><img class="img-responsive" alt="Responsive image" src="ter.PNG" /> 
						</div>
                            <a href="https://docs.google.com/spreadsheets/d/1DQHAH6YhfDJ-wvShBKlJuqkxqy_1Hf7PmUwvecxls3g/edit#gid=31292999">View the movie list <i class="fa fa-link" aria-hidden="true"></i></a>
					</div>
				</div>
	
			</div>
		</div>
		
		
			
		
        <div class="row">
            <div class="well well-xs">
                <h3 role="button" data-toggle="collapse" data-target="#demo2">Search movie by Star's First Name <span class="caret"></span></h3>
                <div id="demo2" class="collapse">
				
                    <div class="row">
						<h5> Seaching with substring "kris" in "Kristen"</h5>
						<div class="col-xs-12 col-md-7 col-lg-8"><img class="img-responsive" alt="Responsive image" src="starf_kris.PNG" /> 
						</div>
							<a href="https://docs.google.com/spreadsheets/d/1BEhTtjBE6R5sDnbOwUIQpbSDcLlnSCGOPcf8GA4Lbwc/edit#gid=1345242852">View movies with star's first name having substring "Kris" <i class="fa fa-link" aria-hidden="true"></i></a>
                       
					</div>
				</div>
			</div>
		</div>
		
                    
                    
     
        <div class="row">
            <div class="well well-xs">
                <h3 role="button" data-toggle="collapse" data-target="#demo3">Search movie by Star's Last Name <span class="caret"></span></h3>
                <div id="demo3" class="collapse">
				<h5> Seaching with substring "ride" in "Drive"</h5>
                    <div class="row">
                        <div class="col-xs-12 col-md-7 col-lg-8"><img class="img-responsive" alt="Responsive image" src="starl_river.PNG" /> 
						</div>
							<a href="https://docs.google.com/spreadsheets/d/1oSPVPBkMilP6_DugfSa73SoVxH6ZqdPMj-zu6RN5Yp4/edit#gid=718466304">View movies with star's last name having substring "ride" <i class="fa fa-link" aria-hidden="true"></i></a>
                    
					</div>
				</div>
			</div>
		</div>
		
        <div class="row">
            <div class="well well-xs">
                <h3 role="button" data-toggle="collapse" data-target="#demo4">Search movie by Director name <span class="caret"></span></h3>
                <div id="demo4" class="collapse">
				<h5> Seaching with substring "jeff" in "Jeff Schaffler"</h5>
                    <div class="row">
                        <div class="col-xs-12 col-md-7 col-lg-8"><img class="img-responsive" alt="Responsive image" src="director_jeff.PNG" /> </div>
                       
                            <a href="https://docs.google.com/spreadsheets/d/1HcV9eCWnI9NhFF7myKPiYiQEP9aBy8Xjz4bTJ0gRu5c/edit#gid=99542463">View movies with director name <i class="fa fa-link" aria-hidden="true"></i></a>
                       
					</div>
				</div>
			</div>
		</div>
		
</div>

</body>

</html>