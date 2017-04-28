package com.readlearncode.pingpong;

import org.glassfish.tyrus.client.ClientManager;

import javax.websocket.Session;
import java.net.URI;
import java.nio.ByteBuffer;

/**
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
public class PingClient {

    public static void main(String[] args) throws Exception {

        Session session = ClientManager.createClient().connectToServer(
                PingClientEndpoint.class,
                new URI("ws://localhost:8080/ws/heartbeat"));

        do {

            Thread.sleep(10_000);
            System.out.println("Client sending Pong");
            session.getBasicRemote().sendPong(ByteBuffer.wrap("PONG".getBytes()));

            Thread.sleep(10_000);
            System.out.println("Client sending Ping");
            session.getBasicRemote().sendPing(ByteBuffer.wrap("PING".getBytes()));

        } while (true);

    }

}