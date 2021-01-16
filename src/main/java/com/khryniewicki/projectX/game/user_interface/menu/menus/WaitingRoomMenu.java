package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.game.engine.Application;
import com.khryniewicki.projectX.game.multiplayer.controller.MultiplayerController;
import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextFactory;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import com.khryniewicki.projectX.game.user_interface.symbols.Symbol;
import com.khryniewicki.projectX.graphics.Colors;
import com.khryniewicki.projectX.graphics.textures.MenuTextures;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextureMenuFactory.TIMER;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;

@Setter
@Getter
@Slf4j
public class WaitingRoomMenu extends AbstractMenu {
    private float level = 4.5f;
    private boolean waitingForPlayer;
    protected Long timeLeftToLogOut;

    private WaitingRoomMenu() {
        super();
    }


    public void addTimer() {
        permanentImages.add(TIMER);
    }

    public void addText(String text) {
        permanentImages.add(createText(text));
        level -= 0.7f;
        render();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public MenuSymbol createText(String text) {
        return new MenuSymbol.Builder()
                .withTexture(TextFactory.textInConnectionWindow(text))
                .withName(text)
                .withHeight(0.8f)
                .withWidth(10f)
                .withPositionX(-9.5f)
                .withPositionY(level)
                .build();
    }

    @Override
    public void update() {
        if (Objects.nonNull(timeLeftToLogOut)) {
            if (timeLeftToLogOut > 0L) {
                changeTime(TIMER, timeLeftToLogOut);
                setWaitingForPlayer(true);
            } else {
                if (waitingForPlayer) {
                    restart();
                    Application.play();
                    MultiplayerController.getInstance().stop();
                }
            }
        }
    }

    @Override
    public void restart() {
        setWaitingForPlayer(false);
        TIMER.setTexture(MenuTextures.BLANK_TEXT_WINDOW);
        level = 4.5f;
        permanentImages = new ArrayList<>();
        timeLeftToLogOut = null;
    }


    @Override
    public void execute() {

    }

    public void changeTime(MenuSymbol symbol, Long timeLeftToLogOut) {
        glfwPollEvents();
        List<Symbol> collect = permanentImages.stream()
                .peek(menuSymbol -> {
                    if (menuSymbol.getName().equals(symbol.getName())) {
                        String text = "Logout in: " + timeLeftToLogOut.toString() + " sec.";
                        symbol.setTexture(TextFactory.textInLoadingMenuToImage(text, Colors.BRIGHT_GREEN, 32));
                    }
                })
                .collect(Collectors.toList());
        setPermanentImages(collect);
    }

    public static WaitingRoomMenu getWaitingRoomMenu() {
        return HELPER.WAITING_ROOM_MENU;
    }


    private static class HELPER {
        private final static WaitingRoomMenu WAITING_ROOM_MENU = new WaitingRoomMenu();
    }
}
