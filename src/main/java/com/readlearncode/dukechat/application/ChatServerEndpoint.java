package com.readlearncode.dukechat.application;

import com.readlearncode.dukechat.domain.Message;
import com.readlearncode.dukechat.domain.Room;
import com.readlearncode.dukechat.infrastructure.MessageDecoder;
import com.readlearncode.dukechat.infrastructure.MessageEncoder;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

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
    public void onOpen(final Session session,
                       @PathParam("roomName") final String roomName,
                       @PathParam("userName") final String userName )  {
        // Implement open session logic
    }

    @OnMessage
    public void onMessage(Session session, Message message)  {
        // Implement message receive logic
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
    public void onClose(Session session, CloseReason reason)  {
        // Implement close session logic
    }

    @OnError
    public void onError(Session session, Throwable error) {
        // Implement error logic
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
