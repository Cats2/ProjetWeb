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
public class CommentAPI {
	
	private static List<Users> customers = new ArrayList<Users>();
	private static List<Commentaires> commentaires = new ArrayList<Commentaires>();
	
	static {
	
			JDBC.Connection();

			Commentaires com = new Commentaires();
			com.setIdCom(1);
			com.setContenuCom("...");
			commentaires.add(com);
		
	}
	
	@GET
	@Path("commentaire")
	@Produces(MediaType.APPLICATION_JSON)
	public Commentaires getCommentaire(@QueryParam("commentaire") String contenu_com) {
		System.out.println("param" + contenu_com );
		Commentaires article_com = null;
		for (Commentaires a : commentaires) {
			System.out.println("test art one "  + a.getIdCom());
			if (a.getContenuCom().equals(commentaires))
				article_com = a;
		}
		return article_com;
	}
	
	@GET
	@Path("commentaire")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Commentaires> getListComment() {
		return commentaires;
	}
}
