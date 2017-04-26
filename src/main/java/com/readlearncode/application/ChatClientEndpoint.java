package com.readlearncode.application;

import com.readlearncode.domain.Message;

import java.text.SimpleDateFormat;

/**
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
// TODO Add client endpoint annotation
public class ChatClientEndpoint {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

    // TODO: add OnMessage annotation.
    public void onMessage(Message message) {
        System.out.println(String.format("[%s:%s] %s",
                simpleDateFormat.format(message.getReceived()), message.getSender(), message.getContent()));
    }

}
