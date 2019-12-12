package com.jsm.bitmexorderbook.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@Component
public class OrderBook {

    private String exchange;
    private ArrayList<OrderBookLevel> bids;
    private ArrayList<OrderBookLevel> asks;

}
