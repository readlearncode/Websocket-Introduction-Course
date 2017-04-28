package com.readlearncode.pingpong;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
@ServerEndpoint(value = "/heartbeat")
public class PongServerEndpoint {

    @OnOpen
    public void onOpen(Session session) throws IOException {
        System.out.println("Open connection");
    }

    @OnMessage
    public void onPongMessage(PongMessage pongMessage) {
        System.out.println("Pong received by server from client " + new String(pongMessage.getApplicationData().array()));
    }

    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        System.out.println("Close connection");
        session.close();
    }

}