package com.khryniewicki.projectX.services;

import com.khryniewicki.projectX.services.DTO.HeroDTO;
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
    private Integer MockLife;
    private Integer MockMana;



    private HeroReceiveService() {
        mockStartingPosition = MockStartingPosition.getInstance();
    }

    public static HeroReceiveService getInstance() {
        return HELPER.INSTANCE;
    }

    public Float getMockPositionX() {
        return MockPositionX == null ? mockStartingPosition.getX() : MockPositionX;
    }

    public Float getMockPositionY() {
        return MockPositionY == null ? mockStartingPosition.getY() : MockPositionY;
    }

    public Integer getMockLife() { return MockLife; }

    public Integer getMockMana() { return MockMana; }

    public void receivedMockDTO(HeroDTO heroDTO) {
        MockPositionX = (heroDTO.getPositionX());
        MockPositionY = (heroDTO.getPositionY());
        MockLife = heroDTO.getLife();
        MockMana = heroDTO.getMana();
    }

    private static class HELPER {
        private final static HeroReceiveService INSTANCE = new HeroReceiveService();
    }

}
