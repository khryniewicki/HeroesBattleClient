package com.khryniewicki.projectX.game.user_interface.menu.animation;

import com.khryniewicki.projectX.game.attack.attackActivation.AttackTrajectory;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.SpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class AnimationSpell extends Spell {
    private SuperHero superHero;
    private MenuSymbol animation_hero;
    private SpellInstance spell;

    public AnimationSpell(SuperHero superHero, MenuSymbol animation_hero) {
        this.superHero = superHero;
        this.animation_hero = animation_hero;
        createHero();
        setSpellInstance(superHero.getBasicSpellInstance());
        setAttackTrajectory(new AttackTrajectory(this, hero));
        createProperties();
        setMesh();
    }

    public void newSpell(int number) {
        toss_spell_instance(number);
        setSpellInstance(spell);
        attackTrajectory.setSpellInstance(spell);
        createProperties();
        setMesh();
    }

    protected void toss_spell_instance(int number) {
        spell = number % 2 == 0 ? superHero.getBasicSpellInstance() : superHero.getUltimateSpellInstance();
    }

    @Override
    public void createHero() {
        if (hero == null) {
            setHero(superHero);
        }
    }


    @Override
    public Float getHeroPositionX() {
        return animation_hero.getPosition().x;
    }

    @Override
    public Float getHeroPositionY() {
        return animation_hero.getPositionY() + 0.1f;
    }


    @Override
    public void setPosition() {
        setPositionX(animation_hero.getPosition().x);
        setPositionY(animation_hero.getPositionY());
        setPositionZ(-0.1f);
    }
}
