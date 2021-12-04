<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Consulta</title>

<!-- referência do bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
	
<!-- referência do JQuery DataTables CSS -->
<link
	href="//cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css"
	rel="stylesheet">

</head>
<body>

	<jsp:include page="/WEB-INF/components/menu.jsp" />
	<jsp:include page="/WEB-INF/components/mensagens.jsp" />

	<div class="container mt-3 mb-5">

		<h4>Consulta de Tarefas</h4>
		Informe o período de datas para consultar as tarefas da sua agenda.
		<hr />

		<form method="post" action="consultar-tarefas">

			<div class="row">

				<div class="col-md-3">
					<form:input path="dto.dataMin" type="date" class="form-control"
						required="required" />
				</div>

				<div class="col-md-3">
					<form:input path="dto.dataMax" type="date" class="form-control"
						required="required" />
				</div>

				<div class="col-md-6">
					<input type="submit" value="Pesquisar Tarefas"
						class="btn btn-success" />
				</div>

			</div>

		</form>

		<c:if test="${tarefas.size() == 0}">
			<div class="alert alert-warning alert-dismissible fade show"
				role="alert">
				<strong>Aviso!</strong> Nenhuma tarefa foi encontrada dentro do
				período de datas informado.
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
		</c:if>

		<c:if test="${tarefas != null and tarefas.size() > 0}">
			<table id="consulta-tarefas" class="table table-sm table-hover table-striped mt-5">
				<thead>
					<tr>
						<th>Nome da tarefa</th>
						<th>Data</th>
						<th>Hora</th>
						<th>Descrição</th>
						<th>Prioridade</th>
						<th>Operações</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach items="${tarefas}" var="t">

						<tr>
							<td>${t.nome}</td>
							<td><fmt:formatDate pattern="dd/MM/yyyy" value="${t.data}" /></td>
							<td>${t.hora}</td>
							<td>${t.descricao}</td>
							<td>${t.prioridade}</td>
							<td>
								<a href="/projetoSpringMVC01/edicao-tarefas?id=${t.idTarefa}" 
								   class="btn btn-primary btn-sm">
									Editar
								</a> 
								<a href="/projetoSpringMVC01/excluir-tarefa?id=${t.idTarefa}"
								   onclick="return confirm('Deseja excluir a tarefa ${t.nome}?')" 
								   class="btn btn-danger btn-sm">
									Excluir
								</a>
							</td>
						</tr>

					</c:forEach>

				</tbody>
				<tfoot>
					<tr>
						<td colspan="6">Quantidade de tarefas: ${tarefas.size()}</td>
					</tr>
				</tfoot>
			</table>
		</c:if>

	</div>


	<!-- referência do bootstrap JS -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
		
	<!-- referência do jquery JS -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	
	<!-- referência do jquery DataTables JS -->
	<script src="//cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
	
	<script>
		$(document).ready( function () {
	    	$('#consulta-tarefas').DataTable({
		        language: {
		            url: '//cdn.datatables.net/plug-ins/1.10.24/i18n/Portuguese-Brasil.json'
		        }
		    });
		});
	</script>

</body>
</html>
