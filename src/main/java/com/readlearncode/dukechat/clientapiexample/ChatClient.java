package com.readlearncode.dukechat.clientapiexample;

import org.glassfish.tyrus.client.ClientManager;

import javax.websocket.Session;
import java.net.URI;
import java.util.Scanner;

/**
 * @author Alex Theedom
 * @version 1.0
 */
public class ChatClient {

    public static void main(String[] args) throws Exception {

        // Greeting message
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello from Pico Chat!");
        System.out.println("Enter your name?");
        String user = scanner.nextLine();

        // Create client
        ClientManager client = ClientManager.createClient();

        // Create connection between client to server
        Session session = client.connectToServer(ClientEndpointExample.class, new URI("ws://localhost:8090/pico/chat"));
        System.out.println("Welcome: " + user);

        String message;
        do {
            message = scanner.nextLine();
            session.getBasicRemote().sendText(message);
        } while (!message.equalsIgnoreCase("quit"));
    }

}
