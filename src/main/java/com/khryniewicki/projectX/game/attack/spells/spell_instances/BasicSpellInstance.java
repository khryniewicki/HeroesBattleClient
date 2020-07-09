package com.khryniewicki.projectX.game.attack.spells.spell_instances;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.graphics.Texture;
import lombok.Data;

@Data

public class BasicSpellInstance implements SpellInstance {

    private String name;
    private Integer powerAttack;
    private Integer manaConsumed;
    private Float castingSpeed;
    private Texture consumedSpellTexture;
    private Texture throwingSpellTexture;
    private Long spellDuration;
    private boolean isBasic;
    private Spell spell;


    public BasicSpellInstance(Spell spell) {
        this.spell = spell;
        this.name=spell.getName();
        this.powerAttack = spell.getPowerAttack();
        this.manaConsumed = spell.getManaConsumed();
        this.castingSpeed = spell.getCastingSpeed();
        this.throwingSpellTexture = spell.getThrowingSpellTexture();
        this.consumedSpellTexture = spell.getConsumedSpellTexture();
        this.spellDuration = spell.getSpellDuration();
        this.isBasic=spell.isBasic();
    }


}