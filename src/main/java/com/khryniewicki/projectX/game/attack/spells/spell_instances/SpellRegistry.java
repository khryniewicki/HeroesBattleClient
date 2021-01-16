package com.khryniewicki.projectX.game.attack.spells.spell_instances;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.game.attack.spells.spellbook.king.BlackFire;
import com.khryniewicki.projectX.game.attack.spells.spellbook.king.SkullCurse;
import com.khryniewicki.projectX.game.attack.spells.spellbook.fire.Fire;
import com.khryniewicki.projectX.game.attack.spells.spellbook.fire.FireBomb;
import com.khryniewicki.projectX.game.attack.spells.spellbook.ice.IceBerg;
import com.khryniewicki.projectX.game.attack.spells.spellbook.ice.IceBolt;
import com.khryniewicki.projectX.game.attack.spells.spellbook.monk.FrostFury;
import com.khryniewicki.projectX.game.attack.spells.spellbook.monk.BloodyWhip;
import com.khryniewicki.projectX.game.attack.spells.spellbook.thunder.Lightning;
import com.khryniewicki.projectX.game.attack.spells.spellbook.thunder.Thunderbolt;
import com.khryniewicki.projectX.game.attack.spells.spellbook.witcher.ElectricShock;
import com.khryniewicki.projectX.game.attack.spells.spellbook.witcher.ElectricBomb;

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
                new Fire(), new FireBomb(),
                new IceBolt(), new IceBerg(),
                new Thunderbolt(),new Lightning(),
                new ElectricShock(),new ElectricBomb(),
                new FrostFury(), new BloodyWhip(),
                new BlackFire(),new SkullCurse()
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
