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
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Users> getCustomers() {
		return customers;
	}
	
	@GET
	@Path("customer")
	@Produces(MediaType.APPLICATION_JSON)
	public Users getCustomer(@QueryParam("id") long id) {
		Users customer = null;
		
		for (Users c : customers) {
			if (c.getId() == id)
				customer = c;
		}
		return customer;
	}
	
	@POST
	@Path("customer")
	@Produces(MediaType.APPLICATION_JSON)
	public Users addCustomer(@QueryParam("name") String name, @QueryParam("email") String email) {
		Users customer = new Users();
		customer.setId(customers.size());
		customer.setPseudo(email);
		customer.setMdp(name);
		
		customers.add(customer);
		
		return customer;
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
