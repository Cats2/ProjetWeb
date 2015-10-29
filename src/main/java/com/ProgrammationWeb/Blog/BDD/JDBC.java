package com.ProgrammationWeb.Blog.BDD;

import java.net.URI;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ProgrammationWeb.Blog.RestAPI.Article;
import com.ProgrammationWeb.Blog.RestAPI.Users;


public class JDBC {

	static Connection connection = null;
	
	/*public static void main(String[] argv) {

		 
	      try {
	         Class.forName("org.postgresql.Driver");
	         connection = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/ProjetWeb",
	            "postgres", "admin");
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	      System.out.println("Opened database successfully");
	      List<Article> articles = getArticle();
	      for (Article a : articles)
	      {
	    	  System.out.println("retour: " + a.getTitre());
	      }
	}*/
	
	public static void Connection()
	{
		 try {
			//[database type]://[username]:[password]@[host]/[database name]
			 	//URI dbUri = new URI("postgresql://jenddphahayfkn:C_CgDO1gbkva3D9_v0DZ-Cgvpq@ec2-54-217-208-102.eu-west-1.compute.amazonaws.com:5432/d3kl00l3f54v18");
				//String username = dbUri.getUserInfo().split(":")[0];
				//String password = dbUri.getUserInfo().split(":")[1];
				//String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
				connection= DriverManager.getConnection("jdbc:postgresql://postgresql.alwaysdata.com:5432/cats_blog", "cats","ironman");
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
		 System.out.println("Opened database successfully");
	}
	
	public static List<Article> getArticle()
	{
		
		List<Article> articles = new ArrayList<Article>();
		System.out.println("Get Article");
		Statement st;
		try {
			st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM article ORDER BY titre");
			while (rs.next())
			{
				Article a = new Article();
			   System.out.println("Column 1 returned ");
			   a.setId(rs.getLong(1));
			   a.setTitre(rs.getString(2));
			   a.setContenu(rs.getString(3));
			   a.setDateCrea(rs.getDate(5));
			   a.setImage(rs.getString(6));
			   a.setCateg(rs.getString(7));
			   a.setAvis(rs.getInt(8));
			   System.out.println("article add " + a.getId() + " " + a.getTitre());
			   articles.add(a);
			} rs.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return articles;
	}
	
	public static List<Article> getMyArticle(String pseudo)
	{
		
		List<Article> articles = new ArrayList<Article>();
		System.out.println("Get Article");
		Statement st;
		try {
			st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM article a, users u where a.id_users = u.id_users AND pseudo=\'"+pseudo+"\' ORDER BY titre");
			while (rs.next())
			{
				Article a = new Article();
			   System.out.println("Column 1 returned ");
			   a.setId(rs.getLong(1));
			   a.setTitre(rs.getString(2));
			   a.setContenu(rs.getString(3));
			   a.setDateCrea(rs.getDate(5));
			   a.setImage(rs.getString(6));
			   a.setCateg(rs.getString(7));
			   a.setAvis(rs.getInt(8));
			   System.out.println("article add " + a.getId() + " " + a.getTitre());
			   articles.add(a);
			} rs.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return articles;
	}
	
	public static List<Article> getSearchArticle(String titre)
	{
		
		List<Article> articles = new ArrayList<Article>();
		System.out.println("Get Search Article");
		Statement st;
		String titremin = titre.toLowerCase();
		try {
			st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM article where lower(titre) LIKE \'%"+ titremin +"%\' ORDER BY titre");
			while (rs.next())
			{
				Article a = new Article();
			   a.setId(rs.getLong(1));
			   a.setTitre(rs.getString(2));
			   a.setContenu(rs.getString(3));
			   a.setDateCrea(rs.getDate(5));
			   a.setImage(rs.getString(6));
			   a.setCateg(rs.getString(7));
			   a.setAvis(rs.getInt(8));
			   System.out.println("article search " + a.getId() + " " + a.getTitre());
			   articles.add(a);
			} rs.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return articles;
	}
	
	public static Boolean addArticle(String titre, String contenu)
	{
		System.out.println("Ajout Article");
		String sql = "INSERT INTO article values ( ?, ?, ?, ?, ?)";
		Boolean retour;
		Statement st;
		long cmp =0;
		java.util.Date utilDate = new java.util.Date();
	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		try {
			st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM article");
			while (rs.next())
			{
				cmp = rs.getLong(1);
			}
			st = connection.createStatement();
			PreparedStatement prep = connection.prepareStatement(sql);
			System.out.println("identifiant " + cmp);
			System.out.println("identifiant " + sqlDate);
			cmp++;
			prep.setLong(1,cmp);
			prep.setString(2, titre);
			prep.setString(3, contenu);
			prep.setLong(4, 1);
			prep.setDate(5, sqlDate);
			prep.execute();
			st.close();
			prep.close();
			retour = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			retour = false;
		}
		return retour;
	}
	
	public static String addUser(String pseudo, String mdp)
	{
		System.out.println("Ajout Utilisateur");
		String retour = "KO";
		Statement st;
		String sqlInsert = "INSERT INTO users values (?, ?, ?, ?, ?)";
		java.util.Date utilDate = new java.util.Date();
	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		int cmp =0;
		try {
			st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT count(*) AS total FROM users WHERE pseudo ='" + pseudo+ "'");
			while (rs.next())
			{
				cmp = rs.getInt("total");
			}
			System.out.println("compteur " + cmp);
			st = connection.createStatement();
			System.out.println("identifiant " + cmp);
			if(cmp > 0)
			{
				retour = "Ce pseudo est déjà utilisé";
				return retour;
			}
			ResultSet rs2 = st.executeQuery("SELECT count(*) AS total FROM users");
			while (rs2.next())
			{
				cmp = rs2.getInt("total");
			}
			System.out.println("nombre total in bdd " + cmp);
			cmp++;
			PreparedStatement prep = connection.prepareStatement(sqlInsert);
			prep.setLong(1, cmp);
			prep.setString(2,  pseudo);
			prep.setString(3,  mdp);
			prep.setString(4,  "user");
			prep.setDate(5, sqlDate);
			prep.execute();
			prep.close();
			st.close();
			retour= "OK";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}
	
	public static String Connexion(String pseudo, String mdp)
	{
		System.out.println("Connexion Utilisateur");
		String retour = "KO";
		Statement st;
		int cmp =0;
		String role="";
		try {
			st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT count(*) AS total FROM users WHERE pseudo ='" + pseudo+ "'");
			while (rs.next())
			{
				cmp = rs.getInt("total");
			}
			System.out.println("compteur " + cmp);
			st = connection.createStatement();
			System.out.println("identifiant " + cmp);
			if(cmp == 0)
			{
				retour = "Ce pseudo n'existe pas";
				return retour;
			}
			ResultSet rs2 = st.executeQuery("SELECT count(*) AS total FROM users  WHERE pseudo ='" + pseudo + "' and motdepasse ='" + mdp +"'");
			while (rs2.next())
			{
				cmp = rs2.getInt("total");
				//role = rs2.getString(1);
			}
			System.out.println("nombre total in bdd " + cmp);
			if(cmp == 0)
			{
				retour = "Mot de passe incorrecte";
			}
			else
			{
				retour = pseudo + ", vous êtes maintenant connecté en tant que !";
			}
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}
	
	public static Users getUser(String pseudo)
	{
		System.out.println("Info Utilisateur");
		Statement st;
		String sqlSelectInfoUs = "SELECT pseudo, motdepasse, datecrea from users where pseudo=?";
		String sqlSelectNbArt = "SELECT count(*) as nbart from users u, article a where u.id_users = a.id_users and pseudo=?";
		Users us = new Users();
		try {
			st = connection.createStatement();
			PreparedStatement prep = connection.prepareStatement(sqlSelectInfoUs);
			prep.setString(1, pseudo);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				us.setPseudo( rs.getString("pseudo"));
				us.setMdp(rs.getString("motdepasse"));
				us.setDate(rs.getDate("datecrea"));
			}
			prep = connection.prepareStatement(sqlSelectNbArt);
			prep.setString(1, pseudo);
			rs = prep.executeQuery();
			while (rs.next()) {
				us.setNbArt( rs.getInt("nbart"));
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return us;
	}
	
	public static Boolean deleteUser(String pseudo)
	{
		System.out.println("Delete Utilisateur");
		Boolean retour = false;
		String sqlInsert = "DELETE FROM users WHERE pseudo=?";
		PreparedStatement prep;
		try {
			prep = connection.prepareStatement(sqlInsert);
			prep.setString(1, pseudo);
			prep.execute();
			prep.close();
			retour = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retour;
	}
	
	public static Boolean updatePseudo(String old_pseudo, String new_pseudo)
	{
		System.out.println("Update Pseudo");
		Boolean retour = false;
		String sqlInsert = "UPDATE users set pseudo=? WHERE pseudo=?";
		PreparedStatement prep;
		try {
			prep = connection.prepareStatement(sqlInsert);
			prep.setString(1, new_pseudo);
			prep.setString(2, old_pseudo);
			prep.execute();
			prep.close();
			retour = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retour;
	}
	
	public static Boolean updateMdp(String pseudo, String new_mdp)
	{
		System.out.println("Update Mdp");
		Boolean retour = false;
		String sqlInsert = "UPDATE users set motdepasse=? where pseudo=?";
		PreparedStatement prep;
		try {
			prep = connection.prepareStatement(sqlInsert);
			prep.setString(1, new_mdp);
			prep.setString(2, pseudo);
			prep.execute();
			prep.close();
			retour = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retour;
	}
	
	public static void addAvis(String titre, String pseudo)
	{
		System.out.println("Ajout Avis");
		Boolean retour = false;
		String sqlSelectArt = "Select id_art from article where titre=?";
		int id_art=0;
		int id_us=0;
		String sqlSelectUser = "Select id_users from users where pseudo=?";
		String sqlInsert = "INSERT INTO users_avis VALUES (?,?)";
		PreparedStatement prep;
		try {
			prep = connection.prepareStatement(sqlSelectArt);
			prep.setString(1, titre);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				id_art = rs.getInt(1);
			}
			prep = connection.prepareStatement(sqlSelectUser);
			prep.setString(1, pseudo);
			rs = prep.executeQuery();
			while (rs.next()) {
				id_us = rs.getInt(1);
			}
			prep = connection.prepareStatement(sqlInsert);
			prep.setInt(1, id_us);
			prep.setInt(2, id_art);
			prep.execute();
			prep.close();
			retour = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void majAvis(String titre)
	{
		System.out.println("Maj Avis");
		Boolean retour = false;
		String sqlSelectArt = "Select id_art, avis from article where titre=?";
		int id_art=0;
		int avis = 0;
		String sqlInsert = "UPDATE article set avis=? where id_art=?";
		PreparedStatement prep;
		try {
			prep = connection.prepareStatement(sqlSelectArt);
			prep.setString(1, titre);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				id_art = rs.getInt(1);
				avis = rs.getInt(2);
			}
			avis++;
			prep = connection.prepareStatement(sqlInsert);
			prep.setInt(1, avis);
			prep.setInt(2, id_art);
			prep.execute();
			prep.close();
			retour = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Boolean Avis(String titre, String pseudo)
	{
		System.out.println("Avis");
		Statement st;
		String sqlSelect = "SELECT Count(*) as total from users u, users_avis ua, article a where u.id_users=ua.id_users and a.id_art=ua.id_article and pseudo=? and titre=?";
		int count = 0;
		try {
			st = connection.createStatement();
			PreparedStatement prep = connection.prepareStatement(sqlSelect);
			prep.setString(1, pseudo);
			prep.setString(2, titre);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			rs.close();
			prep.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (count == 0)
			return false;
		else
			return true;
	}
	
	public static List<Article> getTop5Art()
	{
		List<Article> articles = new ArrayList<Article>();
		System.out.println("Top5 Article");
		Statement st;
		String sqlSelect = "SELECT titre, avis from article order by avis desc";
		String titre;
		int avis = 0;
		int cmp =0;
		try {
			st = connection.createStatement();
			PreparedStatement prep = connection.prepareStatement(sqlSelect);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				if(cmp >= 5) break;
				Article a = new Article();
				a.setTitre(rs.getString(1));
				a.setAvis(rs.getInt(2));
				articles.add(a);
				cmp++;
			}
			rs.close();
			prep.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return articles;
	}
	
	public static List<Article> getLast5Art()
	{
		List<Article> articles = new ArrayList<Article>();
		System.out.println("Last5 Article");
		Statement st;
		String sqlSelect = "SELECT titre, categ from article order by id_art desc";
		String titre;
		String categ;
		int cmp =0;
		try {
			st = connection.createStatement();
			PreparedStatement prep = connection.prepareStatement(sqlSelect);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				if(cmp >= 5) break;
				Article a = new Article();
				a.setTitre(rs.getString(1));
				a.setCateg(rs.getString(2));
				articles.add(a);
				cmp++;
			}
			rs.close();
			prep.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return articles;
	}
	
	public static List<Users> getTop5Us()
	{
		List<Users> users = new ArrayList<Users>();
		System.out.println("Top5 Users");
		Statement st;
		String sqlSelect = "SELECT pseudo from article order by avis";
		String titre;
		int avis = 0;
		int cmp =0;
		try {
			st = connection.createStatement();
			PreparedStatement prep = connection.prepareStatement(sqlSelect);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				if(cmp >= 5) break;
				Article a = new Article();
				a.setTitre(rs.getString(1));
				a.setAvis(rs.getInt(2));
				cmp++;
			}
			rs.close();
			prep.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
}
