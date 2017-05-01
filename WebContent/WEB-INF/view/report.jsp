<%@ include file="header.jsp"%>

<body>

    <div class="container">
		<h3>You don't know exact the title of your desired movie? We got you under cover.</br>
			Whether you only know first word of movie title, or partial word of a stars' or director' name . Do not worry! We will take care of it
		</h3>
		</br>
        <div class="row">
            <div class="well well-xs">
                <h3 role="button" data-toggle="collapse" data-target="#demo1">Search movie by substring Movie's Title<span class="caret"></span></h3>
                <div id="demo1" class="collapse">
                    <div class="row">
						<h5>You know a word from a movie's title</h5>
                        <div class="col-xs-12 col-md-7 col-lg-8"><img class="img-responsive" alt="Responsive image" src="ter.PNG" /></div>   
					</div>
					<div class="row">
						<h5>You know only a partial word from a movie's title</h5>
                        <div class="col-xs-12 col-md-7 col-lg-8"><img class="img-responsive" alt="Responsive image" src="ter.PNG" /></div>   
					</div>
				</div>
			</div>
		</div>
		
		
			
		
        <div class="row">
            <div class="well well-xs">
                <h3 role="button" data-toggle="collapse" data-target="#demo2">Search movie by substring Star's First Name <span class="caret"></span></h3>
                <div id="demo2" class="collapse">
                    <div class="row">
						<h5> Searching with a substring like "kris" in "Kristen"</h5>
						<div class="col-xs-12 col-md-7 col-lg-8"><img class="img-responsive" alt="Responsive image" src="starf_kris.PNG" /> 
						</div>
					</div>
				</div>
			</div>
		</div>
		
                    
                    
     
        <div class="row">
            <div class="well well-xs">
                <h3 role="button" data-toggle="collapse" data-target="#demo3">Search movie by substring Star's Last Name <span class="caret"></span></h3>
                <div id="demo3" class="collapse">
				<h5> Searching with substring "ride" in "Drive"</h5>
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
                <h3 role="button" data-toggle="collapse" data-target="#demo4">Search movie by substring Director name <span class="caret"></span></h3>
                <div id="demo4" class="collapse">
				<h5> Searching with substring "jeff" in "Jeff Schaffler"</h5>
                    <div class="row">
                        <div class="col-xs-12 col-md-7 col-lg-8"><img class="img-responsive" alt="Responsive image" src="director_jeff.PNG" /> </div>
                       
                            <a href="https://docs.google.com/spreadsheets/d/1HcV9eCWnI9NhFF7myKPiYiQEP9aBy8Xjz4bTJ0gRu5c/edit#gid=99542463">View movies with director name <i class="fa fa-link" aria-hidden="true"></i></a>
                       
					</div>
				</div>
			</div>
		</div>
		
</div>
<%@ include file="footer.jsp"%>

</body>

</html>