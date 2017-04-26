package com.readlearncode.dukechat.application;

import com.readlearncode.dukechat.domain.Message;
import com.readlearncode.dukechat.domain.Room;
import com.readlearncode.dukechat.infrastructure.MessageDecoder;
import com.readlearncode.dukechat.infrastructure.MessageEncoder;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.nio.ByteBuffer;
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
@ServerEndpoint(value = "/chat/{roomName}/{userName}", encoders = MessageEncoder.class, decoders = MessageDecoder.class)
public class ChatServerEndpoint {

    private final static Logger log = Logger.getLogger(ChatServerEndpoint.class.getSimpleName());

    private static final Map<String, Room> rooms = Collections.synchronizedMap(new HashMap<String, Room>());

    private static final String[] roomNames = {"Java EE 7", "Java SE 8", "Websockets", "JSON"};

    @PostConstruct
    public void initialise() {
        Arrays.stream(roomNames).forEach(roomName -> rooms.computeIfAbsent(roomName, new Room(roomName)));
    }

    @OnOpen
    public void onOpen(final Session session, @PathParam("roomName") final String roomName, @PathParam("userName") final String userName,EndpointConfig conf) throws IOException, EncodeException {

        // Set session level configurations
        session.getUserProperties().putIfAbsent("roomName", roomName);
        session.getUserProperties().putIfAbsent("userName", userName);

        // Timeouts after 5 minutes
        session.setMaxIdleTimeout(5 * 60 * 1000);

        // Store session in room
        Room room = rooms.get(roomName);
        room.join(session);

        // Send welcome message
        session.getBasicRemote().sendObject(objectify(WELCOME_MESSAGE));
    }

    @OnMessage
    public void onMessage(Session session, Message message) throws IOException, EncodeException {
        rooms.get(extractRoomFrom(session)).sendMessage(message);
    }

    @OnMessage
    public void onBinaryMessage(ByteBuffer message, Session session) {
        // Not implemented
    }

    @OnMessage
    public void onPongMessage(PongMessage message, Session session) {
        // Not implemented
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) throws IOException, EncodeException {
        log.info(reason::getReasonPhrase);
        rooms.get(extractRoomFrom(session)).leave(session);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.info(error::getMessage);
        // implement error handling
    }

    /**
     * Extracts the room from the session
     *
     * @param session the session object
     * @return the room name
     */
    private String extractRoomFrom(Session session) {
        return ((String) session.getUserProperties().get("roomName"));
    }

    /**
     * Returns the list of rooms in chat application
     * @return Map of room names to room instances
     */
    static Map<String, Room> getRooms() {
        return rooms;
    }

}
