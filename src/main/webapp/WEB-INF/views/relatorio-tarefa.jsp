<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Relat�rio</title>

<!-- refer�ncia do bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>

	<jsp:include page="/WEB-INF/components/menu.jsp" />
	<jsp:include page="/WEB-INF/components/mensagens.jsp" />

	<div class="container mt-3">

		<h4>Relat�rio de Tarefas</h4>
		Informe o per�odo de datas para gerar o relat�rio de tarefas da sua agenda.
		<hr />
		
		<form method="post" action="relatorio-tarefas">

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
					<input type="submit" value="Gerar Relat�rio"
						class="btn btn-success" />
				</div>

			</div>

		</form>

	</div>


	<!-- refer�ncia do bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
