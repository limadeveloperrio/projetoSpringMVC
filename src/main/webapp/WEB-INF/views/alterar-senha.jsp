<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alterar Senha</title>

<!-- referência do bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>

	<jsp:include page="/WEB-INF/components/menu.jsp" />
	<jsp:include page="/WEB-INF/components/mensagens.jsp" />

	<div class="container mt-3">

		<h4>Alterar senha de acesso</h4>
		Utilize o formulário abaixo para alterar sua senha de acesso ao sistema.
		<hr />
		
		<form method="post" action="atualizar-senha">
		
			<div class="row">
				<div class="col-md-4">
					<label>Senha atual:</label>
					<form:input path="dto.senhaAtual" type="password" class="form-control" required="required"/>
				</div>
				<div class="col-md-4">
					<label>Nova senha:</label>
					<form:input path="dto.novaSenha" type="password" class="form-control" required="required"/>
				</div>
				<div class="col-md-4">
					<label>Confirme a nova senha:</label>
					<form:input path="dto.novaSenhaConfirmacao" type="password" class="form-control" required="required"/>
				</div>
			</div>	
			
			<div class="row mt-3">
				<div class="col-md-12">
					<input type="submit" value="Salvar alterações" class="btn btn-success"/>
				</div>
			</div>
		
		</form>

	</div>	

	<!-- referência do bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
