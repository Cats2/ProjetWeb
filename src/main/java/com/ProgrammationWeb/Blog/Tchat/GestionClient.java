package com.ProgrammationWeb.Blog.Tchat;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

public class GestionClient {

	private static final ConcurrentHashMap<Integer, Client> clients =
            new ConcurrentHashMap<Integer, Client>();
	
	public static synchronized void addClient(Client client) {
        clients.put(Integer.valueOf(client.getId()), client);
    }

	 public static Collection<Client> getClients() {
	        return Collections.unmodifiableCollection(clients.values());
	    }
	
	public static void broadcast(String message) throws Exception {
        for (Client c : GestionClient.getClients()) {
            c.sendMessage(message);
        }
    }
	
	public static synchronized void removeClient(Client client) {
        clients.remove(Integer.valueOf(client.getId()));
    }
}
