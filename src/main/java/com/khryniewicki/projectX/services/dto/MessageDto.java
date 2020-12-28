package com.khryniewicki.projectX.services.dto;

import com.khryniewicki.projectX.game.multiplayer.websocket.states.ConnectionState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@NoArgsConstructor
@Setter
@Getter
public class MessageDto implements Serializable, BaseDto {
    private String channel;
    private String content;
    private String sessionID;
    private String playerName;
    private ConnectionState status;
    private BaseDtoType type;

    public MessageDto(Builder builder) {
        this.content = builder.content;
        this.sessionID = builder.sessionID;
        this.playerName = builder.playerName;
        this.status = builder.status;
        this.type = BaseDtoType.MESSAGE;
    }

    public static class Builder {
        private String content;
        private String sessionID;
        private String playerName;
        private ConnectionState status;

        public Builder heroType(String heroType) {
            this.content = heroType;
            return this;
        }

        public Builder sessionID(String sessionID) {
            this.sessionID = sessionID;
            return this;
        }

        public Builder playerName(String playerName) {
            this.playerName = playerName;
            return this;
        }

        public Builder status(ConnectionState status) {
            this.status = status;
            return this;
        }

        public MessageDto build() {
            return new MessageDto(this);
        }
    }

    @Override
    public String toString() {
        return "Message{" +
                "channel='" + channel + '\'' +
                ", content='" + content + '\'' +
                ", sessionID='" + sessionID + '\'' +
                ", playerName='" + playerName + '\'' +
                ", status=" + status +
                '}';
    }
}