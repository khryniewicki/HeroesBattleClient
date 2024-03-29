package com.khryniewicki.projectX.services.receive;

import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.MockStartingPosition;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.services.dto.HeroesDto;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
@Data
@Slf4j
public class HeroReceiveService {
    private int counter;
    private Position MockPosition;
    private Integer MockLife;
    private Float MockMana;
    private final MockStartingPosition mockStartingPosition;

    public HeroReceiveService() {
        this.mockStartingPosition = MockStartingPosition.getInstance();
    }

    public Position getMockPosition() {
        return isNull(MockPosition) ? new Position(mockStartingPosition.getX(), mockStartingPosition.getY()) : MockPosition;
    }

    public void setHeroMock(HeroesDto heroesDTO) {
        setMockPosition(heroesDTO);
        MockLife = heroesDTO.getLife();
        MockMana = heroesDTO.getMana();
    }

    private void setMockPosition(HeroesDto heroesDTO) {
        Position tmp = new Position(heroesDTO.getPositionX(), heroesDTO.getPositionY());

        if (isNull(MockPosition)) {
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
    }



}
