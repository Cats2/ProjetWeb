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

@Named
@Path("/")
public class CommentAPI {
	
	private static List<Users> customers = new ArrayList<Users>();
	private static List<Article> articles = new ArrayList<Article>();
	
	static {
		for (int i = 0; i <= 5 ; i++) {
			Users customer = new Users();
			customer.setId(i);
			customer.setPseudo("Customer " + i);
			customer.setMdp("Customer" + i +"@gmail.com");
			customers.add(customer);
			
			Article art = new Article();
			art.setId(i);
			art.setTitre("Titre" + i);
			art.setContenu("...");
			articles.add(art);
		}
	}	
}
