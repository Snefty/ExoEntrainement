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
	
	public Map<Integer,Article> afficherArticle() throws SQLException{
		Map<Integer,Article> articles = new HashMap<Integer, Article>();
		etablirConnexion();
		
		sql = "Select * FROM article;";
		rs = st.executeQuery(sql);
		
		while(rs.next()) {
			articles.put(rs.getInt("idArticle"), new Article(rs.getString("designation"), 
					rs.getInt("pu"),
					rs.getInt("qty"),
					rs.getInt("idCategorie"),
					rs.getString("designation")));
		}
		
		cloturerConnexion();
		return articles;
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
}
