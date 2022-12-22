package com.khryniewicki.projectX.game.attack.spell.instance;

import com.khryniewicki.projectX.game.attack.spell.settings.Spell;
import com.khryniewicki.projectX.game.attack.spell.settings.SpellTexture;
import com.khryniewicki.projectX.graphics.Texture;
import lombok.Data;

@Data

public class BasicSpellInstance implements SpellInstance {

    private String name;
    private Integer powerAttack;
    private Integer manaConsumed;
    private Float castingSpeed;
    private SpellTexture consumedSpellTexture;
    private SpellTexture throwingSpellTexture;
    private Texture icon;
    private Texture fadedIcon;
    private Long spellDuration;

    private boolean isBasic;
    private Spell spell;


    public BasicSpellInstance(Spell spell) {
        this.spell = spell;
        this.name=spell.getName();
        this.powerAttack = spell.getPowerAttack();
        this.manaConsumed = spell.getManaConsumed();
        this.castingSpeed = spell.getCastingSpeed();
        this.throwingSpellTexture = spell.getMissleSpell();
        this.consumedSpellTexture = spell.getExecutedSpell();
        this.icon=spell.getIcon();
        this.fadedIcon=spell.getFadedIcon();
        this.spellDuration = spell.getSpellDuration();
        this.isBasic=spell.isBasic();
    }


}