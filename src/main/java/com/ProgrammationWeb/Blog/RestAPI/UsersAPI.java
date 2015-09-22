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
public class UsersAPI {
	
	private static List<Users> customers = new ArrayList<Users>();
	
	static {
		for (int i = 0; i <= 5 ; i++) {
			Users customer = new Users();
			customer.setId(i);
			customer.setPseudo("Customer " + i);
			customer.setMdp("Customer" + i +"@gmail.com");
			customers.add(customer);
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
		System.out.println(customer.getPseudo());
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
	
}
