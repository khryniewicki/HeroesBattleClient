package com.khryniewicki.projectX.game.user_interface.menu.graphic_factory;

import com.khryniewicki.projectX.game.attack.attackActivation.AttackTrajectory;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.SpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.Spell;
import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class AnimationSpell extends Spell {
    private SuperHero superHero;
    private MenuSymbol animation_hero;
    private SpellInstance spell;

    public AnimationSpell(SuperHero superHero, MenuSymbol animation_hero) {
        this.superHero = superHero;
        this.animation_hero = animation_hero;
        createHero();
        super.setSpellInstance(superHero.getBasicSpellInstance());
        super.setAttackTrajectory(new AttackTrajectory(this, super.getHero()));
        super.createProperties();
        setMesh(createMesh());
    }

    public void newSpell(int number) {
        spell = number % 2 == 0 ? superHero.getBasicSpellInstance() : superHero.getUltimateSpellInstance();
        super.setSpellInstance(spell);
        super.getAttackTrajectory().setSpellInstance(spell);
        super.createProperties();
        super.setMesh(createMesh());
    }

    @Override
    public void createHero() {
        if (super.getHero() == null) {
            super.setHero(superHero);
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
