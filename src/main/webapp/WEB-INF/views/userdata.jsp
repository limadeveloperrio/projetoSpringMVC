<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Minha Conta</title>

<!-- referência do bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>

	<jsp:include page="/WEB-INF/components/menu.jsp" />
	<jsp:include page="/WEB-INF/components/mensagens.jsp" />

	<div class="container mt-3">

		<h4>Minha Conta</h4>
		Dados do usuário autenticado no sistema.
		<hr />
		
		<div class="row">
			<div class="col-md-6">
			
				<table class="table table-bordered">
					<tr>
						<td>ID do usuário:</td>
						<td><strong>${usuario_autenticado.idUsuario}</strong></td>
					</tr>
					<tr>
						<td>Nome do usuário:</td>
						<td><strong>${usuario_autenticado.nome}</strong></td>
					</tr>
					<tr>
						<td>Email:</td>
						<td><strong>${usuario_autenticado.email}</strong></td>
					</tr>
				</table>
			
			</div>
		</div>

	</div>


	<!-- referência do bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
