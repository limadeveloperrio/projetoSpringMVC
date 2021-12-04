<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edição</title>

<!-- referência do bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>

	<jsp:include page="/WEB-INF/components/menu.jsp" />
	<jsp:include page="/WEB-INF/components/mensagens.jsp" />

	<div class="container mt-3">

		<h4>Edição de Tarefas</h4>
		Utilize o formulário abaixo para alterar os dados da sua tarefa.
		<hr />
		
		<form method="post" action="atualizar-tarefa">
		
			<!-- campo oculto para armazenar o ID da tarefa -->
			<form:input type="hidden" path="dto.idTarefa"/>
		
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
					<label>Descrição da tarefa:</label>
					<form:textarea path="dto.descricao" class="form-control" required="required"></form:textarea>
				</div>
				<div class="col-md-3">
					<label>Prioridade da tarefa:</label>
					<form:select path="dto.prioridade" class="form-select" required="required">
						<option value="">Escolha uma opção</option>
						<option value="BAIXA" ${dto.prioridade == 'BAIXA' ? "selected" : ""}>Prioridade Baixa</option>
						<option value="MEDIA" ${dto.prioridade == 'MEDIA' ? "selected" : ""}>Prioridade Média</option>
						<option value="ALTA"  ${dto.prioridade == 'ALTA' ? "selected" : ""}>Prioridade Alta</option>
					</form:select>
				</div>
			</div>
			
			<div class="row mt-3">
				<div class="col-md-12">
					<input type="submit" value="Salvar alterações" class="btn btn-primary"/>
				</div>
			</div>
		
		</form>

	</div>


	<!-- referência do bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
