package com.jsm.bitmexorderbook.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import javax.annotation.PostConstruct;
import java.net.URI;

@Service
public class SimpleSpringbootWebsocketClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleSpringbootWebsocketClient.class);

    @PostConstruct
    public void connect() {
        try {
            WebSocketClient webSocketClient = new StandardWebSocketClient();

            WebSocketSession webSocketSession = webSocketClient.doHandshake(new OrderBookWebSocketHandler(),
                    new WebSocketHttpHeaders(),
                    URI.create("wss://www.bitmex.com/realtime?subscribe=orderBookL2_25:XBTUSD")
            ).get();

        } catch (Exception e) {
            LOGGER.error("Exception while accessing websocket", e);
        }
    }
}
