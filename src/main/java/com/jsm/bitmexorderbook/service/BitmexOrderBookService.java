package com.jsm.bitmexorderbook.service;

import com.jsm.bitmexorderbook.domain.BitmexWebsocketMessage;
import com.jsm.bitmexorderbook.domain.OrderBook;
import com.jsm.bitmexorderbook.domain.OrderBookLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class BitmexOrderBookService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BitmexOrderBookService.class);

    OrderBook bitmexOrderBook;

    void initializeOrderBook(BitmexWebsocketMessage msg) {
        this.bitmexOrderBook.setExchange("Bitmex");
        this.bitmexOrderBook.setAsks(new ArrayList<>());
        this.bitmexOrderBook.setBids(new ArrayList<>());
        msg.getData().forEach((dataEl) -> {
            if(dataEl.get("side").equals("Sell")) {
                this.bitmexOrderBook.getAsks().add(new OrderBookLevel(
                        dataEl.get("symbol").toString(),
                        dataEl.get("id").asLong(),
                        dataEl.get("side").toString(),
                        dataEl.get("size").decimalValue(),
                        dataEl.get("price").decimalValue()));
            } else {
                this.bitmexOrderBook.getBids().add(new OrderBookLevel(
                        dataEl.get("symbol").toString(),
                        dataEl.get("id").asLong(),
                        dataEl.get("side").toString(),
                        dataEl.get("size").decimalValue(),
                        dataEl.get("price").decimalValue()));
            }
        });
        LOGGER.info("BITMEX ORDERBOOK CREATED!:" + bitmexOrderBook.toString());
    }

    void updateOrderBook(BitmexWebsocketMessage msg) {
        LOGGER.info("BITMEX ORDERBOOK UPDATE!: " + msg);
        msg.getData().forEach((dataEl) -> {
//            if (dataEl) {
//
//            }
        });
    }

    void deleteOrderBookLevel(BitmexWebsocketMessage msg) {
        LOGGER.info("BITMEX ORDERBOOK DELETE!: " + msg);
    }

    void insertOrderBookLevel(BitmexWebsocketMessage msg) {
        LOGGER.info("BITMEX ORDERBOOK INSERT!: " + msg);
    }
}
