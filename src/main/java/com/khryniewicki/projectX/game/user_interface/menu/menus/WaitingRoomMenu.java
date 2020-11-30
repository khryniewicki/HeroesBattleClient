package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.game.multiplayer.websocket.WaitingRoomTimer;
import com.khryniewicki.projectX.game.multiplayer.websocket.WebsocketScheduler;
import com.khryniewicki.projectX.game.user_interface.menu.buttons.Button;
import com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextFactory;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import com.khryniewicki.projectX.graphics.Colors;
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
public class WaitingRoomMenu extends WaitingRoomTimer {
    private float level = 4.5f;
    private volatile boolean restart;

    private WaitingRoomMenu() {
        super();
        websocketScheduler = WebsocketScheduler.getInstance();
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
        return new Button.Builder()
                .withTexture(TextFactory.textInConnectionWindow(text))
                .withName(text)
                .withHeight(0.8f)
                .withWidth(10f)
                .withPositionX(-9.5f)
                .withPositionY(level)
                .build();
    }

    @Override
    public void execute() {
        setRestart(false);
        setJoined(false);
        do {
            if (Objects.nonNull(timeLeftToLogOut) && timeLeftToLogOut>0L) {
                update(TIMER, timeLeftToLogOut);
                render();
                if (timeLeftToLogOut ==1L && !restart) {
                    timeLeftToLogOut = null;
                    permanentImages=new ArrayList<>();
                    level = 4.5f;
                    addTimer();
                    setRestart(true);
                    log.info("Restart: {}", restart);
                }
            }
        } while (!joined && !restart);

    }

    public void update(MenuSymbol symbol, Long timeLeftToLogOut) {
        glfwPollEvents();
        List<MenuSymbol> collect = permanentImages
                .stream()
                .peek(menuSymbol -> {
                    if (menuSymbol.getName().equals(symbol.getName())) {
                        symbol.setTexture(TextFactory.textInLoadingMenuToImage("Time to logout: " + timeLeftToLogOut.toString() + " sec.", Colors.BRIGHT_GREEN));
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
