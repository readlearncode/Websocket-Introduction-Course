package com.readlearncode.dukechat.application;

import com.readlearncode.dukechat.domain.Message;
import com.readlearncode.dukechat.domain.MessageEvent;
import com.readlearncode.dukechat.domain.Room;
import com.readlearncode.dukechat.infrastructure.MessageDecoder;
import com.readlearncode.dukechat.infrastructure.MessageEncoder;
import com.readlearncode.dukechat.infrastructure.cdi.MessageReceived;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.readlearncode.dukechat.utils.Messages.WELCOME_MESSAGE;
import static com.readlearncode.dukechat.utils.Messages.objectify;

/**
 * @author Alex Theedom
 * @version 1.0
 */
@ApplicationScoped
@ServerEndpoint(value = "/chat/{roomName}", encoders = MessageEncoder.class, decoders = MessageDecoder.class)
public class ChatServerEndpoint {

    public static final Map<String, Room> rooms = Collections.synchronizedMap(new HashMap<String, Room>());

    private static String[] roomNames = {"Java EE 7", "Java SE 8", "Websockets", "JSON"};


    @Inject
    @MessageReceived
    private Event<MessageEvent> messageReceived;


    @PostConstruct
    public void initialise() {
        Arrays.stream(roomNames).forEach(roomName -> rooms.computeIfAbsent(roomName, new Room(roomName)));
    }

    @OnOpen
    public void onOpen(final Session session, @PathParam("roomName") final String roomName) {

        Room room = rooms.get(roomName);
        session.getUserProperties().putIfAbsent("roomName", roomName);

        session.setMaxIdleTimeout(5 * 60 * 1000); // Timeouts after 5 minutes

        room.join(session);
        room.sendMessage(objectify(WELCOME_MESSAGE));
    }

    @OnMessage
    public void onMessage(Message message, Session session) throws IOException, EncodeException {

        System.out.println("Message received onMessage");

        rooms.get(extractRoomFrom(session)).sendMessage(message);
        messageReceived.fire(new MessageEvent(message, session));
    }

    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        rooms.get(extractRoomFrom(session)).leave(session);
    }

    private String extractRoomFrom(Session session) {
        return ((String) session.getUserProperties().get("roomName"));
    }

}
