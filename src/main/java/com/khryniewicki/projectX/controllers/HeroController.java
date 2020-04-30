package com.khryniewicki.projectX.controllers;

import com.khryniewicki.projectX.game.Character.HeroDTO;
import com.khryniewicki.projectX.services.HeroReceiveService;
import com.khryniewicki.projectX.services.HeroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class HeroController {


    private final HeroService heroService;
    private final HeroReceiveService heroReceiveService;

    @GetMapping("/checkCoordinate")
    public HeroDTO checkCoodinate() {
        return heroService.getHeroPositions();
    }

    @MessageMapping("/hero")
    @SendTo("/topic/hero")
    public HeroDTO getCoordinates(HeroDTO heroDTO) throws InterruptedException {
        heroReceiveService.receivedMockPosition(heroDTO);
        log.info("Received coordinates: x :{},y{}", heroDTO.getPositionX(), heroDTO.getPositionY());
        return heroDTO;
    }


}
