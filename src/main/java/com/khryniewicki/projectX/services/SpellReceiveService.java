package com.khryniewicki.projectX.services;

import com.khryniewicki.projectX.game.attack.spells.spell_properties.SpellDTO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class SpellReceiveService {
    public static Float spellMockPositionX;
    public static Float spellMockPositionY;
    public static Float spellMockPositionZ;

    public SpellReceiveService() {

    }

    public static void receiveSpellMock(SpellDTO spellDTO) {
        spellMockPositionX = spellDTO.getTargetSpellX();
        spellMockPositionY = spellDTO.getTargetSpellY();
        log.info("X: {} Y: {}", spellMockPositionX, spellMockPositionY);
    }
}
