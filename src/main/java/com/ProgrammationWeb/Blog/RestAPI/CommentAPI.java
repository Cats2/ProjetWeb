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
	
	static {
		JDBC.Connection();
	}
	
	@GET
	@Path("commentaires")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Commentaires> getListComment(@QueryParam("titre") String titre) {
		List<Commentaires> commentaires = new ArrayList<Commentaires>();
		commentaires.addAll(JDBC.getCommentaires(titre));
		return commentaires;
	}
	
	@POST
	@Path("addComm")
	@Produces(MediaType.APPLICATION_JSON)
	public void addArticle(@QueryParam("titre") String titre, @QueryParam("pseudo") String pseudo, @QueryParam("contenu") String contenu) {
		JDBC.addCommentaire(pseudo, contenu, titre);
	}

}
