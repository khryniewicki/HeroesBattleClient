package com.khryniewicki.projectX.game.attack.spells.spell_instances;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.game.attack.spells.spellbook.blackKing.BlackFire;
import com.khryniewicki.projectX.game.attack.spells.spellbook.blackKing.Skull;
import com.khryniewicki.projectX.game.attack.spells.spellbook.fire.Fire;
import com.khryniewicki.projectX.game.attack.spells.spellbook.fire.UltimateFire;
import com.khryniewicki.projectX.game.attack.spells.spellbook.ice.IceBerg;
import com.khryniewicki.projectX.game.attack.spells.spellbook.ice.IceBolt;
import com.khryniewicki.projectX.game.attack.spells.spellbook.monk.BlueOrb;
import com.khryniewicki.projectX.game.attack.spells.spellbook.monk.RedOrb;
import com.khryniewicki.projectX.game.attack.spells.spellbook.thunder.Thunder;
import com.khryniewicki.projectX.game.attack.spells.spellbook.thunder.VioletMissle;
import com.khryniewicki.projectX.game.attack.spells.spellbook.witcher.Storm;
import com.khryniewicki.projectX.game.attack.spells.spellbook.witcher.Wind;

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
    }

    public void fill() {
        Spell[] spellist = new Spell[]{
                new Fire(), new UltimateFire(),
                new IceBolt(), new IceBerg(),
                new VioletMissle(),new Thunder(),
                new Storm(),new Wind(),
                new BlueOrb(), new RedOrb(),
                new BlackFire(),new Skull()
        };
        spellbook = Arrays.asList(spellist);
    }

    public static SpellRegistry getInstance() {
        return SpellRegistry.HELPER.INSTANCE;
    }

    private static class HELPER {
        private final static SpellRegistry INSTANCE = new SpellRegistry();
    }
}
