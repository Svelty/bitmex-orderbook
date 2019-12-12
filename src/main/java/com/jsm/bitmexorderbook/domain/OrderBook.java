package com.jsm.bitmexorderbook.domain;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
@ToString
public class OrderBook {

    
    private String exchange;
    private ArrayList<OrderBookLevel> bids;
    private ArrayList<OrderBookLevel> asks;

}
