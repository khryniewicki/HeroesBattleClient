package com.khryniewicki.projectX.game.heroes.character;

import com.khryniewicki.projectX.game.attack.spells.spell_instances.BasicSpell;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.SpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.UltimateSpell;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.UltraSpell;
import com.khryniewicki.projectX.game.heroes.character.properties.LifeBar;
import com.khryniewicki.projectX.game.heroes.character.properties.ManaBar;
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
        this.ultraHero = superHero;
        heroReceiveService = HeroReceiveService.getInstance();
    }


    private void mockMove() {

        if (tmp != null && tmp.equals(heroReceiveService.getMockPosition())) {
            if (now != null && System.currentTimeMillis() - now > 300) {
                isIdle = true;
                now = null;
            }

            if (isIdle) {
                setHeroIdle();
                setMesh();
                isIdle = false;
                log.info("IDLE");
            }
            return;
        }
        changeMockSide();

        setHeroRun();
        setMesh();
        changePosition();
        log.info("RUN");

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
        setMovingLeft(isMovingLeft);

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
