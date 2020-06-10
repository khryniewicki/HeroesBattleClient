package com.khryniewicki.projectX.services;

import com.khryniewicki.projectX.game.heroes.character.HeroDTO;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.MockStartingPosition;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Data
@Slf4j
public class HeroReceiveService {

    private MockStartingPosition mockStartingPosition;
    private Float heroMockPositionX;
    private Float heroMockPositionY;

    private HeroReceiveService() {
        mockStartingPosition = MockStartingPosition.getInstance();
    }

    public static HeroReceiveService getInstance() {
        return HELPER.INSTANCE;
    }

    public Float getHeroMockPositionX() {
        return heroMockPositionX!=null? heroMockPositionX : mockStartingPosition.getX();
    }

    public Float getHeroMockPositionY() {
        return heroMockPositionY!=null? heroMockPositionY : mockStartingPosition.getY();
    }

    public void receivedMockPosition(HeroDTO heroDTO) {
        heroMockPositionX = (heroDTO.getPositionX());
        heroMockPositionY = (heroDTO.getPositionY());
//        log.info("MOCK"+heroMockPositionX+","+heroMockPositionY);
    }

    private static class HELPER {
        private final static HeroReceiveService INSTANCE = new HeroReceiveService();
    }

}
