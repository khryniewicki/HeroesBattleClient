package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.game.engine.Game;
import com.khryniewicki.projectX.game.engine.GameState;
import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.user_interface.board.Board;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import com.khryniewicki.projectX.game.user_interface.symbols.Symbol;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.graphics.textures.GameTextures;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.khryniewicki.projectX.game.user_interface.board.GameFactory.BACKGROUND;
import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.ButtonsFactory.MAIN_MENU;
import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.ButtonsFactory.QUIT;
import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextureMenuFactory.YOU_LOSE;
import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextureMenuFactory.YOU_WIN;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;

@Slf4j
public class RestartMenu extends AbstractMenu {
    private Board board;
    private Game game;
    private boolean show;

    private RestartMenu() {
        super();
        start();
        game = Game.getInstance();
        board = game.getBoard();
    }

    public void execute() {
        loop();
        if (Game.state.equals(GameState.OK)) {
            reset();
            game.initialize_game();
        }
    }

    protected void reset() {
        reset_heroes();
        reset_background();
        reset_result();
        show=false;
    }


    protected void reset_heroes() {
        HeroesInstances heroesInstances = HeroesInstances.getInstance();
        heroesInstances.reset();
    }

    private void reset_background() {
        updateImage(BACKGROUND, GameTextures.LIGHT_BACKGROUND);
    }

    private void reset_result() {
        permanentImages = new ArrayList<>();
    }

    @Override
    public void prepare() {
        updateImage(BACKGROUND, GameTextures.SHADOW_BACKGROUND);
        win_or_loose();
        begin();
        addEventClick();
    }

    @Override
    public void loop() {
        if (state.equals(GameState.RESTART)) {
            prepare();
            insideLoop();
        }
    }

    @Override
    public void update() {
        glfwPollEvents();
        board.update();
    }

    @Override
    public void render() {
        clearBuffers();
        board.render();
        buttons.forEach(MenuSymbol::render);
        permanentImages.forEach(MenuSymbol::render);
        swapBuffers();
    }

    @Override
    public void init() {
        super.setButtons(new ArrayList<>(Arrays.asList(QUIT, MAIN_MENU)));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        String btnName = (String) evt.getNewValue();
        if (btnName.equals("main_menu")) {
            game.ok();
        } else if (btnName.equals("quit")) {
            game.finish_game();
        }
        stop();
    }

    public void win_or_loose() {
        HeroesInstances heroesInstances = HeroesInstances.getInstance();
        SuperHero hero = heroesInstances.getHero();
        Integer herolife = hero.getLife();
        if (!show) {
            if (herolife == 0) {
                log.info("YOU LOSE");
                permanentImages.add(YOU_LOSE);
            } else {
                log.info("YOU WIN");
                permanentImages.add(YOU_WIN);
            }
            show = true;
        }

    }

    public void updateImage(Symbol symbol, Texture texture) {
        List<Symbol> symbols = board.getSymbols()
                .stream()
                .peek(menuSymbol -> {
                    if (menuSymbol.equals(symbol)) {
                        symbol.setTexture(texture);
                    }
                })
                .collect(Collectors.toList());
        board.setSymbols(symbols);
    }

    private static final RestartMenu instance = new RestartMenu();

    public static RestartMenu getInstance() {
        return instance;
    }
}
