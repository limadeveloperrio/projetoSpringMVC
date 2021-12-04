<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>	
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Acesso de Usuário</title>

<!-- referência do bootstrap CSS -->
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

							<h5>Acesso ao Sistema</h5>
							<hr />
						</div>
						
						<jsp:include page="/WEB-INF/components/mensagens.jsp" />
						
						<form method="post" action="autenticar-usuario">
							
							<label>Email de acesso:</label>
							<form:input type="email" path="dto.email" class="form-control" required="required"/>
							<br/>
								
							<label>Senha de acesso:</label>
							<form:input type="password" path="dto.senha" class="form-control" required="required"/>
							<div class="text-end">
								<small><a href="/projetoSpringMVC01/password-recover">Esqueci minha senha?</a></small>
							</div>
							
							<div class="d-grid mt-2">
								<input type="submit" value="Acessar Sistema" class="btn btn-dark"/>
							</div>
							
						</form>
						
						<div class="text-center">
							<small><a href="/projetoSpringMVC01/register">Criar conta de usuário</a></small>
						</div>

					</div>
				</div>

			</div>
		</div>

	</div>

	<!-- referência do bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>