package com.readlearncode.dukechat.client;

import com.readlearncode.dukechat.application.ServerEndpointExample;
import org.glassfish.tyrus.server.Server;

import javax.websocket.DeploymentException;
import java.util.Scanner;

/**
 * @author Alex Theedom
 * @version 1.0
 */
public class ChatServer {

    public static void main(String[] args) {

        // Create server
        Server server = new Server("localhost", 8090, "/pico", ServerEndpointExample.class);

        try {
            server.start();
            System.out.println("To stop press any key.");
            new Scanner(System.in).nextLine();
        } catch (DeploymentException e) {
            throw new RuntimeException(e);
        } finally {
            server.stop();
        }

    }

}
