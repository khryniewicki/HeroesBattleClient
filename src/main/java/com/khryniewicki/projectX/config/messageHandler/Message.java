package com.khryniewicki.projectX.config.messageHandler;

import com.khryniewicki.projectX.config.messageHandler.ConnectionStatus;
import lombok.Data;

import java.io.Serializable;

@Data
public class Message implements Serializable {
   private String content;
   private String sessionID;
   private ConnectionStatus status;

    public Message() {
    }

    public Message(String content) {
        this.content = content;
    }

    public Message(String sessionID, ConnectionStatus status) {
        this.sessionID = sessionID;
        this.status = status;
    }

    public Message(String content, String sessionID, ConnectionStatus status) {
        this.content = content;
        this.sessionID = sessionID;
        this.status = status;
    }


}