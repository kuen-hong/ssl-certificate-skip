package idv.kun.skipssl;

import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class JettyWebsocketTest {

    @WebSocket
    public static class MyWebsocketClient {

        @OnWebSocketConnect
        public void onConnect(Session session) {
            System.out.println("onopen");
        }

        @OnWebSocketClose
        public void onClose(int statusCode, String reason) {
            System.out.println("onclose");
        }

        @OnWebSocketMessage
        public void onMessage(String msg) {
            System.out.println("onmessage="+msg);
        }
    }

    @BeforeAll
    static void setup() {
    }

    @BeforeEach
    void init() {
    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void done() {
    }

    @Test
    public void testWsSkipSSL() throws Exception {
        SslContextFactory.Client ssl = new SslContextFactory.Client(); // ssl config
        ssl.setTrustAll(true);
        WebSocketClient client = new WebSocketClient(ssl);
        MyWebsocketClient wsEndpoint = new MyWebsocketClient();
        client.start();
        URI uri = new URI("wss://localhost/ws");
        Future<Session> f = client.connect(wsEndpoint, uri);
        Session session = f.get(10, TimeUnit.SECONDS);
        System.out.println(session.isOpen());
        session.getRemote().sendString("hello world");

        Thread.sleep(5000);
    }
}
