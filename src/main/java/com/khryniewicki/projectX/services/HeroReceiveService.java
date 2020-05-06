package com.khryniewicki.projectX.services;

import com.khryniewicki.projectX.game.heroes.character.HeroDTO;
import com.khryniewicki.projectX.utils.GameUtill;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Data
public class HeroReceiveService {
    public static Float heroMockPositionX = GameUtill.mockStartingPositionX;
    public static Float heroMockPositionY = GameUtill.mockStartingPositionY;
    private static HeroDTO heroMock = new HeroDTO(heroMockPositionX, heroMockPositionY);
    private static Float tmpPositionX;
    private static Float tmpPositionY;


    private static Boolean checkIfCoordinateChanged() {
        if (tmpPositionX != null && tmpPositionX == (heroMockPositionX)) {
            if (tmpPositionY != null && tmpPositionY == (heroMockPositionY)) {
                return false;
            }
        }
        tmpPositionX = heroMockPositionX;
        tmpPositionY = heroMockPositionY;
        heroMock = new HeroDTO(tmpPositionX, tmpPositionY);
        return true;
    }



    public static void setHeroMock(HeroDTO heroMock) {
        HeroReceiveService.heroMock = heroMock;
    }


    public static HeroDTO getHeroMockPositions() {
        Boolean isCoordinatesChanged = checkIfCoordinateChanged();
        if (isCoordinatesChanged) {
            return new HeroDTO(heroMockPositionX, heroMockPositionY);        }
        return heroMock;
    }
    public  void receivedMockPosition(HeroDTO heroDTO) {
        System.out.println(heroDTO.toString());
        checkIfCoordinateChanged();
        setHeroMock(heroDTO);
    }
}
