package jwProject;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreerConnexion {
	Connection cn = null;
	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String sql = "";

	private final String URL = "jdbc:mysql://localhost:3306/prjCommerce"; 
	private final String LOGIN = "root"; 
	private final String MDP = ""; 

	List<Article> articles = new ArrayList<>();

	public Connection etablirConnexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection(
					URL,LOGIN,MDP);
			//System.out.println("Bravo!!! connexion réussie");
			st = cn.createStatement();
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return cn;
	}
	public void cloturerConnexion() {
		try {
			cn.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	public List<String> verifierCoordonnees(String login) {
		List<String> lt = new ArrayList<>();
		etablirConnexion();
		sql = "SELECT pwd, type FROM compte WHERE login LIKE '" + 
				login + "'";
		try {
			rs = st.executeQuery(sql);
			if(rs.next()) {
				lt.add(rs.getString(1));
				lt.add(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lt;
	}
	public void ajouterCompte(Compte cp) throws SQLException {
		etablirConnexion();
		/*
		 * sql = "SELECT * FROM compte WHERE login LIKE '" + cp.getLogin() + "'"; rs =
		 * st.executeQuery(sql); if(!rs.next()) {
		 */	
		sql = "INSERT INTO compte(login,pwd,type,idUsers) VALUES('"
				+ cp.getLogin() + "','" + cp.getPwd() + "','" 
				+ cp.getType() + "'," + cp.getIdUsers() + ");";
		ps = cn.prepareStatement(sql);
		ps.execute();
		cloturerConnexion();
		//}
	}
	public void ajouterUsers(Users u) throws SQLException {
		etablirConnexion();
		sql = "INSERT INTO users(fname,lname,adresse,tel,age,sexe) "
				+ "VALUES('" + u.getfName() + "','" + u.getlName() +
				"','" + u.getAdresse() + "','" + u.getTel() + "'," +
				u.getAge() + ",'" + u.getSexe() + "')";
		ps = cn.prepareStatement(sql);
		ps.execute();
		cloturerConnexion();
	}

	public void ajouterArticle(Article e) throws SQLException{
		etablirConnexion();
		sql = "INSERT INTO article(designation, pu, qty, idCategorie) "
				+ "VALUES('" + e.getDesignation() + "',"
				+ "'" + e.getPu() + "',"
				+ "'" + e.getQty() + "',"
				+ "'" + e.getIdCategorie() + "'"
				+ ");";
		ps = cn.prepareStatement(sql);
		ps.execute();
		cloturerConnexion();
	}

	public void ajouterCategorie(String designation) throws SQLException {
		etablirConnexion();

		String other = Character.toUpperCase(designation.charAt(0)) + designation.substring(1);

		sql = "INSERT INTO categorie(designation) "
				+ "VALUES('" + other + "');";
		ps = cn.prepareStatement(sql);
		ps.execute();
		cloturerConnexion();
	}

	public void supprimerArticlesIdArticle(int idArticle) throws SQLException {
		etablirConnexion();

		sql = "DELETE FROM article"
				+ " WHERE idArticle = '"+ idArticle +"';";

		ps = cn.prepareStatement(sql);
		ps.execute();
		cloturerConnexion();
	}

	public void supprimerArticlesDesignation(String designation) throws SQLException {
		etablirConnexion();

		sql = "DELETE FROM article"
				+ " WHERE designation LIKE '"+ designation +"';";

		ps = cn.prepareStatement(sql);
		ps.execute();
		cloturerConnexion();
	}

	public void supprimerArticleCategorie(int categorie) throws SQLException {
		etablirConnexion();

		sql = "DELETE FROM article"
				+ " WHERE idCategorie = "+ categorie +";";

		ps = cn.prepareStatement(sql);
		ps.execute();
		cloturerConnexion();
	}

	public void supprimerCategorie(int idCat) throws SQLException {
		supprimerArticleCategorie(idCat);

		etablirConnexion();

		sql = "DELETE FROM categorie WHERE idCategorie = "+ idCat +";";

		ps = cn.prepareStatement(sql);
		ps.execute();
		cloturerConnexion();
	}

	public int idLastUsers() throws SQLException {
		etablirConnexion();
		int id = 0;
		sql = "SELECT MAX(idUsers) FROM users;";
		rs = st.executeQuery(sql);
		if(rs.next()) {
			id = rs.getInt(1);
		}
		cloturerConnexion();
		return id;
	}

	public void afficherArticle(int choix) throws SQLException{
		this.articles.clear();
		etablirConnexion();

		if(choix == 0){
			sql = "SELECT a.*, c.designation AS cDes "
					+ "FROM article AS a JOIN categorie AS c "
					+ "ON a.idCategorie = c.idCategorie "
					+ "ORDER BY a.idArticle ASC;";
			rs = st.executeQuery(sql);

		}else if(choix == 1){
			sql = "SELECT a.*, c.designation AS cDes "
					+ "FROM article a JOIN categorie c "
					+ "ON a.idCategorie = c.idCategorie"
					+ "ORDER BY a.designation ASC;";
			rs = st.executeQuery(sql);

		}

		while(rs.next()) {
			this.articles.add(new Article(rs.getInt("idArticle"), rs.getString("designation"), rs.getInt("pu"),
					rs.getInt("qty"), rs.getInt("idCategorie") , rs.getString("cDes")));
		}

		cloturerConnexion();
	}

	public void afficherArticleDesignation(String des) throws SQLException {
		this.articles.clear();
		etablirConnexion();
		sql = "SELECT a.*, c.designation AS cDes "
				+ "FROM article AS a JOIN categorie AS c "
				+ "ON a.idCategorie = c.idCategorie "
				+ "WHERE a.designation LIKE '%" + des + "' "
				+ "ORDER BY a.idArticle ASC;";
		rs = st.executeQuery(sql);

		while(rs.next()) {
			this.articles.add(new Article(rs.getInt("idArticle"), rs.getString("designation"), rs.getInt("pu"),
					rs.getInt("qty"), rs.getInt("idCategorie") , rs.getString("cDes")));
		}
		
		cloturerConnexion();
	}

	public Map<Integer,String> afficherCategorie() throws SQLException{
		Map<Integer,String> cat = new HashMap<Integer, String>();
		etablirConnexion();

		sql = "Select * FROM categorie;";
		rs = st.executeQuery(sql);

		while(rs.next()) {
			cat.put(rs.getInt("idCategorie"), rs.getString("designation"));
		}

		cloturerConnexion();
		return cat;
	}

	public Users retournerUsers(String login, String mdp) throws SQLException {
		etablirConnexion();
		Users user = null;
		sql = "Select * FROM users;";
		rs = st.executeQuery(sql);
		
		while(rs.next()) {
			user = new Users(rs.getString("fname"), rs.getString("lname"), rs.getString("adresse"),
					rs.getString("tel"), rs.getInt("age"), rs.getString("sexe"));
		}

		cloturerConnexion();
		return user;
	}
	
	public boolean isExistIdArticle(int idArticle) throws SQLException {
		etablirConnexion();

		sql = "Select idArticle FROM article;";
		rs = st.executeQuery(sql);

		while(rs.next()) {
			if(rs.getInt("idArticle") == idArticle) {
				return true;
			}
		}

		cloturerConnexion();
		return false;
	}

	public boolean isExistDesignation(String des) throws SQLException {
		etablirConnexion();

		sql = "Select designation FROM article;";
		rs = st.executeQuery(sql);

		while(rs.next()) {
			if(rs.getString("designation").equals(des)) {
				return true;
			}
		}

		cloturerConnexion();
		return false;
	}
	public List<Article> getArticles() {
		return articles;
	}

	public boolean isVide() {
		return this.articles.isEmpty();
	}

}
