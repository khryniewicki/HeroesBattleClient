package com.khryniewicki.projectX.game.attack.spells.spellbook;

import com.khryniewicki.projectX.game.attack.spells.spell_properties.Spell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpellBook {
    private List<Spell> spellbook;

    public List<Spell> getSpellbook() {
        return spellbook;
    }

    private SpellBook() {
        this.spellbook = new ArrayList<>();
        fill();
    }

    private void fill() {
        Spell[] spellist = new Spell[]{new Fire(), new Ice(), new Thunder()};
        spellbook = Arrays.asList(spellist);
    }

    public static SpellBook getInstance() {
        return SpellBook.HELPER.INSTANCE;
    }

    private static class HELPER {
        private final static SpellBook INSTANCE = new SpellBook();
    }
}
