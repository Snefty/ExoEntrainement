<%@ page import="jwProject.CreerConnexion" import="jwProject.Users" import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap.min.css">
<title>Modification</title>
</head>
<body>

	<%
		CreerConnexion cc = (CreerConnexion)session.getAttribute("cc");
		String login = (String)session.getAttribute("login");
		String mdp = (String)session.getAttribute("pwd");
		Users user = cc.retournerUsers(login, mdp);
	%>

	<header>
		<h1>Modification du compte</h1>
	</header>

	<form action="MyServlet?flag=recherche" method="POST">
		<label class="form-label mt-4">Nom :</label> <input
			type="text" class="form-control" autofocus="autofocus" id="des"
			name="des" style="width: 400px" placeholder="chercher....">
	</form>

	<form action="MyServlet?flag=recherche" method="POST">
		<label class="form-label mt-4">Prenom :</label> <input
			type="text" class="form-control" autofocus="autofocus" id="des"
			name="des" style="width: 400px" placeholder="chercher....">
	</form>

	<form action="MyServlet?flag=recherche" method="POST">
		<label class="form-label mt-4">Adresse :</label> <input
			type="text" class="form-control" autofocus="autofocus" id="des"
			name="des" style="width: 400px" placeholder="chercher....">
	</form>

	<form action="MyServlet?flag=recherche" method="POST">
		<label class="form-label mt-4">Telephone :</label> <input
			type="text" class="form-control" autofocus="autofocus" id="des"
			name="des" style="width: 400px" placeholder="chercher....">
	</form>
	
	<form action="MyServlet?flag=recherche" method="POST">
		<label class="form-label mt-4">Age :</label> <input
			type="text" class="form-control" autofocus="autofocus" id="des"
			name="des" style="width: 400px" placeholder="chercher....">
	</form>
	
	<form action="MyServlet?flag=recherche" method="POST">
		<label class="form-label mt-4">Prenom :</label> <input
			type="text" class="form-control" autofocus="autofocus" id="des"
			name="des" style="width: 400px" placeholder="chercher....">
	</form>
	
	<form action="MyServlet?flag=recherche" method="POST">
		<label class="form-label mt-4">Sexe :</label> <input
			type="text" class="form-control" autofocus="autofocus" id="des"
			name="des" style="width: 400px" placeholder="chercher....">
	</form>
	
	<form action="MyServlet?flag=recherche" method="POST">
		<label class="form-label mt-4">Login :</label> <input
			type="text" class="form-control" autofocus="autofocus" id="des"
			name="des" style="width: 400px" placeholder="chercher....">
	</form>
	
	<form action="MyServlet?flag=recherche" method="POST">
		<label class="form-label mt-4">Mot de passe :</label> <input
			type="text" class="form-control" autofocus="autofocus" id="des"
			name="des" style="width: 400px" placeholder="chercher....">
	</form>
</body>
</html>