package com.khryniewicki.projectX.services;

import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.MockStartingPosition;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.services.DTO.HeroDTO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Data
@Slf4j
public class HeroReceiveService {
    private MockStartingPosition mockStartingPosition;
    private Position MockPosition;
    private Integer MockLife;
    private Integer MockMana;


    private HeroReceiveService() {
        mockStartingPosition = MockStartingPosition.getInstance();
    }

    public static HeroReceiveService getInstance() {
        return HELPER.INSTANCE;
    }


    public Position getMockPosition() {
        return MockPosition == null ? new Position(mockStartingPosition.getX(), mockStartingPosition.getY()) : MockPosition;
    }

    public void receivedMockDTO(HeroDTO heroDTO) {
        MockPosition = new Position(heroDTO.getPositionX(), heroDTO.getPositionY());
        MockLife = heroDTO.getLife();
        MockMana = heroDTO.getMana();
    }

    private static class HELPER {
        private final static HeroReceiveService INSTANCE = new HeroReceiveService();
    }

}
