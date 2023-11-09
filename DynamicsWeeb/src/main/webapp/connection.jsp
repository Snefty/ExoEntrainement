<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="jwProject.Errors" import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="css/bootstrap.min.css">

<title>JakartaEE</title>

</head>
<body>

	<div class="container">
		<div class="card">
	
			<form action="MyServlet?flag=connect" method="POST" >
				<fieldset>
					<h4>Connection</h4>
				
					
					<div class="form-group">
						
						<label class="form-label mt-4">Login</label> 
						<input type="text" class="form-control" id="login" name="login" placeholder="Enter login">
						
					</div>

					<div class="form-group">
						<label class="form-label mt-4">Password</label> 
						<input type="password" class="form-control" id="mdp" name="mdp" placeholder="Enter password">
					</div>
					
					<br>
					<br> 
					<input type="submit" class="btn btn-primary" value="submit"> 
					<input type="reset" class="btn btn-primary" value="reset">
					
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