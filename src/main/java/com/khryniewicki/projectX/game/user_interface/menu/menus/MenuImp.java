package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.Game;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.game.control_settings.mouse_settings.MousePosition;
import com.khryniewicki.projectX.game.user_interface.menu.buttons.ButtonTransferObject;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.khryniewicki.projectX.Game.window;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

@Data
@Slf4j

public class MenuImp implements PropertyChangeListener, Menu {
    private boolean running;
    private ButtonTransferObject buttonTransferObject;
    private List<MenuSymbol> buttons = new ArrayList<>();
    private List<MenuSymbol> messages=new ArrayList<>();
    private MousePosition mousePosition;
    private String className;
    private Menu menu;

    public MenuImp() {
        this.className = this.getClass().getName();
        mousePosition = new MousePosition();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }

    @Override
    public void render() {
        addEventClick();
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        buttons.forEach(MenuSymbol::render);
        messages.stream()
                .filter(menuSymbol -> !menuSymbol.isDisabled())
                .collect(Collectors.toList())
                .forEach(MenuSymbol::render);
        swapBuffers();
    }

    @Override
    public void subscribe() {
        buttons.forEach(button -> button.addPropertyChangeListener(this));
    }

    @Override
    public void swapBuffers() {
        int error = glGetError();
        if (error != GL_NO_ERROR)
            System.out.println(error);
        glfwSwapBuffers(window);
    }

    @Override
    public void addEventClick() {
        glfwSetMouseButtonCallback(Game.window, (window, key, action, mods) -> {

            if (key == 0 && action != GLFW_RELEASE) {
                Position cursorPosition = mousePosition.getCursorPosition();
                buttons.stream()
                        .filter(btn -> btn.getClassName().equals(className))
                        .filter(btn -> mousePosition.getWindowPositionX() > btn.getPositionX0() && mousePosition.getWindowPositionX() < btn.getPositionX1())
                        .filter(btn -> mousePosition.getWindowPositionY() > btn.getPositionY0() && mousePosition.getWindowPositionY() < btn.getPositionY1())
                        .findFirst()
                        .ifPresent(btn -> {
                            btn.setNews(new ButtonTransferObject(btn.getName(), cursorPosition));
                        });
            }
        });
    }

    @Override
    public void start() {
        init();
        subscribe();
    }
}
