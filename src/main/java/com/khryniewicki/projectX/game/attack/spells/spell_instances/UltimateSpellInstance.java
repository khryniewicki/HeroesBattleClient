package com.khryniewicki.projectX.game.attack.spells.spell_instances;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.graphics.Texture;
import lombok.Data;

@Data
public class UltimateSpellInstance implements SpellInstance{

    private Integer powerAttack;
    private Integer manaConsumed;
    private Float castingSpeed;
    private Texture consumedSpellTexture;
    private Texture throwingSpellTexture;
    private Texture icon;
    private Texture fadedIcon;

    private Spell spell;
    private Long spellDuration;
    private String name;
    private boolean isBasic;


    public UltimateSpellInstance(Spell spell) {
        this.spell = spell;
        this.name=spell.getName();
        this.powerAttack = spell.getPowerAttack();
        this.manaConsumed = spell.getManaConsumed();
        this.castingSpeed = spell.getCastingSpeed();
        this.icon=spell.getIcon();
        this.throwingSpellTexture = spell.getThrowingSpellTexture();
        this.consumedSpellTexture = spell.getConsumedSpellTexture();
        this.fadedIcon=spell.getFadedIcon();
        this.spellDuration = spell.getSpellDuration();
        this.isBasic=spell.isBasic();

    }
}
