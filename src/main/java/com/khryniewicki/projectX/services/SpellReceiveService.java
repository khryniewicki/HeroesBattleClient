package com.khryniewicki.projectX.services;

import com.khryniewicki.projectX.game.attack.spells.spell_instances.BasicSpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.SpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.SpellRegistry;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.UltimateSpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.services.DTO.SpellDTO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@Slf4j
public class SpellReceiveService {
    public static Position basicSpellTarget;
    public static Position ultimateSpellTarget;
    public static SpellInstance spellInstance;
    private static List<Spell> spellBook;

    public static void receiveSpellMock(SpellDTO spellDTO) {
        createSpellBook();
        spellInstance = spellAdapter(spellDTO.getName());
        if (spellInstance.isBasic()) {
            basicSpellTarget = new Position(spellDTO.getTargetSpellX(), spellDTO.getTargetSpellY());
        } else {
            ultimateSpellTarget = new Position(spellDTO.getTargetSpellX(), spellDTO.getTargetSpellY());
        }
        log.info("Name: {} , X: {} Y: {}", spellDTO.getName(), basicSpellTarget.getX(), basicSpellTarget.getY());
    }

    private static void createSpellBook() {
        if (spellBook == null) {
            SpellRegistry book = SpellRegistry.getInstance();
            spellBook = book.getSpellbook();
        }
    }

    private static SpellInstance spellAdapter(String name) {

        SpellInstance spellInstance = null;

        for (Spell spell : spellBook) {
            if (name.equals(spell.getName())) {
                if (spell.isBasic()) {
                    spellInstance = new BasicSpellInstance(spell);
                } else
                    spellInstance = new UltimateSpellInstance(spell);
            }
        }
        return spellInstance;
    }

}
