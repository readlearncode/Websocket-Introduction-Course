package com.readlearncode.dukechat.infrastructure;

import com.readlearncode.dukechat.domain.Message;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @author Alex Theedom
 * @version 1.0
 */
public class MessageEncoder implements Encoder.Text<Message> {

    @Override
    public String encode(final Message message) throws EncodeException {
        // TODO: Implement code that encodes the Message object and returns a JSON String representation
        return "";
    }

    @Override
    public void init(EndpointConfig config) {
        // not implemented
    }

    @Override
    public void destroy() {
        // not implemented
    }
}
