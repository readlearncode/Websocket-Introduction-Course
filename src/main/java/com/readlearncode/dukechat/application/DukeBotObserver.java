package com.readlearncode.dukechat.application;


import com.readlearncode.dukechat.domain.Message;
import com.readlearncode.dukechat.domain.MessageEvent;
import com.readlearncode.dukechat.infrastructure.cdi.MessageReceived;

import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
@Singleton
public class DukeBotObserver {

    private static final Logger logger = Logger.getLogger(DukeBotObserver.class.getName());

    private static Map<String, String> triggers = Collections.unmodifiableMap(createTriggers());

    private static Map<String, String> createTriggers() {
        Map<String, String> triggers = new HashMap<>();
        triggers.put("coffee", "Coffee is a programmers fuel");
        triggers.put("duke", "I hear you talking about me");
        return triggers;
    }

    public static void onMessageReceived(@Observes @MessageReceived MessageEvent messageEvent) {

        logger.log(Level.INFO, "Message received", messageEvent.getMessage().getContent());

        System.out.println("Message received: " + messageEvent.getMessage().getContent());

        System.out.println("Message received: getSession " + messageEvent.getSession());

        String message = messageEvent.getMessage().getContent();

        for(String key : triggers.keySet()){

            if(message.contains(key)){
                sendMessage(new Message(triggers.get(key), "Duke Bot"), messageEvent.getSession());
            }

        }



//        triggers.keySet().stream()
//                .filter(message::contains)
//                .findFirst()
//                .ifPresent(trig -> sendMessage(new Message(triggers.get(trig), "Duke Bot"), messageEvent.getSession()));

    }

    private static void sendMessage(Message message, Session session) {
        try {
            session.getBasicRemote().sendObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EncodeException e) {
            e.printStackTrace();
        }
    }


}
