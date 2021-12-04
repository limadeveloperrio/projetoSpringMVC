<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Esqueci minha senha</title>

<!-- refer�ncia do bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body class="bg-secondary">

	<div class="container mt-5">

		<div class="row">
			<div class="col-md-4 offset-md-4">

				<div class="card">
					<div class="card-body">

						<div class="text-center">
							<img
								src="https://www.cotiinformatica.com.br/imagens/logo-coti-informatica.png"
								class="img-fluid" />

							<h5>Recupera��o de senha</h5>
							<hr />
						</div>
						
						<jsp:include page="/WEB-INF/components/mensagens.jsp" />
						
						<form action="recuperar-senha" method="post">
													
							<label>Email de acesso:</label>
							<form:input path="dto.email" type="email" class="form-control" required="required"/>
							<br/>
							
							<div class="d-grid">
								<input type="submit" value="Recuperar Senha" class="btn btn-success"/>
							</div>
							
						</form>
						
						<div class="text-center">
							<small><a href="/projetoSpringMVC01/">Acesso ao Sistema</a></small>
						</div>

					</div>
				</div>

			</div>
		</div>

	</div>

	<!-- refer�ncia do bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>