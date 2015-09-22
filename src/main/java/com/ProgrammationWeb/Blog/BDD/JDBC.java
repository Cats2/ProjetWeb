package com.ProgrammationWeb.Blog.BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ProgrammationWeb.Blog.RestAPI.Article;


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
			ResultSet rs = st.executeQuery("SELECT * FROM article");
			while (rs.next())
			{
				Article a = new Article();
			   System.out.println("Column 1 returned ");
			   a.setId(rs.getLong(1));
			   a.setTitre(rs.getString(2));
			   a.setContenu(rs.getString(3));
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
}
