package com.khryniewicki.projectX.services.receive_services;

import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.MockStartingPosition;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.services.dto.HeroDto;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Data
@Slf4j
public class HeroReceiveService {
    private int counter;
    private Position MockPosition;
    private Integer MockLife;
    private Integer MockMana;
    private final MockStartingPosition mockStartingPosition;

    public HeroReceiveService() {
        this.mockStartingPosition = MockStartingPosition.getInstance();
    }

    public Position getMockPosition() {
        return MockPosition == null ? new Position(mockStartingPosition.getX(), mockStartingPosition.getY()) : MockPosition;
    }

    public void set_hero_mock(HeroDto heroDTO) {
        set_mock_position(heroDTO);
        MockLife = heroDTO.getLife();
        MockMana = heroDTO.getMana();
    }

    private void set_mock_position(HeroDto heroDTO) {
        Position tmp = new Position(heroDTO.getPositionX(), heroDTO.getPositionY());

        if (MockPosition == null) {
            MockPosition = tmp;
        }
        if (tmp.equals(MockPosition)) {
            counter++;
        } else {
            counter = 0;
        }

        if (counter < 3) {
            MockPosition = tmp;
        }
    }

    public void reset() {
        MockLife = null;
        MockMana = null;
        MockPosition=null;
    }



}
