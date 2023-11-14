<%@page import="jwProject.CreerConnexion" import="java.util.*"
	import="jwProject.Article"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/fichierStyle1.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<title>Inventaire</title>
</head>
<body>

	<header style="display: flex;justify-content: space-between;">
		<div class="accueil" style="">
			<h1>
				<a href="connectionAdmin.jsp" style="text-decoration:none;"> Accueil </a>
			</h1>
		</div>

		<div>
			<form action="MyServlet?flag=recherche" method="POST">
				<input type="text" class="form-control" autofocus="autofocus"
					id="des" name="des" style="width: 400px" placeholder="chercher....">
			</form>
		</div>

	</header>

	<%
	CreerConnexion cc = (CreerConnexion)session.getAttribute("cc");
	
	%>

	<div style="height: 690px; overflow-y: scroll;">
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th scope="col">
						<h4>
							#
						</h4>
					</th>
					<th scope="col"><h4>designation</h4></th>
					<th scope="col"><h4>Prix unitaire</h4></th>
					<th scope="col"><h4>Quantité</h4></th>
					<th scope="col"><h4>Categorie</h4></th>
				</tr>
			</thead>

			<%
			
			List<Article> art = new ArrayList<Article>();
			if(cc.isVide()){
				cc.afficherArticle(0);	
				art = cc.getArticles();
			}else{
				art = cc.getArticles();
				System.out.println(" Ouaip ^^");
			}
			
			for (Article entry : art) {
			%>
			<tr>
				<th scope="row"><%=entry.getIdArticle()%></th>
				<td><%=entry.getDesignation()%></td>
				<td><%=entry.getPu()%> $</td>
				<td><%=entry.getQty()%></td>
				<td><%=entry.getNameCategorie()%></td>
			</tr>

			<%
			}
			System.out.println("\n");
			%>
		</table>
	</div>

	<footer
		style="position: fixed; bottom: 0; width: 100%; text-align: center;">
		<hr>
		<div class="container">

			<div class="d-grid gap-3">

				<button id="bouton1" class="btn btn-success">Ajouter un
					Article</button>

				<div id="creaMod" class="modal">
					<div class="modal-content">

						<form action="MyServlet?flag=creationArticle" method="POST">
							<fieldset>
								<h4>Creation d'un article</h4>

								<div class="form-group">
									<label class="form-label mt-4">Nom de l'article:</label> <input
										type="text" class="form-control" autofocus="autofocus"
										id="nom" name="nom" placeholder="Enter designation">
								</div>

								<div class="form-group">
									<label class="form-label mt-4">Prix unitaire :
										(&lsaquo; 40 000)</label> <input type="number" class="form-control"
										min=0 value=0 required="required" id="pU" name="pU"
										placeholder="Enter prix">
								</div>

								<div class="form-group">
									<label class="form-label mt-4">Quantité : (&lsaquo; 40
										000)</label> <input type="number" class="form-control" min=0 value=0
										required="required" id="qte" name="qte"
										placeholder="Enter quantité">
								</div>

								<div class="form-group">
									<label for="sexe" class="form-label mt-4">Catégories</label> <select
										class="form-select" id="cat" name="cat">

										<%
										Map<Integer, String> categories = cc.afficherCategorie();
										for (Map.Entry<Integer, String> entry : categories.entrySet()) {
										%>
										<option value="<%=entry.getKey()%>"><%=entry.getValue()%></option>
										<%
										}
										%>
									</select>
								</div>

								<br>

								<div class="modal-footer">
									<input type="submit" class="btn btn-primary" value="Accepter">
									<input type="reset" id="btn" class="btn btn-info"
										value="Retour">
								</div>
							</fieldset>
						</form>
					</div>
				</div>

				<button id="bouton2" class="btn btn-success">Ajouter une
					Categorie</button>

				<div id="creaMod2" class="modal">
					<div class="modal-content">

						<form action="MyServlet?flag=creationCategorie" method="POST">
							<fieldset>
								<h4>Creation d'une categorie</h4>

								<div class="form-group">
									<label class="form-label mt-4">Nom de la categorie :</label> <input
										type="text" class="form-control" autofocus="autofocus"
										id="nom2" name="nom2" placeholder="Enter designation">
								</div>

								<br> <br>

								<div class="modal-footer">
									<input type="submit" class="btn btn-primary" value="Accepter">
									<input type="reset" id="btn2" class="btn btn-info"
										value="Retour">
								</div>
							</fieldset>
						</form>
					</div>
				</div>

				<button id="bouton3" class="btn btn-danger">Supprimer
					un/des article.s</button>

				<div id="supprMod" class="modal">
					<div class="modal-content">

						<form action="MyServlet?flag=supprArticle" method="POST">
							<fieldset>
								<h4>Suppression un/des article.s</h4>

								<div class="form-group">
									<label class="form-label mt-4">ID de l'article</label> <input
										type="number" class="form-control" min=0 value="0"
										autofocus="autofocus" id="idArticle" name="idArticle"
										placeholder="Enter ID de l'article">
								</div>

								<div class="form-group">
									<label class="form-label mt-4">Designation</label> <input
										type="text" class="form-control" autofocus="autofocus"
										id="idArticle" name="suprDes"
										placeholder="Enter designation de l'article">
								</div>

								<div class="form-group">
									<label class="form-label mt-4">Categorie</label> <label
										class="form-label mt-4">Categorie</label> <select
										class="form-select" id="cat" name="cat">
										<option value=0>Choissisez une catégorie...</option>
										<%
										for (Map.Entry<Integer, String> entry : categories.entrySet()) {
										%>
										<option value="<%=entry.getKey()%>"><%=entry.getValue()%></option>
										<%
										}
										%>

									</select>

								</div>

								<br> <br>

								<div class="modal-footer">
									<input type="submit" class="btn btn-primary" value="Accepter">
									<input type="reset" id="btn3" class="btn btn-info" value="Retour">
								</div>
							</fieldset>
						</form>

					</div>
				</div>

				<button id="bouton4" class="btn btn-danger">Supprimer une
					categorie</button>

				<div id="supprMod2" class="modal">
					<div class="modal-content">

						<form action="MyServlet?flag=supprCat" method="POST">
							<fieldset>
								<h4>Suppression d'une categorie</h4>

								<div class="form-group">
									<label class="form-label mt-4">Categories :</label> <select
										class="form-select" id="cat" name="cat">
										<option value=0>Choissisez une catégorie...</option>
										<%
										for (Map.Entry<Integer, String> entry : categories.entrySet()) {
										%>
										<option value="<%=entry.getKey()%>"><%=entry.getValue()%></option>
										<%
										}
										%>

									</select>

								</div>

								<p class="text-warning">
									<strong>Warning !!! </strong> La suppresion d'une catégorie,
									supprime les articles avec lequels il est lier
								</p>

								<br> <br>

								<div class="modal-footer">
									<input type="submit" class="btn btn-primary" value="Accepter">
									<input type="reset" id="btn4" class="btn btn-info"
										value="Retour">
								</div>
							</fieldset>
						</form>

					</div>
				</div>

			</div>
			
			<!--  
			<div id="alerte" class="alert alert-dismissible alert-danger">
  			<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
  			<p class="${empty erreurs ? 'succes' : 'erreur'}">${erreurs}</p>
			</div>
			-->
			
		</div>
	</footer>

	<script>
		// Get the modal
		var modal = document.getElementById("creaMod");
		var modal2 = document.getElementById("creaMod2");
		var supprModal = document.getElementById("supprMod");
		var supprModal2 = document.getElementById("supprMod2");

		// Get the button that opens the modal
		var btn = document.getElementById("bouton1");
		var btn2 = document.getElementById("bouton2");
		var btn3 = document.getElementById("bouton3");
		var btn4 = document.getElementById("bouton4");

		// Get the <span> element that closes the modal
		var span = document.getElementById("btn");
		var span2 = document.getElementById("btn2");
		var span3 = document.getElementById("btn3");
		var span4 = document.getElementById("btn4");

		// When the user clicks the button, open the modal 
		btn.onclick = function() {
			modal.style.display = "block";
		}

		btn2.onclick = function() {
			modal2.style.display = "block";
		}

		btn3.onclick = function() {
			supprModal.style.display = "block";
		}

		btn4.onclick = function() {
			supprModal2.style.display = "block";
		}

		// When the user clicks on <span> (x), close the modal
		span.onclick = function() {
			modal.style.display = "none";
		}

		span2.onclick = function() {
			modal2.style.display = "none";
		}

		span3.onclick = function() {
			supprModal.style.display = "none";
		}

		span4.onclick = function() {
			supprModal2.style.display = "none";
		}

		// When the user clicks anywhere outside of the modal, close it
		/*window.onclick = function(event) {
			if (event.target == modal) {
				modal.style.display = "none";
				$('#creaMod').on('dispose')
			} else if (event.target == modal2) {
				modal2.style.display = "none";
			} else if (event.target == supprModal) {
				supprModal.style.display = "none";
			}else if(even.target == supprModal2){
				supprModal2.style.display = "none";
			}
		}*/
	</script>

</body>
</html>