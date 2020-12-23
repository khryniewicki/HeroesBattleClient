package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.game.engine.Game;
import com.khryniewicki.projectX.game.engine.GameState;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;

import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.ButtonsFactory.MAIN_MENU;
import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.ButtonsFactory.QUIT;
@Slf4j
public class RestartMenu extends MenuImp {
    private static final RestartMenu instance = new RestartMenu();

    public static RestartMenu getInstance() {
        return instance;
    }

    private RestartMenu() {
        super();
        start();
        addEventClick();
    }

    @Override
    public void init() {
        super.setButtons(new ArrayList<>(Arrays.asList(QUIT, MAIN_MENU)));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        String btnName = (String) evt.getNewValue();
        if (btnName.equals("main_menu")) {
            Game.getInstance().restart();
        } else {
            Game.getInstance().stop();
        }
    }

    public void win_or_loose(){
        HeroesInstances heroesInstances=HeroesInstances.getInstance();
        Integer herolife = heroesInstances.getHero().getLife();
        if (herolife==0){
            log.info("YOU LOST");
        }else {
            log.info("YOU WIN");
        }
    }
}
