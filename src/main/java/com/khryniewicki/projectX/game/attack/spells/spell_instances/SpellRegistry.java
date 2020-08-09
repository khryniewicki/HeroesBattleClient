package com.khryniewicki.projectX.game.attack.spells.spell_instances;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.game.attack.spells.spellbook.fire.Fire;
import com.khryniewicki.projectX.game.attack.spells.spellbook.ice.IceBolt;
import com.khryniewicki.projectX.game.attack.spells.spellbook.thunder.Thunder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpellRegistry {
    private List<Spell> spellbook;

    public List<Spell> getSpellbook() {
        return spellbook;
    }

    private SpellRegistry() {
        this.spellbook = new ArrayList<>();
        fill();
    }

    private void fill() {
        Spell[] spellist = new Spell[]{new Fire(), new IceBolt(), new Thunder()};
        spellbook = Arrays.asList(spellist);
    }

    public static SpellRegistry getInstance() {
        return SpellRegistry.HELPER.INSTANCE;
    }

    private static class HELPER {
        private final static SpellRegistry INSTANCE = new SpellRegistry();
    }
}
