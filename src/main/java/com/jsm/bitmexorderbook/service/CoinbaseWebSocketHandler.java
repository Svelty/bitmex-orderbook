package com.jsm.bitmexorderbook.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsm.bitmexorderbook.domain.BitmexWebsocketMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Service
@AllArgsConstructor
public class CoinbaseWebSocketHandler extends TextWebSocketHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CoinbaseWebSocketHandler.class);

//    BitmexOrderBookService bitmexOrderBookService;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {

//        LOGGER.info("Coinbase Message: "+ message.getPayload());
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        LOGGER.info("established coin base connection - " + session);

        session.setTextMessageSizeLimit(1000000);

        @Getter
        @ToString
        class CoinbaseMessage {
            String type = "subscribe";
            String[] product_ids = new String[] {"BTC-USD"};
            String[] channels = {"level2", "heartbeat"};
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String string = objectMapper.writeValueAsString(new CoinbaseMessage());
            WebSocketMessage webSocketMessage = new TextMessage(string);
            session.sendMessage(webSocketMessage);
        } catch (Exception e) {
            LOGGER.info("ERROR SENDING MESSAGE TO COINBASE", e);
        }
    }
    //TODO:
    /**
     * Send a WebSocket message: either {@link TextMessage} or {@link BinaryMessage}.
     *
     * <p><strong>Note:</strong> The underlying standard WebSocket session (JSR-356) does
     * not allow concurrent sending. Therefore sending must be synchronized. To ensure
     * that, one option is to wrap the {@code WebSocketSession} with the
     * {@link org.springframework.web.socket.handler.ConcurrentWebSocketSessionDecorator
     * ConcurrentWebSocketSessionDecorator}.
     * @see org.springframework.web.socket.handler.ConcurrentWebSocketSessionDecorator
     */

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

        LOGGER.info("COINBASE CONNECTION CLOSED", status.toString());
        super.afterConnectionClosed(session, status);
    }
}
