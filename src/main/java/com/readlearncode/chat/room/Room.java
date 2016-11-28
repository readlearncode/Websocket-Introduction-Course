package com.readlearncode.chat.room;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.Session;

public class Room {
	private static Room instance = null;
	private List<Session> sessions = new ArrayList<Session>();

	public synchronized void join(Session session) { sessions.add(session); }
	public synchronized void leave(Session session) { sessions.remove(session); }
	public synchronized void sendMessage(String message) {
		for (Session session: sessions) {
			if (session.isOpen()) {
				try { session.getBasicRemote().sendText(message); }
				catch (IOException e) { e.printStackTrace(); }
			}
		}
	}

	public synchronized static Room getRoom() {
		if (instance == null) { instance = new Room(); }
		return instance;
	}
}
