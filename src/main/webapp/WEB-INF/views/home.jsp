<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>

<!-- referência do bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>

	<jsp:include page="/WEB-INF/components/menu.jsp" />

	<div class="container mt-3">
	
		<div class="row">
			<div class="col-md-8">
				<h5>Olá ${usuario_autenticado.nome}, seja bem vindo ao projeto!</h5>
			</div>
			<div class="col-md-4 text-end">
				<h5>Data atual: <strong class="text-primary"><fmt:formatDate pattern="dd/MM/yyyy" value="${dataatual}" /></strong></h5>
			</div>
		</div>
		
		<div class="row mt-3">
			<div class="col-md-4 text-success">
				<div class="card card-body">
					<div class="row">
						<div class="col-md-8"><h5>Prioridade baixa</h5></div>
						<div class="col-md-4 text-end"><h5><strong>${qtdprioridadebaixa}</strong></h5></div>
					</div>
				</div>
			</div>
			<div class="col-md-4 text-warning">
				<div class="card card-body">
					<div class="row">
						<div class="col-md-8"><h5>Prioridade média</h5></div>
						<div class="col-md-4 text-end"><h5><strong>${qtdprioridademedia}</strong></h5></div>
					</div>
				</div>
			</div>
			<div class="col-md-4 text-danger">
				<div class="card card-body">
					<div class="row">
						<div class="col-md-8"><h5>Prioridade alta</h5></div>
						<div class="col-md-4 text-end"><h5><strong>${qtdprioridadealta}</strong></h5></div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row mt-4">		
			<div class="col-md-8">
			
				<h5>Tarefas agendadas para hoje:</h5>
			
				<table class="table table-bordered table-sm table-hover table-striped">
					<thead>
						<tr>
							<th>Nome da tarefa</th>
							<th>Data</th>
							<th>Hora</th>
							<th>Prioridade</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${tarefas}" var="t">
							<tr>
								<td>${t.nome}</td>
								<td><fmt:formatDate pattern="E dd/MM/yyyy" value="${t.data}" /></td>
								<td>${t.hora}</td>
								<td>${t.prioridade}</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								Quantidade de tarefas: ${tarefas.size()}
							</td>
						</tr>
					</tfoot>
				</table>
			
			</div>
			<div class="col-md-4">
				<div id="grafico"></div>
			</div>		
		</div>
	
	</div>


	<!-- referência do bootstrap JS -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
		
	<!-- referencias para o highcharts -->
	<script src="https://code.highcharts.com/highcharts.js"></script>
	<script src="https://code.highcharts.com/highcharts-3d.js"></script>
	<script src="https://code.highcharts.com/modules/exporting.js"></script>
	<script src="https://code.highcharts.com/modules/export-data.js"></script>
	
	<!-- montando o gráfico -->
	<script>
		
		var array = [];

    	//dados do gráfico
    	array.push(['ALTA', ${qtdprioridadealta}]);
    	array.push(['MEDIA', ${qtdprioridademedia}]);
    	array.push(['BAIXA', ${qtdprioridadebaixa}]);
    	
    	new Highcharts.Chart({
            chart: {
                type: 'pie',
                renderTo: 'grafico'
            },
            title: {
                text: 'Tarefas por prioridade'
            },
            plotOptions: {
                pie: {
                    innerSize: '60%',
                    dataLabels: {
                        enable: true
                    },
                    showInLegend: true
                }
            },
            credits: {
                enabled: false
            },
            series: [{
                data: array
            }],
            colors: ['#d9534f', '#f0ad4e', '#5cb85c']
        });
	
	</script>

</body>
</html>



