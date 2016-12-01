package com.readlearncode.dukechat.application;

import com.readlearncode.dukechat.domain.Message;
import com.readlearncode.dukechat.infrastructure.MessageDecoder;
import com.readlearncode.dukechat.infrastructure.MessageEncoder;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import java.text.SimpleDateFormat;

/**
 * @author Alex Theedom
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
