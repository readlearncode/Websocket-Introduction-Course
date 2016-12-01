package com.readlearncode.dukechat.domain;

import javax.websocket.Session;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
public class MessageEvent {

    private Session session;
    private Message message;

    public MessageEvent(Message message, Session session) {
        this.message = message;
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
