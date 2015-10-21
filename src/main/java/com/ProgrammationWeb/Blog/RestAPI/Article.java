package com.ProgrammationWeb.Blog.RestAPI;

import java.sql.Date;

public class Article {
	
	private long id;
	private String titre;
	private String contenu;
	private String pseudo_user;
	private Date dateCrea;
	private String image;
	private String categ;
	private int avis;
	
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
	
	public String getCateg() {
		return categ;
	}
	public void setCateg(String categ) {
		this.categ = categ;
	}
	
	public void setDateCrea(Date dateCrea)
	{
		this.dateCrea = dateCrea;
	}
	public Date getDateCrea()
	{
		return dateCrea;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public int getAvis() {
		return avis;
	}
	public void setAvis(int avis) {
		this.avis = avis;
	}

}
