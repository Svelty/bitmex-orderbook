package com.jsm.bitmexorderbook.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.*;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BitmexWebsocketMessage extends Object{
    String table;
    String action;
    ArrayList<String> keys;
    ObjectNode types;
    ObjectNode foreignKeys;
    ObjectNode attributes;
    ObjectNode filter;
    ArrayList<ObjectNode> data;
    String info;
    String version;
    String timestamp;
}
