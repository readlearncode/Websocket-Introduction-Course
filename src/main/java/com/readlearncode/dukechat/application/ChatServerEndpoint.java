package com.readlearncode.dukechat.application;

import com.readlearncode.dukechat.domain.Message;
import com.readlearncode.dukechat.domain.Room;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static com.readlearncode.dukechat.utils.Messages.WELCOME_MESSAGE;
import static com.readlearncode.dukechat.utils.Messages.objectify;

/**
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
public class ChatServerEndpoint extends Endpoint {

    private final static Logger log = Logger.getLogger(ChatServerEndpoint.class.getSimpleName());

    private static final Map<String, Room> rooms = Collections.synchronizedMap(new HashMap<String, Room>());

    private static final String[] roomNames = {"Java EE 7", "Java SE 8", "Websockets", "JSON"};

    @PostConstruct
    public void initialise() {
        Arrays.stream(roomNames).forEach(roomName -> rooms.computeIfAbsent(roomName, new Room(roomName)));
    }

    @Override
    public void onOpen(Session session, EndpointConfig config) {

        // Add Message message handler
        session.addMessageHandler((MessageHandler.Whole<Message>) message ->
                {
                    System.out.println("message " + message);
                    rooms.get(extractRoomFrom(session)).sendMessage(message);
                }
        );

        // Set session level configurations
        String roomName = session.getPathParameters().get("roomName");
        String userName = session.getPathParameters().get("userName");
        session.getUserProperties().putIfAbsent("roomName", roomName);
        session.getUserProperties().putIfAbsent("userName", userName);
        session.setMaxIdleTimeout(5 * 60 * 1000); // Timeouts after 5 minutes

        // Store session
        Room room = rooms.get(roomName);
        room.join(session);

        // Send welcome message
        try {
            session.getBasicRemote().sendObject(objectify(WELCOME_MESSAGE));
        } catch (IOException | EncodeException e) {
            log.info("Welcome message not sent!");
        }

    }

    public void onClose(Session session, CloseReason reason) {
        log.info(reason::getReasonPhrase);
        rooms.get(extractRoomFrom(session)).leave(session);
    }

    public void onError(Session session, Throwable error) {
        log.info(error::getMessage);
    }

    private String extractRoomFrom(Session session) {
        return ((String) session.getUserProperties().get("roomName"));
    }

    static Map<String, Room> getRooms() {
        return rooms;
    }

}