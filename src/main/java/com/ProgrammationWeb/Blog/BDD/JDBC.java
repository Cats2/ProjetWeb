package com.ProgrammationWeb.Blog.BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


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
	
	public static void getArticle()
	{
		System.out.println("Get Article");
		Statement st;
		try {
			st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT titre FROM article");
			while (rs.next())
			{
			   System.out.println("Column 1 returned ");
			   System.out.println(rs.getString(1));
			} rs.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}
}
