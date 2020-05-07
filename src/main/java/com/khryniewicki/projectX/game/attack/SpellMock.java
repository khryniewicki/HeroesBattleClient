package com.khryniewicki.projectX.game.attack;

import com.khryniewicki.projectX.services.SpellReceiveService;

public class SpellMock implements UltraSpell {
    private UltraSpell ultraSpell;

    public SpellMock(UltraSpell ultraSpell) {
        this.ultraSpell = ultraSpell;
    }

    @Override
    public void update() {
        setPositionX(SpellReceiveService.spellMockPositionX);
        setPositionY(SpellReceiveService.spellMockPositionY);
        setPositionZ(1f);
    }


    @Override
    public void setProperties() {
        ultraSpell.setProperties();
    }

    @Override
    public void setThrowingSpellTexture() {
        ultraSpell.setThrowingSpellTexture();
    }

    @Override
    public void setConsumedSpellTexture() {
        ultraSpell.setConsumedSpellTexture();
    }

    @Override
    public void setTexture() {
        ultraSpell.setTexture();
    }

    @Override
    public void setMesh() {
        ultraSpell.setMesh();
    }



    @Override
    public void render() {
        ultraSpell.render();
    }

    @Override
    public void castingSpell() {
        ultraSpell.castingSpell();
    }

    @Override
    public void setPositionX(Float positionX) {
        ultraSpell.setPositionX(positionX);
    }

    @Override
    public void setPositionY(Float positionY) {
        ultraSpell.setPositionX(positionY);

    }

    @Override
    public void setPositionZ(Float positionZ) {
        ultraSpell.setPositionX(positionZ);

    }
}
