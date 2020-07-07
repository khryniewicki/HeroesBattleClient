package com.khryniewicki.projectX.game.heroes.character;

import com.khryniewicki.projectX.game.attack.spells.spell_instances.BasicSpell;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.SpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.UltimateSpell;
import com.khryniewicki.projectX.game.attack.spells.spell_properties.Spell;
import com.khryniewicki.projectX.game.attack.spells.spell_properties.UltraSpell;
import com.khryniewicki.projectX.game.heroes.character.properties.LifeBar;
import com.khryniewicki.projectX.game.heroes.character.properties.ManaBar;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.services.HeroReceiveService;

public class HeroMock implements UltraHero {
    private Float tmpPositionX;
    private Float tmpPositionY;
    private Integer tmpLife;
    private Integer tmpMana;
    private final UltraHero ultraHero;
    private HeroReceiveService heroReceiveService;
    boolean isMovingLeft = false;

    public HeroMock(SuperHero superHero) {
        this.ultraHero = superHero;
        heroReceiveService = HeroReceiveService.getInstance();

    }


    private void mockMove() {

        if (tmpPositionX != null && tmpPositionX == heroReceiveService.getMockPositionX()) {
            if (tmpPositionY != null && tmpPositionY == heroReceiveService.getMockPositionY()) {
                setHeroIdle();
                setMesh();
                return;
            }
        }
        changeMockSide();
        changePosition();
        setHeroRun();
        setMesh();
        updateLifeBar();
        updateManaBar();
    }

    private void updateLifeBar() {
        LifeBar lifeBar = getLifeBar();
        lifeBar.updateLifeBar();
    }

    @Override
    public void updateManaBar() {
        ManaBar manaBar = getManaBar();
        manaBar.updateManaBar();
    }

    private void changePosition() {
        tmpPositionX = heroReceiveService.getMockPositionX();
        tmpPositionY = heroReceiveService.getMockPositionY();

        setPositionX(heroReceiveService.getMockPositionX());
        setPositionY(heroReceiveService.getMockPositionY());
    }

    private void changeMockSide() {
        if (tmpPositionX != null) {

            if (Math.signum(tmpPositionX - heroReceiveService.getMockPositionX()) == 1) isMovingLeft = true;
            else if (Math.signum(tmpPositionX - heroReceiveService.getMockPositionX()) == -1) isMovingLeft = false;
            setMovingLeft(isMovingLeft);
        }
    }

    @Override
    public void update() {
        mockMove();
        checkLifeAndMana();
    }

    private void checkLifeAndMana() {
        checkLife();
        checkMana();
    }

    private void checkLife() {
        Integer life = getLife();
        if (life != null && tmpLife != null) {
            if (!tmpLife.equals(life)) {
                updateLifeBar();
                tmpLife = life;
            }

        } else
            tmpLife = life;
    }

    private void checkMana() {
        Integer mana = getMana();
        if (mana != null && tmpMana != null) {
            if (!tmpMana.equals(mana)) {
                updateManaBar();
                tmpMana = mana;
            }

        } else
            tmpMana = mana;
    }

    @Override
    public void render() {
        ultraHero.render();
    }

    @Override
    public Vector getPosition() {
        return ultraHero.getPosition();
    }

    @Override
    public SpellInstance getSpellInstance() {
        return ultraHero.getSpellInstance();
    }

    @Override
    public LifeBar getLifeBar() {
        return ultraHero.getLifeBar();
    }

    @Override
    public ManaBar getManaBar() {
        return ultraHero.getManaBar();
    }

    @Override
    public Integer getLife() {
        return heroReceiveService.getMockLife();
    }

    @Override
    public Integer getMana() {
        return heroReceiveService.getMockMana();
    }

    @Override
    public BasicSpell getBasicSpell() {
        return ultraHero.getBasicSpell();
    }

    @Override
    public UltimateSpell getUltimateSpell() {
        return ultraHero.getUltimateSpell();
    }

    @Override
    public void setUltraSpell(UltraSpell ultraSpell) {
        ultraHero.setUltraSpell(ultraSpell);
    }

    @Override
    public UltraSpell getUltraSpell() {
        return ultraHero.getUltraSpell();
    }


    @Override
    public void setPosition() {
        ultraHero.setPosition();
    }

    @Override
    public void setPositionX(Float positionX) {
        ultraHero.setPositionX(positionX);
    }

    @Override
    public void setPositionY(Float positionY) {
        ultraHero.setPositionY(positionY);
    }

    @Override
    public void setProperties() {
        ultraHero.setProperties();
    }

    @Override
    public void setTexture() {
        ultraHero.setTexture();
    }

    @Override
    public void setHeroIdle() {
        ultraHero.setHeroIdle();
    }

    @Override
    public void setHeroRun() {
        ultraHero.setHeroRun();
    }

    @Override
    public void setSpellBasis() {
        ultraHero.setSpellBasis();
    }

    @Override
    public void setLifeBar(LifeBar lifeBar) {
        ultraHero.setLifeBar(lifeBar);
    }

    @Override
    public void setManaBar(ManaBar manaBar) {
        ultraHero.setManaBar(manaBar);
    }

    @Override
    public void setMesh() {
        ultraHero.setMesh();
    }

    @Override
    public void setMovingLeft(boolean movingLeft) {
        ultraHero.setMovingLeft(movingLeft);
    }


}
