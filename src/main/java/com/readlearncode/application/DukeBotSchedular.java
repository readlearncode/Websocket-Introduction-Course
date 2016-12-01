package com.readlearncode.application;


import com.readlearncode.utils.Messages;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
@Stateless
public class DukeBotSchedular {

    private static final Logger logger = Logger.getLogger(DukeBotSchedular.class.getName());

    private Map<String, String> triggers = Collections.unmodifiableMap(createTriggers());

    private Map<String, String> createTriggers() {
        Map<String, String> triggers = new HashMap<>();
        triggers.put("coffee", "Coffee is a programmers fuel");
        triggers.put("duke", "I hear you talking about me");
        return triggers;
    }

    @Schedule(minute = "*/1", hour = "*")
    private void interrupt() {
        ChatServerEndpoint.rooms.forEach((s, room) -> room.sendMessage(Messages.objectify("Hello from Duke Bot")));
    }


}
