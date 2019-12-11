package com.jsm.bitmexorderbook.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class OrderBook {

    private String exchange;
    private ArrayList<OrderBookLevel> bids;
    private ArrayList<OrderBookLevel> asks;

    public void addLevel(OrderBookLevel level) {

    }

    public void createFromPartial(String partialBook) {

    }
}
