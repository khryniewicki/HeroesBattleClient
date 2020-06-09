package com.khryniewicki.projectX.game.heroes.character;

import com.khryniewicki.projectX.game.attack.spells.spell_properties.Spell;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.services.HeroReceiveService;

public class HeroMock implements UltraHero {
    private final UltraHero ultraHero;
    private Float tmpPositionX;
    private Float tmpPositionY;


    public HeroMock(UltraHero insertedHero) {
        this.ultraHero = insertedHero;
    }

    private Boolean changeMockPosition() {
        if (tmpPositionX != null && tmpPositionX == HeroReceiveService.heroMockPositionX) {
            if (tmpPositionY != null && tmpPositionY == HeroReceiveService.heroMockPositionY) {
                return false;
            }
        }
        changeMockSide();
        tmpPositionX = HeroReceiveService.heroMockPositionX;
        tmpPositionY = HeroReceiveService.heroMockPositionY;
        setPositionX(HeroReceiveService.heroMockPositionX);
        setPositionY(HeroReceiveService.heroMockPositionY);
        setMesh();

        return true;
    }

    private void changeMockSide() {
        if (tmpPositionX != null) {
            if (Math.signum(tmpPositionX - HeroReceiveService.heroMockPositionX) == 1) setMovingLeft(true);
            else if (Math.signum(tmpPositionX - HeroReceiveService.heroMockPositionX) == -1) setMovingLeft(false);
            setTextureRun();
        }
    }

    @Override
    public void update() {
        changeMockPosition();
    }

    @Override
    public void render() {
        ultraHero.render();
    }

    @Override
    public void setTexture() {
        ultraHero.setTexture();
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
    public Vector getPosition() {
        return ultraHero.getPosition();
    }

    @Override
    public Spell getSpell() {
       return ultraHero.getSpell();
    }

    @Override
    public void setMesh() {
        ultraHero.setMesh();
    }

    @Override
    public void setMovingLeft(boolean movingLeft) {
        ultraHero.setMovingLeft(movingLeft);
    }

    @Override
    public void setTextureRun() {
        ultraHero.setTextureRun();
    }


}
