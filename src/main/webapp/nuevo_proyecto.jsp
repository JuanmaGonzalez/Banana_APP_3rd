<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="head.jsp"></jsp:include>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<section class="container">


				<div class="card light-green lighten-5 z-depth-3">
					<div class="card-content">
						<span class="card-title">Añadir Nuevo Proyecto</span>
						<form action="new_project" method="post">
						
							<div class="${errorclass}%>">
								<label></label>
								<input name="titulo" value="" placeholder="Título" required>
							</div>
							
							<div class="${errorclass}%>">
								<label></label>
								<input type="date" name="fechaActivacion" value="" placeholder="fechaActivacion">
							</div>
							
							<div class="${errorclass}%>">
								<p>Estado</p>
								
								
							    <p>
							      <input name="activo" type="radio" id="activo" value="1" checked />
							      <label for="activo">Activo</label>
							    </p>
							    <p>
							      <input name="activo" type="radio" id="inactivo" value="0" />
							      <label for="inactivo">No Activo</label>
							    </p>
								
								
							</div>
							<div class="center">
								<button class="btn waves-effect waves-light light-green darken-4">Añadir</button>
							</div>	
						</form>
					</div>
				</div>

	</section>


	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>