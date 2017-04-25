package com.readlearncode.dukechat.application;

import com.readlearncode.MyEndpointTextClient;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;


/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
@RunWith(Arquillian.class)
public class EchoEndpointTest {

    final String TEXT = "Hello World!";

    @ArquillianResource
    URI base;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(EchoEndpoint.class, MyEndpointTextClient.class);
    }


    @Test
    public void testTextEndpoint() throws URISyntaxException, IOException, InterruptedException, DeploymentException {
        MyEndpointTextClient.latch = new CountDownLatch(1);
        Session session = connectToServer(MyEndpointTextClient.class, "echo");
        assertNotNull(session);
        assertTrue(MyEndpointTextClient.latch.await(2, TimeUnit.SECONDS));
        assertEquals(TEXT, MyEndpointTextClient.response);
    }

    public Session connectToServer(Class<?> endpoint, String uriPart) throws DeploymentException, IOException, URISyntaxException {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        URI uri = new URI("ws://"
                + base.getHost()
                + ":"
                + base.getPort()
                + base.getPath()
                + uriPart);
        System.out.println("URI: " + uri);
        return container.connectToServer(endpoint, uri);
    }

}
