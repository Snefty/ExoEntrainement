<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JakartaEE</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>

	<div class="container">
		<div class="card">

			<form action="MyServlet?flag=inscri" method="POST">
				<fieldset>
					<h4>Connection</h4>

					<div class="form-group">
						<label class="form-label mt-4">Nom :</label> <input type="text"
							class="form-control" autofocus="autofocus" id="nom" name="nom"
							placeholder="Enter nom">
					</div>

					<div class="form-group">
						<label class="form-label mt-4">Prenom :</label> <input type="text"
							class="form-control" id="prenom" name="prenom"
							placeholder="Enter prenom">
					</div>

					<div class="form-group">
						<label class="form-label mt-4">Adresse :</label> <input
							type="email" class="form-control" id="adr" name="adr"
							placeholder="Enter adresse">
					</div>

					<div class="form-group">
						<label class="form-label mt-4">Telephone :</label> <input
							type="text" class="form-control" id="tel" name="tel"
							placeholder="Enter telephone">
					</div>

					<div class="form-group">
						<label class="form-label mt-4">Age :</label> <input type="number"
							class="form-control" id="age" name="age" placeholder="Enter age">
					</div>

					<div class="form-group">
						<label for="sexe" class="form-label mt-4">Sexe select</label> <select
							class="form-select" id="sexe" name="sexe">
							<option value="h">h</option>
							<option value="f">f</option>
						</select>
					</div>

					<div class="form-group">
						<label class="form-label mt-4">Mot de passe :</label> <input
							type="text" class="form-control" id="pwd" name="pwd"
							placeholder="Enter password">
					</div>

					<br> <br> <input type="submit" class="btn btn-primary"
						value="submit"> <input type="reset"
						class="btn btn-primary" value="reset">

					 <div class="container aqua">
					 <!-- Structure if tertiaire -->
						<p class="${empty erreurs ? 'succes' : 'erreur'}">${resultat}</p>
					</div>
				</fieldset>
			</form>

		</div>
	</div>

</body>
</html>