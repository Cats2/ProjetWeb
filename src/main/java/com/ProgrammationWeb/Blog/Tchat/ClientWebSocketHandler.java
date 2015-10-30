package com.ProgrammationWeb.Blog.Tchat;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.ProgrammationWeb.Blog.BDD.JDBC;
import com.ProgrammationWeb.Blog.RestAPI.ChatMessage;

public class ClientWebSocketHandler extends TextWebSocketHandler {

	private static final AtomicInteger clientIds = new AtomicInteger(0);
	private Client client;
	private int id;
	
	
	public ClientWebSocketHandler() {
        this.id = clientIds.getAndIncrement();
    }
	
    @Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    	client = new Client( id, session);
    	GestionClient.addClient(client);
    	DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    	Date d = new Date();
    	JSONObject j = new JSONObject();
    	j.put("etat", 0);
    	GestionClient.broadcast(j.toJSONString());
    }
	
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    	//if(message == null)return;
    	System.out.println("handle");
    	try
    	{
    	JSONObject obj=(JSONObject) JSONValue.parse(message.getPayload().trim());
    	DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    	Date d = new Date(); //time
    	// Add message BDD
    	JDBC.addChatMessage((String)obj.get("username"), (String)obj.get("message"));
    	JSONObject j = new JSONObject();
    	j.put("etat", 1);
    	j.put("user", obj.get("username"));
    	j.put("date", df.format(d));
    	j.put("message", obj.get("message"));
        //session.sendMessage(msg);
    	System.out.println("handle " + j.toJSONString());
        GestionClient.broadcast(j.toJSONString());
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    }
    
    @Override
    public void afterConnectionClosed(WebSocketSession session,
    		CloseStatus status) throws Exception {
    	GestionClient.removeClient(client);
    	GestionClient.broadcast("{etat: 2}");
    }
}