<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container-fluid">
		<a class="navbar-brand" href="#">Sistema de Agenda de Tarefas</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item">
					<a class="nav-link active" aria-current="page" href="/projetoSpringMVC01/home">Página inicial</a>
				</li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-bs-toggle="dropdown" aria-expanded="false">
						Gerenciar Minhas Tarefas </a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
						<li><a class="dropdown-item"
							href="/projetoSpringMVC01/cadastro-tarefa">Cadastrar Tarefas</a></li>
						<li><a class="dropdown-item"
							href="/projetoSpringMVC01/consulta-tarefa">Consultar Tarefas</a></li>
						<li><hr class="dropdown-divider"></li>
						<li><a class="dropdown-item"
							href="/projetoSpringMVC01/relatorio-tarefa">Relatório de
								Tarefas</a></li>
					</ul>
				</li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-bs-toggle="dropdown" aria-expanded="false">
						Gerenciar Minha Conta </a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
						<li><a class="dropdown-item"
							href="/projetoSpringMVC01/userdata">Dados da Conta</a></li>
						<li><a class="dropdown-item"
							href="/projetoSpringMVC01/alterar-senha">Alterar Senha de acesso</a></li>						
					</ul>
				</li>
			</ul>
			
			<form class="d-flex">
			
				<div class="text-white mt-3">
					${usuario_autenticado.nome}
					&nbsp;&nbsp; 
					<a href="/projetoSpringMVC01/logout" class="btn btn-outline-danger btn-sm"
					   onclick="return confirm('Deseja realmente sair do sistema?');">
						Sair do Sistema
					</a>
				</div>
			
			</form>
			
		</div>
	</div>
</nav>