package com.khryniewicki.projectX.services;

import com.khryniewicki.projectX.game.Map.Level;
import com.khryniewicki.projectX.game.attack.SpellDTO;
import com.khryniewicki.projectX.game.attack.UltraSpell;
import lombok.Data;

@Data
public class SpellReceiveService {
    public static Float spellMockPositionX;
    public static Float spellMockPositionY;
    public static Float spellMockPositionZ;

    public SpellReceiveService() {

    }

    public static void receiveSpellMock(SpellDTO spellDTO) {

        spellMockPositionX=spellDTO.getTargetSpellX();
        spellMockPositionY=spellDTO.getTargetSpellY();
        System.out.println("X:"+spellMockPositionX+"Y:"+spellMockPositionY);

    }
}
