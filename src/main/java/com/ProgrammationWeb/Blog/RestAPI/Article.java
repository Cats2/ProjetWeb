package com.ProgrammationWeb.Blog.RestAPI;

import java.sql.Date;

public class Article {
	
	private long id;
	private String titre;
	private String contenu;
	private String pseudo_user;
	private Date date_post;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

}
