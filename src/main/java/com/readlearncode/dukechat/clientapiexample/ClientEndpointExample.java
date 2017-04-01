package com.readlearncode.dukechat.clientapiexample;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
@ClientEndpoint
public class ClientEndpointExample {

    @OnMessage
    public void onMessage(String message) {
        System.out.println(message);
    }

}