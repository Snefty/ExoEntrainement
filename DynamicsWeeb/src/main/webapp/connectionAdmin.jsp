<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap.min.css">
<title>JakartaEE</title>
</head>
<body>
	
	<%
		
		String login = (String)session.getAttribute("login");
	
	%>
	
	<header style="text-align: center;">
		<h1>Bienvenue <%= login %></h1>
	</header>

	<div class="container">
		<div class="d-grid gap-3">
			<a href="inventaire.jsp" class="btn btn-info"> Verifier Articles
			</a> <a href="modifierCompte.jsp" class="btn btn-success"> Ajouter
				utilisateur (Admin) </a> <a href="inscription.jsp"
				class="btn btn-danger"> Supprimer un compte </a>
		</div>
	</div>


</body>
</html>