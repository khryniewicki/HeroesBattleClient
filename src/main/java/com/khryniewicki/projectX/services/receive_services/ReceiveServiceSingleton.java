package com.khryniewicki.projectX.services.receive_services;

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
    public Position get_hero_mock_position() {
        return heroReceiveService.getMockPosition();
    }

    public Integer get_hero_mock_life() {
        return heroReceiveService.getMockLife();
    }

    public Float get_hero_mock_mana() {
        return heroReceiveService.getMockMana();
    }

    public void set_hero_mock(HeroesDto heroesDTO) {
        heroReceiveService.set_hero_mock(heroesDTO);
    }

    //spell receive service
    public Position get_spell_mock_target(boolean isBasic) {
        return isBasic ? get_basic_spell_mock_target() : get_ultimate_spell_mock_target();
    }

    public Position get_basic_spell_mock_target() {
        return spellReceiveService.getBasicSpellTarget();
    }

    public Position get_ultimate_spell_mock_target() {
        return spellReceiveService.getUltimateSpellTarget();
    }

    public void set_spell_mock(SpellDto spell_dto) {
        spellReceiveService.set_spell_mock(spell_dto);
    }


    public static ReceiveServiceSingleton getInstance() {
        return HELPER.INSTANCE;
    }

    private static class HELPER {
        private final static ReceiveServiceSingleton INSTANCE = new ReceiveServiceSingleton();
    }
}
