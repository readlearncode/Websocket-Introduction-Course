package com.readlearncode.dukechat.domain;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.lang.String.format;

/**
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
public class Room implements Function<String, Room> {

    private String name;
    private List<Session> sessions = new ArrayList<>();

    @Override
    public Room apply(String name) {
        return new Room(name);
    }

    public Room(String name) {
        this.name = name;
    }

    public synchronized void join(Session session) {
        sessions.add(session);
    }

    public synchronized void leave(Session session) {
        sendMessage(new Message("Duke Bot", format("%s left the chat room", (String) session.getUserProperties().get("userName"))));
        sessions.remove(session);
    }

    public synchronized void sendMessage(Message message) {
        sessions.parallelStream()
                .filter(Session::isOpen)
                .forEach(session -> sendMessage(message, session));
    }


    private void sendMessage(Message message, Session session) {
        try {
            session.getBasicRemote().sendObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EncodeException e) {
            e.printStackTrace();
        }
    }

}
