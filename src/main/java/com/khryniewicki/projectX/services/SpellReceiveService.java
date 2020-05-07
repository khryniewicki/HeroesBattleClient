package com.khryniewicki.projectX.services;

import com.khryniewicki.projectX.game.Map.Level;
import com.khryniewicki.projectX.game.attack.SpellDTO;
import lombok.Data;

@Data
public class SpellReceiveService {
    public static Float spellMockPositionX;
    public static Float spellMockPositionY;
    public static Float spellMockPositionZ;

    public SpellReceiveService() {
        spellMockPositionX = Level.heroMock.getPosition().x;
        spellMockPositionY = Level.heroMock.getPosition().y;
    }

    public static void receiveSpellMock(SpellDTO spellDTO) {
        spellMockPositionX+=(Level.heroMock.getPosition().x-spellDTO.getTargetSpellX())/50;
        spellMockPositionX+=(Level.heroMock.getPosition().x-spellDTO.getTargetSpellX())/50;
    }
}
