package com.khryniewicki.projectX.game.menu;

import com.khryniewicki.projectX.Game;
import com.khryniewicki.projectX.config.Application;
import com.khryniewicki.projectX.config.Message;
import com.khryniewicki.projectX.game.heroes.Factory.WizardFactory;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.heroes.character.positions.HeroStartingPosition;
import com.khryniewicki.projectX.game.menu.renderer.RenderFactory;
import com.khryniewicki.projectX.utils.TextUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.lwjgl.glfw.GLFW.*;

@Data
@Slf4j
public class MultiplayerInitializer {
    private Message message;
    private static Application.MyStompSessionHandler handler ;
    private boolean isHeroEstablishedCorrectly;
    private RenderFactory renderFactory;
    private HeroStartingPosition heroStartingPosition;
    private static String inputText;


    public MultiplayerInitializer() {
        renderFactory = RenderFactory.getRenderFactory();
        handler= new Application.MyStompSessionHandler();
    }

    public void setMessage(Message message) {
        this.message = message;

    }

    public void validateMessage() {
        heroStartingPosition = HeroStartingPosition.getInstance();

        if (message.getContent().equals("1") || message.getContent().equals("2")) {
            int appDTO = Integer.parseInt(message.getContent());
            handler.setApp(appDTO);

            if (appDTO == 1) {
                handler.setTopic(2);
                heroStartingPosition.setX(4f);
                heroStartingPosition.setY(4f);
            } else {
                handler.setTopic(1);
                heroStartingPosition.setX(-3f);
                heroStartingPosition.setY(-3f);
            }
            Game.isHeroEstablishedCorrectly =true;
            Game.latch.countDown();

            handler.sendHeroToStompSocket();

        } else {
            Game.latch.countDown();
            Game.isHeroEstablishedCorrectly =false;
        }

    }
    private void checkedInput() {

        getInput();

        if (inputText != null) {
            Set<String> characters = new HashSet<>(Arrays.asList("FireWizard", "IceWizard", "ThunderWizard"));
            boolean contains = characters.contains(inputText);

            if (!contains) {
                inputText = null;
                renderFactory.render(TextUtil.ERROR);

            }
        }
    }

    private void getInput() {
        glfwPollEvents();
        glfwSetKeyCallback(Game.window, (window, key, scancode, action, mods) -> {

            if (key == GLFW_KEY_1 && action == GLFW_PRESS) {
                inputText = "FireWizard";
                renderFactory.render(TextUtil.CHOSE_FIREWIZARD);

            } else if (key == GLFW_KEY_2 && action == GLFW_PRESS) {
                inputText = "IceWizard";
                renderFactory.render(TextUtil.CHOSE_ICEWIZARD);

            } else if (key == GLFW_KEY_3 && action == GLFW_PRESS) {
                inputText = "ThunderWizard";
                renderFactory.render(TextUtil.CHOSE_THUNDERWIZARD);

            } else
                inputText = "else";

        });


    }

    public static SuperHero getWizardType() {
        return new WizardFactory().createWizard(inputText);
    }


    public void getHeroFromPlayer() {
        renderFactory.render(TextUtil.WELCOME);
        boolean running = false;
        do {
            renderFactory.render(TextUtil.ASK_FOR_CHAR);
            checkedInput();
            if (inputText != null) {
                running = true;
            }
        } while (!running);
    }


}
