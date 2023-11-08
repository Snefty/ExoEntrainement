package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import serverConnection.myConnection;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")

public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private myConnection mC = new myConnection();

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

	}

	/**
	 * @throws IOException, SQLException 
	 * @throws ServletException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		String flag = request.getParameter("flag");
		if(flag.equals("connect")) {
			this.doConnection(request,response);
		}else {
			if(flag.equals("inscri")) {
				doInscription(request, response);
			}else {
				doGet(request, response);
			}
		}
	}

	private void doInscription(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idCompte = -1;
		
		HttpSession session = request.getSession();
		String result;
		Map<String,String> erreurs = new HashMap<String, String>();
		
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String adresse = request.getParameter("adr");
		String tel = request.getParameter("tel");
		int age = Integer.parseInt(request.getParameter("age"));
		String sexe = request.getParameter("sexe");
		String password = request.getParameter("pwd");
		
		//VALIDATION
		try {
			this.validationAge(age);
		} catch (Exception e) {
			erreurs.put(age + "", e.getMessage());
		}
		try {
			this.validationNomination(nom);
		} catch (Exception e) {
			erreurs.put(nom, e.getMessage());
		}
		try {
			this.validationNomination(prenom);
		} catch (Exception e) {
			erreurs.put(prenom, e.getMessage());
		}
		try {
			this.validationMotDePasse(password);
		} catch (Exception e) {
			erreurs.put(password + "", e.getMessage());
		}
		
		if(erreurs.isEmpty()) {
			result = "Succès de l'inscription";
			if(mC.exist(nom, prenom) == false){
				idCompte = mC.creationCompte(prenom, password);
				mC.creationUser(nom, prenom, adresse, tel, age, sexe, idCompte);
				request.getRequestDispatcher("/connection.jsp").forward(request, response);
			}else {
				request.getRequestDispatcher("/inscription.jsp").forward(request, response);
			}
		}else {
			result = "Echec de l'inscription";
		}
			
		request.setAttribute("erreurs", erreurs);
		request.setAttribute("resultat", result);
		// Redirection
		request.getRequestDispatcher("/inscription.jsp").forward(request, response);
	}
	
	public void validationAge(int age) throws Exception {
		if(age < 13 && age > 99) {
			throw new Exception("L'age doit etre compris entre 13 et 99 ans");
		}
	}
	
	public void validationNomination(String ch) throws Exception {
		if(ch != null && ch.trim().length() < 3) {
			throw new Exception("Exception doit etre d'au moins 3 caractères");
		}
	}
	
	public void validationMotDePasse(String mdp) throws Exception {
		if(mdp == null ) {
			throw new Exception("Veuillez-entrez un mot de passe !");
		}else if(mdp.trim().length() < 6) {
			throw new Exception("Le mot de passe doit avoir plus de 6 caractéres");
		}
	}
	
	private void doConnection(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		String login = request.getParameter("login");
		String mdpBB = request.getParameter("mdp");

		List<String> coord = mC.verifierCoordonne(login);

		if(coord.isEmpty() || coord == null) {
			request.getRequestDispatcher("/connection.jsp").forward(request, response);
		}else {
			if(mdpBB.equals(coord.get(0))) {

				if(coord.get(1).charAt(0) == 'a') {
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
