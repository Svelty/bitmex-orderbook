package com.jsm.bitmexorderbook.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class OrderBookLevel {
    String symbol;
    Long id;
    String side;
    BigDecimal size;
    BigDecimal price;
}
