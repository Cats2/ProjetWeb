package com.ProgrammationWeb.Blog.RestAPI;

public class Users {
	
	private long id;
	private String pseudo;
	private String mdp;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String name) {
		this.pseudo = name;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String pass) {
		this.mdp = pass;
	}
}
