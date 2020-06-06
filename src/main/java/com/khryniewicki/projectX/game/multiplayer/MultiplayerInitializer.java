package com.khryniewicki.projectX.game.multiplayer;

import com.khryniewicki.projectX.Game;
import com.khryniewicki.projectX.game.heroes.Factory.WizardFactory;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.renderer.RenderFactory;
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
    private RenderFactory renderFactory;
    private static String inputText;


    public MultiplayerInitializer() {
        renderFactory = RenderFactory.getRenderFactory();
    }

    public void getHeroTypeFromPlayer() {
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

    public void waitingForSecondPlayer() {
        renderFactory.render(TextUtil.OTHER_PLAYER);

        WebsocketInitializer websocketInitializer = WebsocketInitializer.getWebsocketInstance();
        HeroesInstances heroesInstances = HeroesInstances.getInstance();
        try {
            websocketInitializer.getSecondPlayerMockType();
            heroesInstances.setMock();

            renderFactory.render(TextUtil.GET_READY);
            Thread.sleep(5000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void occupiedRoom() {
        renderFactory.render(TextUtil.TRY_LATER);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
