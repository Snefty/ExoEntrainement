<%@page import="jwProject.CreerConnexion" import="java.util.*"
	import="jwProject.Article"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/fichierStyle1.css">
<title>Inventaire</title>
</head>
<body>

	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th scope="col">#</th>
				<th scope="col">designation</th>
				<th scope="col">PrixUnitaire</th>
				<th scope="col">Quantité</th>
			</tr>
		</thead>
		<% 
		CreerConnexion cc = new CreerConnexion();
		Map<Integer,Article> m = cc.afficherArticle();
		
		for(Map.Entry<Integer,Article> entry : m.entrySet()){
	%>

		<tr>
			<th scope="row"><%= entry.getKey() %></th>
			<td><%= entry.getValue().getDesignation() %></td>
			<td><%= entry.getValue().getPu() %> $</td>
			<td><%= entry.getValue().getQty() %></td>
		</tr>


		<%
		}
	%>
	</table>
	<hr>
	<div class="container">
	
		<div class="d-grid gap-3">
		
			<button id="bouton1" class="btn btn-warning">Ajouter un Article</button>
			
			<div id="creaMod" class="modal">
				<div class="modal-content">
							
			<form action="MyServlet?flag=creation" method="POST">
				<fieldset>
				<h4>Creation d'un article</h4>

					<div class="form-group">
						<label class="form-label mt-4">Nom de l'article:</label> 
						<input type="text"
							class="form-control" autofocus="autofocus" id="nom" name="nom"
							placeholder="Enter designation">
					</div>

					<div class="form-group">
						<label class="form-label mt-4">Prix unitaire :</label> 
						<input type="number"
							class="form-control" min=0 value=0 required="required" id="pU" name="pU"
							placeholder="Enter prix">
					</div>

					<div class="form-group">
						<label class="form-label mt-4">Quantité :</label> <input
							type="number" class="form-control" min=0 value=0 required="required" id="qte" name="qte"
							placeholder="Enter quantité">
					</div>
					
					<div class="form-group">
						<label for="sexe" class="form-label mt-4">Catégories</label> 
						<select
							class="form-select" id="cat" name="cat">
							
						<%
							Map<Integer,String> categories = cc.afficherCategorie();
							for(Map.Entry<Integer,String> entry : categories.entrySet()){
						%>	
							<option value="<%= entry.getKey() %>"><%= entry.getValue() %></option>
							
						<%
							}
						%>
						</select>
					</div>

					<br> <br> 

					<div class="modal-footer">
						<input type="submit" class="btn btn-primary" value="Accepter"> 
						<input type="reset" class="btn btn-info" value="Retour">
					</div>
					</fieldset>
				</form>
				</div>
			</div>

			<script>
			// Get the modal
			var modal = document.getElementById("creaMod");

			// Get the button that opens the modal
			var btn = document.getElementById("bouton1");

			// Get the <span> element that closes the modal
			var span = document.getElementsByClassName("btn btn-info")[0];

			// When the user clicks the button, open the modal 
			btn.onclick = function() {
 			 modal.style.display = "block";
				}

			// When the user clicks on <span> (x), close the modal
			span.onclick = function() {
 				 modal.style.display = "none";
			}

				// When the user clicks anywhere outside of the modal, close it
			window.onclick = function(event) {
 			 if (event.target == modal) {
   				 modal.style.display = "none";
 			 }
				}
			</script>
			
			<a href="inscription.jsp" class="btn btn-danger"> Supprimer un
				article </a> <a href="connectionAdmin.jsp" class="btn btn-info">
				Revenir profil </a>
		</div>
		
		<div class="container aqua">
					 <!-- Structure if tertiaire -->
						<p class="${empty erreurs ? 'succes' : 'erreur'}">${resultat}</p>
		</div>
	</div>


</body>
</html>