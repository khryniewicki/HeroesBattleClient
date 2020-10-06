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
    private int counter;

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
        createMockPosition(heroDTO);
        MockLife = heroDTO.getLife();
        MockMana = heroDTO.getMana();
    }

    private void createMockPosition(HeroDTO heroDTO) {
        Position tmp = new Position(heroDTO.getPositionX(), heroDTO.getPositionY());

        if (MockPosition==null){
            MockPosition = tmp;
        }
        if (tmp.equals(MockPosition)) {
            counter++;
        } else {
            counter = 0;
        }

        if (counter < 3) {
            MockPosition=tmp;
        }
    }

    private static class HELPER {
        private final static HeroReceiveService INSTANCE = new HeroReceiveService();
    }

}
