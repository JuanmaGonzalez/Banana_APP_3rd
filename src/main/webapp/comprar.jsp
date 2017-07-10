<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="head.jsp"></jsp:include>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<section class="container">

		<div class="section">
			<h5>Escoge tu cosmético favorito:</h5>
			<div class="row">
				
				<c:if test="${not empty error}">
				<div class="card-panel">
	      			<span class="red-text text-darken-2">${error}</span>
	    		</div>
	    		</c:if>
	    		
				<form method="POST" class="col s12">
					
					<div class="row">
						<div class="input-field col s6">
							<select name="cosmetico" id="cosmetico" class="browser-default">
								<option value="0" disabled selected>Escoge tu cosmético</option>
								<c:forEach var="maq" items="${listaMaquillajesAMostrar}" varStatus="counter">
									<option value="${maq.mid}">
										${maq.marca} - ${maq.precio} EUR
									</option>
								</c:forEach>
							</select>
							
						</div>
					
						<div class="input-field col s6">
							<input type="number" min="1" name="cantidad" id="cantidad" value="1" />
							<label for="cantidad">Cantidad:</label>
						</div>
					</div>

					<div class="row">
						<button class="btn waves-effect waves-light" type="submit" name="action">Comprar</button>
					</div>

				</form>
			</div>
		</div>
	</section>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>