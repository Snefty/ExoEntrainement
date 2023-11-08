package serverConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class myConnection {

	private final String URL = "jdbc:mysql://localhost:3306/prjcommerce";
	private final String LOGIN = "root";
	private final String MDP = "";

	private Connection cn = null;
	private Statement st;
	private PreparedStatement pS = null;
	private ResultSet rs = null;

	private String sql = "";

	public Connection myCnx() {
		// Création de la chaîne de connexion
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection(URL, LOGIN, MDP);
			st = cn.createStatement();
			System.out.println("Connexion réussie");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cn;
	}

	public void cloturerConnexion() {
		try {
			cn.close();
			st.close();
			System.out.println("Cloture connexion reussi");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public List<String> verifierCoordonne(String login) {
		List<String> coord = new ArrayList<String>();

		myCnx();
		sql = "SELECT pwd, type FROM compte WHERE login LIKE '" + login + "'"; 

		try {
			rs = st.executeQuery(sql);
			if(rs.next()) {
				coord.add(rs.getString(1));
				coord.add(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cloturerConnexion();
		return coord;
	}

	public int creationCompte(String login, String pwd) {
		myCnx();
		sql = "INSERT INTO compte(login, pwd, type) VALUES ('" + login + "','" + pwd + "','s');";
		try {
			pS = cn.prepareStatement(sql);
			pS.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}

		int idCompte = -1;

		sql = "SELECT idCompte FROM compte WHERE login LIKE '" + login +"';";
		try {
			rs = st.executeQuery(sql);
			if(rs.next()) {
				idCompte = Integer.parseInt(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		cloturerConnexion();
		return idCompte;
	}

	public void creationUser(String nom, String prenom, String adresse, String tel, int age, String sexe, int idCompte) {
		myCnx();

		sql = "INSERT INTO users(fname, Lname, adresse, tel, age, sexe, idCompte) VALUES('"
				+ nom + "','" + prenom + "','" + adresse + "','" + tel + "'," + age + ",'" +  sexe + "'," + idCompte + ");";
		try {
			pS = cn.prepareStatement(sql);
			pS.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}

		sql = "INSERT INTO utilisateur(nom, prenom, sexe, type) VALUES ('" + nom + "','" + prenom + "','" + sexe + "','s')";
		try {
			pS = cn.prepareStatement(sql);
			pS.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}

		cloturerConnexion();
	}

	public boolean exist(String nom, String prenom) {
		int idUsers = -1;
		myCnx();

		sql = "SELECT numero FROM utilisateur WHERE nom LIKE '" + nom +"' AND prenom LIKE '" + prenom +"';";
		try {
			rs = st.executeQuery(sql);
			if(rs.next()) {
				idUsers = Integer.parseInt(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		cloturerConnexion();
		if(idUsers == -1) return false;
		else return true;
	}
}

