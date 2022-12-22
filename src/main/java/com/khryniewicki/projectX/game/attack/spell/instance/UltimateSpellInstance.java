package com.khryniewicki.projectX.game.attack.spell.instance;

import com.khryniewicki.projectX.game.attack.spell.settings.Spell;
import com.khryniewicki.projectX.game.attack.spell.settings.SpellTexture;
import com.khryniewicki.projectX.graphics.Texture;
import lombok.Data;

@Data
public class UltimateSpellInstance implements SpellInstance{

    private Integer powerAttack;
    private Integer manaConsumed;
    private Float castingSpeed;
    private SpellTexture consumedSpellTexture;
    private SpellTexture throwingSpellTexture;
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
        this.throwingSpellTexture = spell.getMissleSpell();
        this.consumedSpellTexture = spell.getExecutedSpell();
        this.fadedIcon=spell.getFadedIcon();
        this.spellDuration = spell.getSpellDuration();
        this.isBasic=spell.isBasic();
    }
}
