package jwProject;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CreerConnexion cc = new CreerConnexion();   
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flag = request.getParameter("flag");
		if(flag.equalsIgnoreCase("connect")) {
			this.doConnexion(request,response);
		}else if(flag.equalsIgnoreCase("inscri")) {
			try {
				this.doInscription(request, response);
			} catch (ServletException | IOException | SQLException e) {
				e.printStackTrace();
			}
		}else if(flag.equalsIgnoreCase("creationArticle")) {
			try {
				this.doCreerArticle(request, response);
			} catch (ServletException | IOException | SQLException e) {
				e.printStackTrace();
			}
		}else if(flag.equalsIgnoreCase("creationCategorie")) {
			try {
				this.doCreerCategorie(request, response);
			} catch (ServletException | IOException | SQLException e) {
				e.printStackTrace();
			}
		}else if(flag.equalsIgnoreCase("supprArticle")) {
			try {
				this.doSupprimerArticle(request, response);
			} catch (ServletException | IOException | SQLException e) {
				e.printStackTrace();
			}
		}else if(flag.equals("supprCat")) {
			try {
				this.doSupprimerCategorie(request, response);
			} catch (ServletException | IOException | SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void doSupprimerCategorie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		@SuppressWarnings("unused")
		HttpSession session = request.getSession(true);
		
		int idCategorie = Integer.parseInt(request.getParameter("cat"));
		
		if(idCategorie != 0) {
			cc.supprimerCategorie(idCategorie);
		}

		request.getRequestDispatcher("/inventaire.jsp").forward(request, response);
	}

	private void doSupprimerArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		@SuppressWarnings("unused")
		HttpSession session = request.getSession(true);
		
		int idArticle = Integer.parseInt(request.getParameter("idArticle"));
		String designation = request.getParameter("suprDes");
		int categorie = Integer.parseInt(request.getParameter("cat"));
		
		String resultat;
		Map<String,String> erreurs = new HashMap<String,String>();
		
		try {
			validationIdArticleExist(idArticle);
		}catch(Exception e) {
			erreurs.put(idArticle + "", e.getMessage());
		}
		try {
			validationDesignationExist(designation);
		}catch(Exception e) {
			erreurs.put(idArticle + "", e.getMessage());
		}
		
		if(erreurs.isEmpty()) {
			resultat = "Succès de l'inscription";
			
			if(idArticle != 0) {
				cc.supprimerArticlesIdArticle(idArticle);
			}else if(designation == null){
				cc.supprimerArticlesDesignation(designation);
			}else {
				cc.supprimerArticleCategorie(categorie);
			}
			
			request.getRequestDispatcher("/inventaire.jsp").forward(request, response);
		}else {
			resultat = "Échec d'inscription";
		}
		
		request.setAttribute("erreurs", erreurs);
		request.setAttribute("resultat", resultat);
		// Redirection 
		request.getRequestDispatcher("/inventaire.jsp").forward(request, response);
	}
	
	private void validationDesignationExist(String designation) throws Exception {
		if(!cc.isExistDesignation(designation)) {
			throw new Exception("Ce nom de produit n'existe pas !");
		}
	}

	private void validationIdArticleExist(int idArticle) throws SQLException, Exception {
		if(!cc.isExistIdArticle(idArticle) && idArticle != 0) {
			throw new Exception("IdArticle n'existe pas dans la base de donnée !!");
		}
		
	}

	// LA PAGE RECREER DES ARTICLES EN BOUCLE FAIRE GAFFE; CAUSE MODALE
	private void doCreerCategorie(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		@SuppressWarnings("unused")
		HttpSession session = request.getSession(true);
		
		String designation = request.getParameter("nom2");
		
		String resultat;
		Map<String,String> erreurs = new HashMap<String,String>();
		
		try {
			validationNomination(designation);
		}catch(Exception e) {
			erreurs.put(designation, e.getMessage());
		}
		
		if(erreurs.isEmpty()) {
			resultat = "Succès de l'inscription";
			
			cc.ajouterCategorie(designation);
			
		}else {
			resultat = "Échec d'inscription";
		}
		
		request.setAttribute("erreurs", erreurs);
		request.setAttribute("resultat", resultat);
		// Redirection 
		request.getRequestDispatcher("/inventaire.jsp").forward(request, response);
	}

	private void doCreerArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		@SuppressWarnings("unused")
		HttpSession session = request.getSession(true);
		
		String designation = request.getParameter("nom");
		int prixUnitaire = Integer.parseInt(request.getParameter("pU"));
		int quantité = Integer.parseInt(request.getParameter("qte"));
		int catégorie = Integer.parseInt(request.getParameter("cat"));
		
		String resultat;
		Map<String,String> erreurs = new HashMap<String,String>();
		
		try {
			validationNomination(designation);
		}catch(Exception e) {
			erreurs.put(designation, e.getMessage());
		}
		try {
			validationNonInferieur(prixUnitaire);
		}catch(Exception e) {
			erreurs.put(prixUnitaire + "", e.getMessage());
		}
		try {
			validationNonSuperieur(prixUnitaire);
		}catch(Exception e) {
			erreurs.put(prixUnitaire + "", e.getMessage());
		}
		try {
			validationNonInferieur(quantité);
		}catch(Exception e) {
			erreurs.put(quantité + "", e.getMessage());
		}
		try {
			validationNonSuperieur(quantité);
		}catch(Exception e) {
			erreurs.put(quantité + "", e.getMessage());
		}
		
		if(erreurs.isEmpty()) {
			resultat = "Succès de l'inscription";
			
			cc.ajouterArticle(new Article(designation, prixUnitaire, quantité, catégorie));
			
		}else {
			resultat = "Échec d'inscription";
		}
		
		request.setAttribute("erreurs", erreurs);
		request.setAttribute("resultat", resultat);
		// Redirection 
		request.getRequestDispatcher("/inventaire.jsp").forward(request, response);
	}
	
	private void validationNonSuperieur(int prixUnitaire) throws Exception {
		if(prixUnitaire > 40000) {
			throw new Exception("Le nombre doit etre inférieur à 40 000 !!!");
		}
	}

	public void validationNonInferieur(int entier) throws Exception {
		if(entier <= 0) throw new Exception();
	}

	private void doInscription(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		@SuppressWarnings("unused")
		HttpSession session = request.getSession(true);

		// sauvegarde du résultat de validation
		String resultat;
		Map<String,String> erreurs = new HashMap<String,String>();
		// Récupérer les valeurs des champs à partir du JSP
		String fname = request.getParameter("firstName");
		String lname = request.getParameter("lastName");
		String adresse = request.getParameter("adresse");
		String tel = request.getParameter("tel");
		int age = Integer.parseInt(request.getParameter("age"));
		String[] tabRadio = request.getParameterValues("sexe");
		String login = request.getParameter("login");
		String pwd = request.getParameter("pwd");
		String pwd2 = request.getParameter("pwd2");
		// récupérer la valeur du bouton radio
		String sexe = "";
		for(int i = 0; i < tabRadio.length; i++) {
			if(tabRadio[i] != null) {
				sexe = tabRadio[i];
			}
		}

		// Contrôle des champs
		try {
			this.validationAge(age);
		} catch (Exception e) {
			erreurs.put(""+age, e.getMessage());
		}
		try {
			this.validationNomination(fname);
		} catch (Exception e) {
			erreurs.put(fname, e.getMessage());
		}
		try {
			this.validationNomination(lname);
		} catch (Exception e) {
			erreurs.put(lname, e.getMessage());
		}
		try {
			this.validationMotsDePasse(pwd, pwd2);
		} catch (Exception e) {
			erreurs.put(pwd, e.getMessage());
		}
		// Résultat global des validations
		if(erreurs.isEmpty()) {
			resultat = "Succès de l'inscription";
			// INSERT INTO
			Users u = new Users(fname,lname,adresse,tel,age,sexe);
			cc.ajouterUsers(u);
			int idUsers = cc.idLastUsers();
			Compte c = new Compte(login, pwd, idUsers);
			cc.ajouterCompte(c);
			request.getRequestDispatcher("/connection.jsp").forward(request, response);
		}else {
			resultat = "Échec d'inscription";
		}
		
		request.setAttribute("erreurs", erreurs);
		request.setAttribute("resultat", resultat);
		// Redirection 
		request.getRequestDispatcher("/inscription.jsp").forward(request, response);
		//response.sendRedirect("/inscrit.jsp");
	}

	// Vérification de l'âge
	public void validationAge(int age) throws Exception {
		if(age < 15 || age > 120) {
			throw new Exception("L'age doit etre compris entre 15 et 120");
		}
	}
	// Vérification du nom/prénom
	public void validationNomination(String ch) throws Exception {
		if(ch == "") throw new Exception("Veulliez-remplir le champ !");
		if(ch != null && ch.trim().length() < 3) {
			throw new Exception("Le champ doit être d'au moins 3 caractères");
		}
	}
	// Vérification du mot de passe et de sa confirmation
	public void validationMotsDePasse(String motDePasse, String confirmation) throws Exception {
		if(motDePasse != null && motDePasse.trim().length() != 0 &&
				confirmation != null && confirmation.trim().length() != 0) {
			if(!motDePasse.equals(confirmation)) {
				throw new Exception("les mots des passe ne sont pas les mêmes");
			}else {
				if(motDePasse.trim().length() < 3) {
					throw new Exception("Longueur insuffisant pour mots des passe");
				}
			}
		}else {
			throw new Exception("SVP saisir un mot des passe");
		}
	}
	// Vérification du pseudo
	public void validationPseudo(String pseudo) throws Exception {
		if(pseudo != null && pseudo.trim().length() != 0) {
			if(!pseudo.matches(
					"([^.@])(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				throw new Exception("Pseudo non valide");	
			}
		}else {
			throw new Exception("SVP saisir un pseudo");
		}
	}
	private void doConnexion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String pwd = request.getParameter("mdp");

		List<String> pwdBDD = cc.verifierCoordonnees(login);

		if(pwdBDD == null) {
			request.getRequestDispatcher("/connection.jsp").forward(request, response);
		}else {
			if(pwd.equals(pwdBDD.get(0))) {

				if(pwdBDD.get(1).equals("a")) {
					request.getRequestDispatcher("/connectionAdmin.jsp").forward(request, response);
				}else {
					request.getRequestDispatcher("/connectionSimple.jsp").forward(request, response);
				}

			}else {
				request.getRequestDispatcher("/connection.jsp").forward(request, response);
			}
		}
	}

}
