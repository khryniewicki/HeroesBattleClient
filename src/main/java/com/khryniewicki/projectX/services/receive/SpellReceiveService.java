package com.khryniewicki.projectX.services.receive;

import com.khryniewicki.projectX.game.attack.spell.instance.BasicSpellInstance;
import com.khryniewicki.projectX.game.attack.spell.instance.SpellInstance;
import com.khryniewicki.projectX.game.attack.spell.instance.SpellRegistry;
import com.khryniewicki.projectX.game.attack.spell.instance.UltimateSpellInstance;
import com.khryniewicki.projectX.game.attack.spell.settings.Spell;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.services.dto.SpellDto;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Setter
@Getter
@Slf4j
public class SpellReceiveService {
    private Position basicSpellTarget;
    private Position ultimateSpellTarget;
    private SpellInstance spellInstance;
    private List<Spell> spellBook;

    public SpellReceiveService() {
        createSpellBook();
    }

    public void set_spell_mock(SpellDto spellDTO) {
        spellInstance = spellAdapter(spellDTO.getName());
        if (spellInstance.isBasic()) {
            basicSpellTarget = get_spell_target(spellDTO);
        } else {
            ultimateSpellTarget = get_spell_target(spellDTO);
        }
    }

    private static Position get_spell_target(SpellDto spellDTO) {
        return new Position(spellDTO.getTargetSpellX(), spellDTO.getTargetSpellY());
    }

    private void createSpellBook() {
        if (spellBook == null) {
            SpellRegistry book = SpellRegistry.getInstance();
            spellBook = book.getSpellbook();
        }
    }

    private SpellInstance spellAdapter(String name) {

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

    void reset() {
        basicSpellTarget = null;
        ultimateSpellTarget = null;
    }

}
