package org.turkcell.training.spring.order.integration.models;

import lombok.Data;

@Data
public class MyMessage {
    private String dest;
    private String msg;
}
