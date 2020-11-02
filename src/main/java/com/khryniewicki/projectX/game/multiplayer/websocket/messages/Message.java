package com.khryniewicki.projectX.game.multiplayer.websocket.messages;

import lombok.Data;

import java.io.Serializable;

@Data
public class Message implements Serializable {
    private String channel;
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

    public Message(String channel, String content, String sessionID, ConnectionStatus status) {
        this.channel = channel;
        this.content = content;
        this.sessionID = sessionID;
        this.status = status;
    }
}