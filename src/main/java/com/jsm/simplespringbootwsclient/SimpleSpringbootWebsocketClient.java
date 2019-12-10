package com.jsm.simplespringbootwsclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.annotation.PostConstruct;
import java.net.URI;

@Service
public class SimpleSpringbootWebsocketClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleSpringbootWebsocketClient.class);

    @PostConstruct
    public void connect() {
        try {
            WebSocketClient webSocketClient = new StandardWebSocketClient();

            WebSocketSession webSocketSession = webSocketClient.doHandshake(new TextWebSocketHandler() {
                @Override
                public void handleTextMessage(WebSocketSession session, TextMessage message) {
                    LOGGER.info("received message - " + message.getPayload());
                }

                @Override
                public void afterConnectionEstablished(WebSocketSession session) {
                    LOGGER.info("established connection - " + session);
                }
            }, new WebSocketHttpHeaders(), URI.create("wss://www.bitmex.com/realtime?subscribe=orderBookL2_25:XBTUSD")).get();

        } catch (Exception e) {
            LOGGER.error("Exception while accessing websocket", e);
        }
    }
}
