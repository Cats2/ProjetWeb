package com.ProgrammationWeb.Blog.RestAPI;

import java.sql.Date;

public class ChatMessage {

	 private String content;
	 private String user;
	 private Date dateposte;

	    public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Date getDateposte() {
		return dateposte;
	}

	public void setDateposte(Date dateposte) {
		this.dateposte = dateposte;
	}

	public void setContent(String content) {
		this.content = content;
	}

	    public String getContent() {
	        return content;
	    }
}
