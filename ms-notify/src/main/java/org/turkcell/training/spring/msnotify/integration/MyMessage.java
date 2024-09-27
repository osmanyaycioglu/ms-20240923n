package org.turkcell.training.spring.msnotify.integration;

import lombok.Data;

@Data
public class MyMessage {
    private String dest;
    private String msg;
}
