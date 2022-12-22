package com.khryniewicki.projectX.game.ui.menu.menus;

import com.khryniewicki.projectX.game.control.settings.mouse.MousePosition;
import com.khryniewicki.projectX.game.ui.menu.buttons.Button;
import com.khryniewicki.projectX.game.ui.subjects.SubjectMultiplayerState;
import com.khryniewicki.projectX.game.ui.symbols.Symbol;
import com.khryniewicki.projectX.graphics.Texture;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.lwjgl.glfw.GLFW.*;

@Getter
@Slf4j
@Setter
public abstract class AbstractMenu implements Menu {
    protected volatile boolean running;
    protected List<Symbol> animationSymbols = new ArrayList<>();
    protected List<Symbol> volatileImages = new ArrayList<>();
    protected List<Symbol> permanentImages = new ArrayList<>();
    protected List<Button> buttons = new ArrayList<>();

    private final MousePosition mousePosition;
    protected final CollectionManager manager;

    protected SubjectMultiplayerState subjectMultiplayerState;
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

        Stream.of(buttons, permanentImages).flatMap(Collection::stream)
                .forEach(Symbol::render);
        Stream.of(animationSymbols, volatileImages).flatMap(Collection::stream)
                .filter(symbol -> !symbol.isDisabled())
                .forEach(Symbol::render);
        swapBuffers();
    }

    @Override
    public void subscribe() {
        buttons.forEach(button -> button.addPropertyChangeListener(this));
    }

    @Override
    public void addEventClick() {
        glfwSetMouseButtonCallback(getWindow(), (window, key, action, mods) -> {

            if (key == 0 && action != GLFW_RELEASE) {
                mousePosition.getCursorPosition();
                buttons.stream()
                        .filter(b -> mousePosition.getWindowPositionX() > b.getPositionX0() && mousePosition.getWindowPositionX() < b.getPositionX1())
                        .filter(b -> mousePosition.getWindowPositionY() > b.getPositionY0() && mousePosition.getWindowPositionY() < b.getPositionY1())
                        .findFirst()
                        .ifPresent(b -> b.setNews(b.getButtonName()));
            }
        });

        glfwSetKeyCallback(getWindow(), (window, key, scancode, action, mods) -> {
        });
    }

    @Override
    public void start() {
        init();
        subscribe();
    }

    protected void update_volatile(Symbol symbol, Texture texture) {
        this.volatileImages = manager.update_texture(volatileImages, symbol, texture);
        render();
    }

    protected void update_volatile(Symbol symbol, boolean disabled) {
        this.volatileImages = manager.toggle_image(volatileImages, symbol, disabled);
        render();
    }

    protected void update_button(Button button, Texture tex) {
        button.setTexture(tex);
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

    @Override
    public void execute() {

    }

    public enum MenuCard {
        CONTROL_SETTINGS, CHARACTER_MENU, MAIN_MENU
    }
}
