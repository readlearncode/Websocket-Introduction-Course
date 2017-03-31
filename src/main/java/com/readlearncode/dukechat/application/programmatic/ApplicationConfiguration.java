package com.readlearncode.dukechat.application.programmatic;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;
import java.util.HashSet;
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

        Set<ServerEndpointConfig> endpointConfigs = new HashSet<>(1);

        endpointConfigs.add(ServerEndpointConfig.Builder.create(ChatServerEndpoint.class, "/chat").build());

        return endpointConfigs;
    }

    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
        return scanned;
    }
}