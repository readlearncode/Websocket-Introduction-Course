package com.readlearncode.application;

import com.readlearncode.domain.Message;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
public class DukeBotTest {

    private Map<String, String> triggers = Collections.unmodifiableMap(createTriggers());

    private Map<String, String> createTriggers() {
        Map<String, String> triggers = new HashMap<>();
        triggers.put("coffee", "Coffee is a programmers fuel");
        triggers.put("duke", "I hear you talking about me");
        return triggers;
    }

    @Test
    public void GivenMessage_WhenContainsTriggerWord_ShouldProduceMessage() {

        // arrange
        String message = "I like coffee";

        // act
        Optional<Message> dukeBotMessage = triggers.entrySet().stream()
                .filter(tt -> message.contains(tt.getKey()))
                .findFirst()
                .map(trig -> new Message(trig.getValue(), "Duke Bot"));

        // assert
        assertEquals("Coffee is a programmers fuel", dukeBotMessage.get().getContent());

    }

}
