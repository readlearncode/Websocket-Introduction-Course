package com.readlearncode.pingpong;

import org.glassfish.tyrus.server.Server;

import javax.websocket.DeploymentException;
import java.util.Scanner;

/**
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
public class PongServer {

    public static void main(String[] args) throws DeploymentException {
        new Server("localhost", 8080, "/ws", PongServerEndpoint.class).start();
        new Scanner(System.in).nextLine();
    }

}