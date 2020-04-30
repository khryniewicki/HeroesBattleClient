package com.khryniewicki.projectX.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SendingHeroOperations {


    @GetMapping("/")
    public String sendCoordinates(Model model){
        String name="Konrad";
        return "index";
    }
}
