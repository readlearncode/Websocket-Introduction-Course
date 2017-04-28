package com.readlearncode.dukechat.application;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import static com.readlearncode.dukechat.utils.Messages.WELCOME_MESSAGE;

/**
 * @author Alex Theedom
 * @version 1.0
 */
@ServerEndpoint(value = "/echo")
public class EchoEndpoint {

    private final static Logger log = Logger.getLogger(EchoEndpoint.class.getSimpleName());
    private final Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(final Session session) throws IOException, EncodeException {

        // Timeouts after 5 minutes
        session.setMaxIdleTimeout(5 * 60 * 1000);

        // Store session
        sessions.add(session);

        // Send welcome message
        session.getBasicRemote().sendObject(WELCOME_MESSAGE);
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException, EncodeException {
        // echo message back
        session.getBasicRemote().sendObject(message);
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) throws IOException, EncodeException {
       sessions.remove(session);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.info(error::getMessage);
    }

}
