package com.jsm.bitmexorderbook.service;

import com.jsm.bitmexorderbook.domain.OrderBook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import javax.annotation.PostConstruct;
import java.net.URI;

@Service
public class BitmexWebsocketClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(BitmexWebsocketClient.class);

    @PostConstruct
    public void connect() {
        try {
            WebSocketClient webSocketClient = new StandardWebSocketClient();

            WebSocketSession webSocketSession = webSocketClient.doHandshake(new BitmexWebSocketHandler(new BitmexOrderBookService(new OrderBook())),
                    new WebSocketHttpHeaders(),
                    URI.create("wss://www.bitmex.com/realtime?subscribe=orderBookL2_25:XBTUSD")
            ).get();

        } catch (Exception e) {
            LOGGER.error("Exception while accessing websocket", e);
        }
    }
}
