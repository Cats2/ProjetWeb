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

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProgrammationWeb.Blog.BDD.JDBC;


@Named
@Path("/")
@RestController
public class UsersAPI {
	
	private static List<Users> customers = new ArrayList<Users>();
	private static Users userco = null;
	static {
		JDBC.Connection();
		userco = new Users();
	}
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Users> getCustomers() {
		return customers;
	}
	
	@GET
	@Path("user")
	@Produces(MediaType.APPLICATION_JSON)
	public Users getCustomer(@QueryParam("pseudo") String pseudo) {
		Users customer = null;
		customer = JDBC.getUser(pseudo);
		return customer;
	}
	
	@POST
	@Path("addUser")
	@Produces(MediaType.TEXT_PLAIN)
	public String addCustomer(@QueryParam("login") String login, @QueryParam("pass") String pass) {
		System.out.println("Users à add " + login);
		System.out.println("Mdp à add" + pass);
		String st = JDBC.addUser(login, pass);
		return st;
	}
	
	@POST
	@Path("deleteUser")
	@Produces(MediaType.TEXT_PLAIN)
	public Boolean deleteCustomer(@QueryParam("login") String login) {
		System.out.println("Users à add " + login);
		Boolean bl = JDBC.deleteUser(login);
		return bl;
	}
	
	@POST
	@Path("connexion")
	@Produces(MediaType.TEXT_PLAIN)
	public String connexion(@QueryParam("login") String login, @QueryParam("pass") String pass) {
		System.out.println("login :" + login);
		System.out.println("pass :" + pass);
		String st = JDBC.Connexion(login, pass);
		if(st.contains("connecté"))
		{
			userco.setPseudo(login);
			userco.setMdp(pass);
			userco.setId(1);
		}
		return st;
	}
}
