package com.ProgrammationWeb.Blog.BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ProgrammationWeb.Blog.RestAPI.Article;


public class JDBC {

	static Connection connection = null;
	
	public static void main(String[] argv) {

		 
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
	      getArticle();
	}
	
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
	
	public static Article getArticle()
	{
		Article a = new Article();
		System.out.println("Get Article");
		Statement st;
		try {
			st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM article");
			while (rs.next())
			{
			   System.out.println("Column 1 returned ");
			   a.setId(rs.getLong(1));
			   a.setTitre(rs.getString(2));
			   a.setContenu(rs.getString(3));
			} rs.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}

		   System.out.println(a.getId() + "nom " + a.getTitre() + " avec :" + a.getContenu());
		return a;
		

		
	}
}
