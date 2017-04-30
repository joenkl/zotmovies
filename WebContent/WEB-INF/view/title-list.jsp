<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
	<div class="row text-center">
		<div class="col-lg-12">
			<ul class="menu-list">
				<c:forEach var="i" begin="0" end="9">
					<c:out value="${i}"/>
				</c:forEach>

			</ul>
		</div>
	</div>
	
	<div class="row text-center">
		<div class="col-lg-12">
			<ul class="menu-list">
				<li><a href="./browse-titles=A">A</a></li>
				<li><a href="./browse-titles=B">B</a></li>
				<li><a href="./browse-titles=C">C</a></li>
				<li><a href="./browse-titles=D">D</a></li>
				<li><a href="./browse-titles=E">E</a></li>
				<li><a href="./browse-titles=F">F</a></li>
				<li><a href="./browse-titles=G">G</a></li>
				<li><a href="./browse-titles=H">H</a></li>
				<li><a href="./browse-titles=I">I</a></li>
				<li><a href="./browse-titles=J">J</a></li>
				<li><a href="./browse-titles=K">K</a></li>
				<li><a href="./browse-titles=L">L</a></li>
				<li><a href="./browse-titles=M">M</a></li>
				<li><a href="./browse-titles=N">N</a></li>
				<li><a href="./browse-titles=O">O</a></li>
				<li><a href="./browse-titles=P">P</a></li>
				<li><a href="./browse-titles=Q">Q</a></li>
				<li><a href="./browse-titles=R">R</a></li>
				<li><a href="./browse-titles=S">S</a></li>
				<li><a href="./browse-titles=T">T</a></li>
				<li><a href="./browse-titles=U">U</a></li>
				<li><a href="./browse-titles=V">V</a></li>
				<li><a href="./browse-titles=W">W</a></li>
				<li><a href="./browse-titles=X">X</a></li>
				<li><a href="./browse-titles=Y">Y</a></li>
				<li><a href="./browse-titles=Z">Z</a></li>
			</ul>
		</div>
	</div>
</div>