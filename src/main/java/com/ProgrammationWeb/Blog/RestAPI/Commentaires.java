/* MOD-ICA fichier entier*/
package com.ProgrammationWeb.Blog.RestAPI;

import java.sql.Date;

public class Commentaires {
	
	private long id_com;
	private String contenu;
	private String pseudous;
	private Date dateposte;
	
	public long getIdCom() {
		return id_com;
	}
	public void setIdCom(long id) {
		this.id_com = id;
	}
	
	public String getContenuCom() {
		return contenu;
	}
	public void setContenuCom(String contenu) {
		this.contenu = contenu;		
	}
	public String getPseudous() {
		return pseudous;
	}
	public void setPseudous(String pseudous) {
		this.pseudous = pseudous;
	}
	public Date getDateposte() {
		return dateposte;
	}
	public void setDateposte(Date dateposte) {
		this.dateposte = dateposte;
	}

}
