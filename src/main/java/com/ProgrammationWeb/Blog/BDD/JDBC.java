package com.ProgrammationWeb.Blog.BDD;

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
			}
			System.out.println("nombre total in bdd " + cmp);
			if(cmp == 0)
			{
				retour = "Mot de passe incorrecte";
			}
			else
			{
				retour = pseudo + ", vous êtes maintenant connecté !";
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
		String retour = "KO";
		Statement st;
		String sqlInsert = "DELETE FROM users WHERE pseudo=?";
		return true;
	}
}
