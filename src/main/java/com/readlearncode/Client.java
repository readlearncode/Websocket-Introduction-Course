package com.readlearncode;

/**
 * @author Alex Theedom
 * @version 1.0
 */
public class Client {

    public static final String SERVER = "ws://localhost:8025/chat/myroom";

    public static void main(String[] args) throws Exception {
//        ClientManager client = ClientManager.createClient();
//        String message;
//
//        // connect to server
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Welcome to Tiny Chat!");
//        System.out.println("What's your name?");
//        String user = scanner.nextLine();
//        Session session = client.connectToServer(ChatClientEndpoint.class, new URI(SERVER));
//        System.out.println("You are logged in as: " + user);
//        System.out.println("You are logged into room: " + session.getUserProperties().get("roomName"));
//
//        // repeatedly read a message and send it to the server (until quit)
//        do {
//            message = scanner.nextLine();
//            session.getBasicRemote().sendText(formatMessage(message, user));
//        } while (!message.equalsIgnoreCase("quit"));
    }

}
