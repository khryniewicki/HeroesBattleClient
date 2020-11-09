package com.khryniewicki.projectX.game.heroes.character.properties;

import com.khryniewicki.projectX.game.attack.spells.spell_instances.BasicSpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.UltimateSpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.UltraSpell;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.StartingPosition;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.services.HeroReceiveService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HeroMock implements UltraHero {
    private Position tmp, finalPosition;
    private Integer tmpLife;
    private Integer tmpMana;

    private final UltraHero ultraHero;
    private final HeroReceiveService heroReceiveService;
    boolean isMovingLeft = false;
    boolean isIdle = false;
    private Long now;

    public HeroMock(SuperHero superHero) {
        super();
        this.ultraHero = superHero;
        heroReceiveService = HeroReceiveService.getInstance();
    }


    private void mockMove() {

        if (tmp != null && tmp.equals(heroReceiveService.getMockPosition())) {
            if (now != null && System.currentTimeMillis() - now > 500) {
                isIdle = true;
                now = null;
            }

            if (isIdle) {
                setHeroIdle();
                setMesh();
                isIdle = false;
            }
            return;
        }

        changeMockSide();
        setHeroRun();
        setMesh();
        changePosition();
        updateLifeBar();
        updateManaBar();
    }


    private void changePosition() {
        tmp = heroReceiveService.getMockPosition();
        now = System.currentTimeMillis();
        setPositionX(finalPosition.getX());
        setPositionY(finalPosition.getY());
    }

    private void changeMockSide() {
        if (tmp == null) {
            tmp = heroReceiveService.getMockPosition();
        }
        finalPosition = heroReceiveService.getMockPosition();
        if (Math.signum(tmp.getX() - finalPosition.getX()) == 1) isMovingLeft = true;
        else if (Math.signum(tmp.getX() - finalPosition.getX()) == -1) isMovingLeft = false;
        setTurningLeft(isMovingLeft);

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
    public UltimateSpellInstance getUltimateSpellInstance() {
        return ultraHero.getUltimateSpellInstance();
    }

    @Override
    public void setBasicSpell(UltraSpell ultraSpell) {
        ultraHero.setBasicSpell(ultraSpell);
    }

    @Override
    public UltraSpell getUltimateSpell() {
        return ultraHero.getUltimateSpell();    }

    @Override
    public void setUltimateSpell(UltraSpell ultraSpell) {
            ultraHero.setUltimateSpell(ultraSpell);
    }

    @Override
    public UltraSpell getBasicSpell() {
        return ultraHero.getBasicSpell();
    }

    @Override
    public Float getX() {
        return ultraHero.getX();
    }

    @Override
    public Float getY() {
        return ultraHero.getY();
    }


    @Override
    public StartingPosition getStartingPosition() {
        return ultraHero.getStartingPosition();
    }

    @Override
    public void setStartingPosition(StartingPosition startingPosition) {
        ultraHero.setStartingPosition(startingPosition);
    }

    @Override
    public BasicSpellInstance getBasicSpellInstance() {
        return ultraHero.getBasicSpellInstance();
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
    public void setHeroAttack() {
        ultraHero.setHeroAttack();
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
    public void setTurningLeft(boolean movingLeft) {
        ultraHero.setTurningLeft(movingLeft);
    }


}
