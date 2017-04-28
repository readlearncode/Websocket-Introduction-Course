package com.readlearncode.pingpong;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.PongMessage;

/**
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
@ClientEndpoint
public class PingClientEndpoint {

    @OnMessage
    public void onMessage(PongMessage pongMessage) {
        System.out.println("Pong received by client from server: " + new String(pongMessage.getApplicationData().array()));
    }

}