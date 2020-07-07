package com.khryniewicki.projectX.game.attack.spells.spell_instances;

import com.khryniewicki.projectX.game.attack.spells.spell_properties.Spell;
import com.khryniewicki.projectX.graphics.Texture;
import lombok.Data;

@Data
public class UltimateSpell  implements SpellInstance{

    private Integer powerAttack;
    private Integer manaConsumed;
    private Float castingSpeed;
    private Texture consumedSpellTexture;
    private Texture throwingSpellTexture;
    private Spell spell;
    private Long spellDuration;
    private String name;

    public UltimateSpell(Spell spell) {
        this.spell = spell;
        this.name=spell.getName();
        this.powerAttack = spell.getPowerAttack();
        this.manaConsumed = spell.getManaConsumed();
        this.castingSpeed = spell.getCastingSpeed();
        this.throwingSpellTexture = spell.getThrowingSpellTexture();
        this.consumedSpellTexture = spell.getConsumedSpellTexture();
        this.spellDuration = spell.getSpellDuration();
    }
}
