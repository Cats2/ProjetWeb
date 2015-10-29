/* MOD-ICA fichier entier*/
package com.ProgrammationWeb.Blog.RestAPI;

import java.sql.Date;

public class Commentaires {
	
	private long id_com;
	private String contenu_com;
	private String pseudo_user_com;
	private Date date_post_com;
	
	public long getIdCom() {
		return id_com;
	}
	public void setIdCom(long id) {
		this.id_com = id;
	}
	
	public String getContenuCom() {
		return contenu_com;
	}
	public void setContenuCom(String contenu) {
		this.contenu_com = contenu;
	}

}
