package com.readlearncode.dukechat.infrastructure;

import com.readlearncode.dukechat.domain.Message;

import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.time.LocalTime;

/**
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
public class MessageEncoder implements Encoder.Text<Message> {

    @Override
    public String encode(final Message message) throws EncodeException {
        return Json.createObjectBuilder()
                .add("content", message.getContent())
                .add("sender", message.getSender())
                .add("received", LocalTime.now().toString())
                .build().toString();
    }

    @Override
    public void init(EndpointConfig config) {
        // Not implemented
    }

    @Override
    public void destroy() {
        // Not implemented
    }
}
