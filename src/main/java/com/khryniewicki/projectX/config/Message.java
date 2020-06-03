package com.khryniewicki.projectX.config;

import lombok.Data;

import java.io.Serializable;

@Data
public class Message implements Serializable {
   private String content;

    public Message() {
    }

    public Message(String content) {
        this.content = content;
    }
}