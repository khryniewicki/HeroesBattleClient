package com.khryniewicki.projectX.services;

import com.khryniewicki.projectX.game.attack.spells.spell_instances.BasicSpell;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.SpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.UltimateSpell;
import com.khryniewicki.projectX.game.attack.spells.spell_properties.Spell;
import com.khryniewicki.projectX.game.attack.spells.spellbook.SpellBook;
import com.khryniewicki.projectX.services.DTO.SpellDTO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@Slf4j
public class SpellReceiveService {
    public static Float spellMockPositionX;
    public static Float spellMockPositionY;
    public static SpellInstance spellInstance;
    private static List<Spell> spellBook;

    public SpellReceiveService() {

    }

    public static void receiveSpellMock(SpellDTO spellDTO) {
       if (spellBook==null) {
           SpellBook book = SpellBook.getInstance();
           spellBook = book.getSpellbook();
       }
        spellMockPositionX = spellDTO.getTargetSpellX();
        spellMockPositionY = spellDTO.getTargetSpellY();
        spellInstance = spellAdapter(spellDTO.getName());
        log.info("X: {} Y: {}", spellMockPositionX, spellMockPositionY);
    }

    private static SpellInstance spellAdapter(String name) {

        SpellInstance spellInstance = null;

        for (Spell spell : spellBook) {
            if (name.equals(spell.getName())) {
                if (spell.isBasic()) {
                    spellInstance = new BasicSpell(spell);
                } else
                    spellInstance = new UltimateSpell(spell);
            }

        }
        return spellInstance;
    }

}
