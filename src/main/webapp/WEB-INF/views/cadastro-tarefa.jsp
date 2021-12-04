<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro</title>

<!-- refer�ncia do bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>

	<jsp:include page="/WEB-INF/components/menu.jsp" />
	<jsp:include page="/WEB-INF/components/mensagens.jsp" />

	<div class="container mt-3">

		<h4>Cadastro de Tarefas</h4>
		Preencha o formul�rio abaixo para incluir uma tarefa em sua agenda.
		<hr />
		
		<form method="post" action="cadastrar-tarefa">
		
			<div class="row">
				<div class="col-md-6">
					<label>Nome da tarefa:</label>
					<form:input path="dto.nome" type="text" class="form-control" placeholder="Digite aqui" required="required"/>
				</div>
				<div class="col-md-3">
					<label>Data da tarefa:</label>
					<form:input path="dto.data" type="date" class="form-control" required="required"/>
				</div>
				<div class="col-md-3">
					<label>Hora da tarefa:</label>
					<form:input path="dto.hora" type="time" class="form-control" required="required"/>
				</div>
			</div>
		
			<div class="row mt-3">
				<div class="col-md-9">
					<label>Descri��o da tarefa:</label>
					<form:textarea path="dto.descricao" class="form-control" required="required"></form:textarea>
				</div>
				<div class="col-md-3">
					<label>Prioridade da tarefa:</label>
					<form:select path="dto.prioridade" class="form-select" required="required">
						<option value="">Escolha uma op��o</option>
						<option value="BAIXA">Prioridade Baixa</option>
						<option value="MEDIA">Prioridade M�dia</option>
						<option value="ALTA">Prioridade Alta</option>
					</form:select>
				</div>
			</div>
			
			<div class="row mt-3">
				<div class="col-md-12">
					<input type="submit" value="Realizar Cadastro" class="btn btn-success"/>
				</div>
			</div>
		
		</form>

	</div>	

	<!-- refer�ncia do bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
