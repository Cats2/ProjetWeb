package com.ProgrammationWeb.Blog.RestAPI;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.ProgrammationWeb.Blog.BDD.JDBC;

@Named
@Path("/")
public class ArticleAPI {
	
	private static List<Article> articles = new ArrayList<Article>();
	
	static {
		/*for (int i = 0; i <= 5 ; i++) {			
			Article art = new Article();
			art.setId(i);
			art.setTitre("Titre" + i);
			art.setContenu("...");
			articles.add(art);			
		}*/
		JDBC.Connection();
		Article a = new Article();
		a = JDBC.getArticle();
		System.out.println("test nom " + a.getTitre());
		articles.add(a);
	}
	
	@GET
	@Path("article")
	@Produces(MediaType.APPLICATION_JSON)
	public Article getArticle(@QueryParam("titre") String titre) {
		System.out.println("param" + titre );
		Article article = null;

		for (Article a : articles) {
			System.out.println("test art one "  + a.getTitre());
			if (a.getTitre().equals(titre))
				article = a;
		}
		return article;
	}
	
}
