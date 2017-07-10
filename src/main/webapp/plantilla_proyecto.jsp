<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="head.jsp"></jsp:include>
<body>

   <form method="GET" action="detalle_proyecto"  >

	<jsp:include page="header.jsp"></jsp:include>  
    
	<section class="container">
		<div class="section">
			<h6>Hola ${usuario.nombre}</h6>
			<h5>Aquí está la lista de Proyectos:</h5>

			<table class="responsive-table highlight light-green lighten-5 z-depth-3">
				<thead>
					<tr>
						<th>Id Usuario</th>
						<th>Id Proyectos</th>
						<th>Código</th>
						<th>Título</th>
						<th>Fecha de Inicio</th>
						<th>Estado</th>
						<th>Detalle</th>
					</tr>
				</thead>
		
				<c:forEach var="proy" items="${listaProyectosMostrar}"
					varStatus="counter">

					<tr>
						<td>${proy.uid}.- ${usuario.nombre} ${usuario.apellido}</td>
						<td>${proy.pid}</td>
						<td>${proy.codigo}</td>
						<td>${proy.titulo}</td>
						<td>${proy.fechainicio}</td>
						<td>${proy.estado}</td>
						<td><a href="detalle_proyecto?pid=${proy.pid}" class="btn btn-floating"><i class="material-icons">list</i></a></td>
					</tr>

				</c:forEach>

				</tbody>
			</table>
			  <ul class="pagination">
			    <li class="disabled"><a href="#!"><i class="material-icons">chevron_left</i></a></li>
			    <li class="active"><a href="#!">1</a></li>
			    <li class="disabled"><a href="#!">2</a></li>
			    <li class="disabled"><a href="#!">3</a></li>
			    <li class="disabled"><a href="#!">4</a></li>
			    <li class="disabled"><a href="#!">5</a></li>
			    <li class="disabled"><a href="#!"><i class="material-icons">chevron_right</i></a></li>
			  </ul>
		</div>
	</section>

	<jsp:include page="footer.jsp"></jsp:include>
	
	</form>
</body>
</html>