package com.khryniewicki.projectX;

import com.khryniewicki.projectX.game.engine.Game;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectXApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectXApplication.class, args);
        Game game=Game.getInstance();
        game.start();
    }
}
