package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.game.control_settings.mouse_settings.MousePosition;
import com.khryniewicki.projectX.game.engine.Game;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import com.khryniewicki.projectX.game.user_interface.symbols.Symbol;
import com.khryniewicki.projectX.graphics.Texture;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.khryniewicki.projectX.game.engine.Game.window;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

@Data
@Slf4j

public class MenuImp implements PropertyChangeListener, Menu {
    protected List<Symbol> animationSymbols = new ArrayList<>();
    protected List<MenuSymbol> buttons = new ArrayList<>();
    protected List<MenuSymbol> volatileImages = new ArrayList<>();
    protected List<MenuSymbol> permanentImages = new ArrayList<>();
    private final MousePosition mousePosition;

    public MenuImp() {
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
        permanentImages.forEach(MenuSymbol::render);
        animationSymbols.forEach(Symbol::render);
        volatileImages.stream()
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
                mousePosition.getCursorPosition();
                buttons.stream()
                        .filter(btn -> mousePosition.getWindowPositionX() > btn.getPositionX0() && mousePosition.getWindowPositionX() < btn.getPositionX1())
                        .filter(btn -> mousePosition.getWindowPositionY() > btn.getPositionY0() && mousePosition.getWindowPositionY() < btn.getPositionY1())
                        .findFirst()
                        .ifPresent(btn -> btn.setNews(btn.getName()));
            }
        });
    }

    @Override
    public void start() {
        init();
        subscribe();
    }

    public void toggleImage(MenuSymbol symbol, boolean state) {
        List<MenuSymbol> menuSymbols = volatileImages
                .stream()
                .peek(menuSymbol -> {
                    if (menuSymbol.equals(symbol)) {
                        menuSymbol.setDisabled(state);
                    }
                })
                .collect(Collectors.toList());
        setVolatileImages(menuSymbols);
        render();
    }

    public void updateImage(MenuSymbol symbol, Texture texture) {
        List<MenuSymbol> menuSymbols = volatileImages
                .stream()
                .peek(menuSymbol -> {
                    if (menuSymbol.equals(symbol)) {
                        symbol.setTexture(texture);
                    }
                })
                .collect(Collectors.toList());
        setVolatileImages(menuSymbols);
        render();
    }
}
