package com.khryniewicki.projectX.game.ui.menu.animation;

import com.khryniewicki.projectX.game.attack.attackActivation.AttackTrajectory;
import com.khryniewicki.projectX.game.attack.spell.instance.SpellInstance;
import com.khryniewicki.projectX.game.attack.spell.settings.Spell;
import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class AnimationSpell extends Spell {
    private SuperHero superHero;
    private AnimationHero animationHero;

    public AnimationSpell(SuperHero superHero, AnimationHero animationHero) {
        this.superHero = superHero;
        this.animationHero = animationHero;
        createHero();
        setSpellInstance(superHero.getBasicSpellInstance());
        setAttackTrajectory(new AttackTrajectory(this, hero));
        createProperties();
        setMesh();
    }

    public void newSpell(int number) {
        setSpellInstance(toss_spell_instance(number));
        attackTrajectory.setSpellInstance(spellInstance);
        createProperties();
        setMesh();
    }

    protected SpellInstance toss_spell_instance(int number) {
        return spellInstance = number % 2 == 0 ? superHero.getBasicSpellInstance() : superHero.getUltimateSpellInstance();
    }

    @Override
    public void createHero() {
        if (hero == null) {
            setHero(superHero);
        }
    }

    @Override
    public Float getHeroPositionX() {
        return animationHero.getPositionX();
    }

    @Override
    public Float getHeroPositionY() {
        return animationHero.getPositionY();
    }

    @Override
    public void setPosition() {
        setPositionX(getHeroPositionX());
        setPositionY(getHeroPositionY());
        setPositionZ(-0.1f);
    }

    public void disappear(){
        setPositionZ(-1f);
    }
}
