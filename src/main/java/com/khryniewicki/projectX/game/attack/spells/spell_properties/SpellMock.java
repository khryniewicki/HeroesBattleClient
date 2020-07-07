package com.khryniewicki.projectX.game.attack.spells.spell_properties;

import com.khryniewicki.projectX.game.attack.attackSuccess.ActivatedAttack;
import com.khryniewicki.projectX.game.board.Board;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.math.Vector;
import com.khryniewicki.projectX.services.SpellReceiveService;

public class SpellMock extends Spell implements UltraSpell {
    private final UltraSpell ultraSpell;
    private final ActivatedAttack activatedAttack;
    private final AttackTrajectory attackTrajectory;
    private Float tmpPositionX, tmpPositionY;

    public SpellMock(UltraSpell ultraSpell) {
        this.ultraSpell = ultraSpell;
        setPosition();
        activatedAttack = new ActivatedAttack(this);
        attackTrajectory = new AttackTrajectory(this);
    }


    private Boolean changeMockPosition() {

        if (tmpPositionX != null && tmpPositionX == SpellReceiveService.spellMockPositionX) {
            if (tmpPositionY != null && tmpPositionY == SpellReceiveService.spellMockPositionY) {
                return false;
            }
        }
        if (SpellReceiveService.spellMockPositionX != null) {
            getPositionFromSpellReceiveService();
            setImage(-Math.signum(getDistanceY()), -Math.signum(getDistanceX()), getThrowingSpellTexture());
            setPosition(new Vector(getHeroPositionX(), getHeroPositionY(), 1f));
        }

        return true;
    }

    private void getPositionFromSpellReceiveService() {
        tmpPositionX = SpellReceiveService.spellMockPositionX;
        tmpPositionY = SpellReceiveService.spellMockPositionY;
        this.setFinalX(tmpPositionX);
        this.setFinalY(tmpPositionY);
        setDistanceX(tmpPositionX - getHeroPositionX());
        setDistanceY(tmpPositionY - getHeroPositionY());
    }

    @Override
    public void update() {
        changeMockPosition();
        spellCasting();
    }

    @Override
    public void spellCasting() {
        attackTrajectory.spellCasting();
    }

    @Override
    public void render() {
        ultraSpell.render();
        activatedAttack.hitsHeroWithSpell();
    }


    @Override
    public Vector getPosition() {
        return ultraSpell.getPosition();
    }

    @Override
    public Float getHeroPositionX() {
        return Board.heroMock.getPosition().x;
    }

    public Float getDistanceX() {
        return ultraSpell.getDistanceX();
    }

    public Float getDistanceY() {
        return ultraSpell.getDistanceY();
    }

    public Texture getThrowingSpellTexture() {
        return ultraSpell.getThrowingSpellTexture();
    }

    @Override
    public Integer getManaConsumed() {
        return ultraSpell.getManaConsumed();
    }

    @Override
    public Integer getPowerAttack() {
        return ultraSpell.getPowerAttack();
    }

    @Override
    public Float getHeroPositionY() {
        return Board.heroMock.getPosition().y;
    }

    @Override
    public void setDistanceX(Float distanceX) {
        ultraSpell.setDistanceX(distanceX);
    }

    @Override
    public void setDistanceY(Float distanceY) {
        ultraSpell.setDistanceY(distanceY);
    }

    @Override
    public void setFinalX(Float finalX) {
        ultraSpell.setFinalX(finalX);
    }

    @Override
    public void setFinalY(Float finalY) {
        ultraSpell.setFinalY(finalY);
    }

    @Override
    public void setImage(Float indexHeight, Float indexWidth, Texture texture) {
        ultraSpell.setImage(indexHeight, indexWidth, texture);
    }

    @Override
    public void setPosition(Vector position) {
        ultraSpell.setPosition(position);
    }
}
