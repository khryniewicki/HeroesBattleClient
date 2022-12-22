package com.khryniewicki.projectX.services.receive;

import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.services.dto.HeroesDto;
import com.khryniewicki.projectX.services.dto.SpellDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReceiveServiceSingleton {
    private  HeroReceiveService heroReceiveService;
    private  SpellReceiveService spellReceiveService;

    private ReceiveServiceSingleton() {
        reset();
    }

    public void reset() {
        this.heroReceiveService = new HeroReceiveService();
        this.spellReceiveService = new SpellReceiveService();
    }

    //hero receive service
    public Position getHeroMockPosition() {
        return heroReceiveService.getMockPosition();
    }

    public Integer getHeroMockLife() {
        return heroReceiveService.getMockLife();
    }

    public Float getHeroMockMana() {
        return heroReceiveService.getMockMana();
    }

    public void setHeroMock(HeroesDto heroesDTO) {
        heroReceiveService.set_hero_mock(heroesDTO);
    }

    //spell receive service
    public Position getSpellMockTarget(boolean isBasic) {
        return isBasic ? getBasicSpellMockTarget() : getUltimateSpellMockTarget();
    }

    public Position getBasicSpellMockTarget() {
        return spellReceiveService.getBasicSpellTarget();
    }

    public Position getUltimateSpellMockTarget() {
        return spellReceiveService.getUltimateSpellTarget();
    }

    public void setSpellMock(SpellDto spell_dto) {
        spellReceiveService.set_spell_mock(spell_dto);
    }


    public static ReceiveServiceSingleton getInstance() {
        return HELPER.INSTANCE;
    }

    private static class HELPER {
        private final static ReceiveServiceSingleton INSTANCE = new ReceiveServiceSingleton();
    }
}
