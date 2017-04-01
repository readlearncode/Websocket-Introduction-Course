package com.readlearncode.dukechat.clientapiexample;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
@ServerEndpoint(value = "/chat")
public class ServerEndpointExample {

    private static List<Session> clients = Collections.synchronizedList(new ArrayList<>());

    @OnOpen
    public void onOpen(Session session) throws IOException, EncodeException {

        // Add new clientapiexample to the list of clients
        clients.add(session);

        // Broadcast that user has entered the room
        for (Session client : clients) {
            // Filter sender
            if (!session.getId().equals(client.getId())) {
                // Send text message
                client.getBasicRemote().sendText("A new person has entered the room. Say Hello!");
            }
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException, EncodeException {

        // Send message to all clients
        for (Session client : clients) {
            // Filter sender
            if (!session.getId().equals(client.getId())) {
                // Send text message
                client.getBasicRemote().sendText(message);
            }
        }
    }

    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {

        // Remove clientapiexample from clientapiexample list
        clients.remove(session);

    }

}