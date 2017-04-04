package com.readlearncode.dukechat.application;

import com.readlearncode.dukechat.infrastructure.MessageDecoder;
import com.readlearncode.dukechat.infrastructure.MessageEncoder;

import javax.websocket.Decoder;
import javax.websocket.Encoder;
import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
public class ApplicationConfiguration implements ServerApplicationConfig {

    @Override
    public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> endpointClasses) {

        Set<ServerEndpointConfig> endpointConfigs = new HashSet<>();

        List<Class<? extends Decoder>> decoders = new ArrayList<>();
        decoders.add(MessageDecoder.class);

        List<Class<? extends Encoder>> encoder = new ArrayList<>();
        encoder.add(MessageEncoder.class);

        endpointConfigs.add(
                ServerEndpointConfig.Builder.create(ChatServerEndpoint.class, "/chat/{roomName}/{userName}")
                        .decoders(decoders)
                        .encoders(encoder)
                        .build());

        return endpointConfigs;
    }

    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
        return scanned;
    }
}