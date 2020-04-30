package com.khryniewicki.projectX.controllers;


import com.khryniewicki.projectX.services.HeroService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
public class RestController {
    private final HeroService heroService;


    @GetMapping("/checkCoordinate")
    public String checkCoodinate() {
        String position = heroService.getHeroPositions();
        return position;
        }

    @GetMapping("/getCoordinate")
    public String getCoodinate() {
        String position = heroService.getHeroPositions();
        return position;
    }

}
