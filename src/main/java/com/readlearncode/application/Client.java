package com.readlearncode.application;

import java.util.Scanner;

/**
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
public class Client {

    public static void main(String[] args) throws Exception {

        // TODO: Create the client instance

        // Start welcome code
        String message;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Tiny Chat!");
        System.out.println("What's your name?");
        String user = scanner.nextLine();
        // End welcome code

        // TODO: Connect to the server endpoint

        System.out.println("You are logged in as: " + user);

        do {
            // repeatedly read a message and send it to the server (until quit)
            message = scanner.nextLine();
            // TODO: Send message to server endpoint
        } while (!message.equalsIgnoreCase("quit"));
    }

}
