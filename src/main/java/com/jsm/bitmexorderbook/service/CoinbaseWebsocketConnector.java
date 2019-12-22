package com.jsm.bitmexorderbook.service;

import com.jsm.bitmexorderbook.domain.OrderBook;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class CoinbaseWebsocketConnector {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoinbaseWebsocketConnector.class);

//    @PostConstruct
    public void doStuff() {

        try {//new CoinbaseOrderBookService(new OrderBook()
            WebSocketClient webSocketClient = new StandardWebSocketClient();
            WebSocketSession webSocketSession = webSocketClient.doHandshake(new CoinbaseWebSocketHandler(),
                    new WebSocketHttpHeaders(),
                    URI.create("wss://ws-feed.pro.coinbase.com")
            ).get();
        } catch (Exception e) {
            LOGGER.error("Exception while accessing websocket", e);
        }
    }
}
