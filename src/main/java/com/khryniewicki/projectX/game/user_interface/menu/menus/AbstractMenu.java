package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.game.control_settings.mouse_settings.MousePosition;
import com.khryniewicki.projectX.game.engine.Game;
import com.khryniewicki.projectX.game.engine.GameLoopImp;
import com.khryniewicki.projectX.game.multiplayer.websocket.states.ServerState;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import com.khryniewicki.projectX.game.user_interface.symbols.Symbol;
import com.khryniewicki.projectX.game.user_interface.symbols.observers.Subject;
import com.khryniewicki.projectX.graphics.Texture;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextureMenuFactory.PLAYERS_BAR_LABEL;
import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextureMenuFactory.PLAYERS_DESCRIPTION_LABEL;
import static org.lwjgl.glfw.GLFW.*;

@Getter
@Slf4j
@Setter
public abstract class AbstractMenu extends GameLoopImp implements PropertyChangeListener, Menu {
    protected List<Symbol> animationSymbols = new ArrayList<>();
    protected List<MenuSymbol> buttons = new ArrayList<>();
    protected List<Symbol> volatileImages = new ArrayList<>();
    protected List<MenuSymbol> permanentImages = new ArrayList<>();

    private final MousePosition mousePosition;
    private final CollectionManager manager;

    protected Subject subject;
    protected static MenuCard currentView;

    public AbstractMenu() {
        mousePosition = new MousePosition();
        manager = new CollectionManager();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }

    @Override
    public void render() {
        clearBuffers();
        buttons.forEach(MenuSymbol::render);
        permanentImages.forEach(MenuSymbol::render);
        animationSymbols.stream()
                .filter(symbol -> !symbol.isDisabled())
                .collect(Collectors.toList())
                .forEach(Symbol::render);
        volatileImages.stream()
                .filter(symbol -> !symbol.isDisabled())
                .collect(Collectors.toList())
                .forEach(Symbol::render);
        swapBuffers();
    }

    @Override
    public void subscribe() {
        buttons.forEach(button -> button.addPropertyChangeListener(this));
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
        glfwSetKeyCallback(Game.window, (window, key, scancode, action, mods) -> {
        });
    }

    @Override
    public void start() {
        init();
        subscribe();
    }

    protected void update_volatiles(Symbol symbol, Texture texture) {
        this.volatileImages = manager.update_texture(volatileImages, symbol, texture);
        render();
    }

    protected void update_volatiles(Symbol symbol, boolean disabled) {
        this.volatileImages = manager.toggle_image(volatileImages, symbol, disabled);
        render();
    }

    protected List<Symbol> update_symbols(List<Symbol> symbols, Symbol symbol, Texture texture) {
        return manager.update_texture(symbols, symbol, texture);
    }

    protected void update_label(ServerState serverState) {
        this.permanentImages = manager.update_label(permanentImages, PLAYERS_BAR_LABEL, serverState);
        this.permanentImages = manager.update_label_description(permanentImages, PLAYERS_DESCRIPTION_LABEL, serverState);
        render();
    }

    protected void update_button(MenuSymbol menuSymbol, Texture tex) {
        menuSymbol.setTexture(tex);
        render();
    }

    protected void runMenu(Menu menu, MenuCard menuCard) {
        menu.addEventClick();
        menu.render();
        setCurrentView(menuCard);
    }

    protected void setCurrentView(MenuCard menuCard) {
        currentView = menuCard;
        log.info("CURRENT VIEW: {}", currentView);
    }

    public enum MenuCard {
        CONTROL_SETTINGS, CHARACTER_MENU, MAIN_MENU
    }
}
