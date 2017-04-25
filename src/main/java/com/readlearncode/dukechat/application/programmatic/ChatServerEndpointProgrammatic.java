package com.readlearncode.dukechat.application;

import javax.websocket.*;
import java.io.IOException;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
public class ChatServerEndpointProgrammatic extends Endpoint {

    @Override
    public void onOpen(Session session, EndpointConfig config) {
        session.addMessageHandler((MessageHandler.Whole<String>) (String msg) -> {
            try {
                session.getBasicRemote().sendText(msg);
            } catch (IOException e) { }
        });
    }

    // Optional
    public void onClose(Session session, CloseReason closeReason) {

    }

    public void onError(Session session, Throwable thr) {

    }
}