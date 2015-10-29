package com.ProgrammationWeb.Blog.Tchat;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class Client {

	private final int id;
	private final WebSocketSession session;
	
	 public Client(int id, WebSocketSession session) {
	        this.id = id;
	        this.session = session;
	 }
	 
	 protected void sendMessage(String msg) throws Exception {
	    	session.sendMessage(new TextMessage(msg));
	    }
	 
	 public int getId()
	 {
		 return id;
	 }

	public WebSocketSession getSession() {
		return session;
	}
}
