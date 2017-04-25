package com.readlearncode.application;

import com.readlearncode.domain.Message;
import com.readlearncode.infrastructure.MessageDecoder;
import com.readlearncode.infrastructure.MessageEncoder;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import java.text.SimpleDateFormat;

/**
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
@ClientEndpoint(encoders = MessageEncoder.class, decoders = MessageDecoder.class)
public class ChatClientEndpoint {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

    @OnMessage
    public void onMessage(Message message) {
        System.out.println(String.format("[%s:%s] %s",
                simpleDateFormat.format(message.getReceived()), message.getSender(), message.getContent()));
    }

}
