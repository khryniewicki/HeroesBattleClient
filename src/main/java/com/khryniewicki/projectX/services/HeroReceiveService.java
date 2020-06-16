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
    private Float MockPositionX;
    private Float MockPositionY;

    private HeroReceiveService() {
        mockStartingPosition = MockStartingPosition.getInstance();
    }

    public static HeroReceiveService getInstance() {
        return HELPER.INSTANCE;
    }

    public Float getMockPositionX() {
        return MockPositionX ==null? mockStartingPosition.getX(): MockPositionX;
    }

    public Float getMockPositionY() {
        return MockPositionY ==null? mockStartingPosition.getY(): MockPositionY;
    }

    public void receivedMockPosition(HeroDTO heroDTO) {
        MockPositionX = (heroDTO.getPositionX());
        MockPositionY = (heroDTO.getPositionY());
    }

    private static class HELPER {
        private final static HeroReceiveService INSTANCE = new HeroReceiveService();
    }

}
