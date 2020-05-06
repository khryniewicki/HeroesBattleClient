package com.khryniewicki.projectX.services;

import com.khryniewicki.projectX.game.heroes.character.HeroDTO;
import com.khryniewicki.projectX.utils.GameUtill;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Data
public class HeroReceiveService {
    public static Float heroMockPositionX;
    public static Float heroMockPositionY;
    private static Float tmpPositionX;
    private static Float tmpPositionY;

    public HeroReceiveService() {
        heroMockPositionX=GameUtill.mockStartingPositionX;
        heroMockPositionY=GameUtill.mockStartingPositionY;
    }


    public void receivedMockPosition(HeroDTO heroDTO) {
        heroMockPositionX=(heroDTO.getPositionX());
        heroMockPositionY=(heroDTO.getPositionY());
    }


}
