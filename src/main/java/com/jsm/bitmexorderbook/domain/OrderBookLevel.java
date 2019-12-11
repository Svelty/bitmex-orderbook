package com.jsm.bitmexorderbook.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class OrderBookLevel {
    String symbol;
    Long id;
    String side;
    BigDecimal size;
    BigDecimal price;
}
