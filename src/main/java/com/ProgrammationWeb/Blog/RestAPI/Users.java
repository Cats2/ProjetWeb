package com.ProgrammationWeb.Blog.RestAPI;

import java.sql.Date;

public class Users {
	
	private long id;
	private String pseudo;
	private String mdp;
	private Date datecrea;
	private String role;
	private int nbart;
	
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
	public Date getDate() {
		return datecrea;
	}
	public void setDate(Date datecrea) {
		this.datecrea = datecrea;
	}
	public int getNbArt() {
		return nbart;
	}
	public void setNbArt(int nbart) {
		this.nbart = nbart;
	}
	public void setRole(String role)
	{
		this.role = role;
	}
	public String getRole()
	{
		return role;
	}
}
