package com.jsm.bitmexorderbook.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsm.bitmexorderbook.domain.BitmexWebsocketMessage;
import com.jsm.bitmexorderbook.domain.OrderBook;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Service
@AllArgsConstructor
public class OrderBookWebSocketHandler extends TextWebSocketHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderBookWebSocketHandler.class);

    BitmexOrderBookService bitmexOrderBookService;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            BitmexWebsocketMessage wsMsg = objectMapper.readValue(message.getPayload(), BitmexWebsocketMessage.class);

            if (wsMsg.getTable() != null && wsMsg.getTable().equals("orderBookL2_25")) {
                if (wsMsg.getAction().equals("partial")) {
                    bitmexOrderBookService.createOrderBookFromPartial(wsMsg);
                }
            }

        } catch (JsonProcessingException e) {
            LOGGER.info("Exception unmarshalling JSON: ", e);
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        LOGGER.info("established connection - " + session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }
}
