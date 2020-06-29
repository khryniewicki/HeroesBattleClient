package com.khryniewicki.projectX.services;

import com.khryniewicki.projectX.config.Application;
import com.khryniewicki.projectX.config.messageHandler.Channels;
import com.khryniewicki.projectX.services.DTO.SpellDTO;
import org.springframework.messaging.simp.stomp.StompSession;

public class SpellSendingService {
    private  Channels channels;


    public SpellSendingService() {
        channels = Channels.getINSTANCE();
    }


    public synchronized void sendSpellToStompSocket(SpellDTO spellDTO) {
        StompSession session = Application.getSession();
        if (session != null) {
            session.send("/app/spell/" + channels.getApp(), spellDTO);
        }
    }
}
