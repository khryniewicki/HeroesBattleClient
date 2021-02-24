package com.khryniewicki.projectX.game.engine;

import com.khryniewicki.projectX.game.multiplayer.controller.MultiplayerController;
import com.khryniewicki.projectX.game.user_interface.menu.menus.BlankMenu;
import com.khryniewicki.projectX.game.user_interface.menu.menus.LoadingMenu;
import com.khryniewicki.projectX.game.user_interface.menu.menus.MainMenu;
import com.khryniewicki.projectX.game.user_interface.menu.menus.RestartMenu;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentLinkedDeque;

@Slf4j
public class Application implements Runnable {
    private static final ConcurrentLinkedDeque<Command> COMMANDS = new ConcurrentLinkedDeque<>();
    public static GameState state = GameState.OK;


    public void start() {
        new Thread(this, "Game").start();
    }

    public void run() {
        prepare();
        init();
        play();
    }

    public static void prepare() {
        COMMANDS.add(LoadingMenu.getInstance());
        COMMANDS.add(BlankMenu.getInstance());
        COMMANDS.add(MainMenu.getInstance());
        COMMANDS.add(MultiplayerController.getInstance());
        COMMANDS.add(Game.getInstance());
        COMMANDS.add(RestartMenu.getInstance());
        COMMANDS.add(terminate());
    }

    private static void init() {
        Command command = COMMANDS.removeFirst();
        command.execute();
    }

    public static void play() {
        COMMANDS.forEach(Command::execute);
    }

    private static Command terminate() {
        return () -> {
            log.info("TERMINATE");
            if (state.equals(GameState.FINISH)) {
                MultiplayerController multiplayerInstance = MultiplayerController.getInstance();
                multiplayerInstance.stop_websocket();
                multiplayerInstance.kill_process();
            }
        };
    }

    public static void restart_game() {
        setState(GameState.RESTART);
    }

    public static void finish_game() {
        setState(GameState.FINISH);
    }

    public static void ok() {
        setState(GameState.OK);
    }

    private static void setState(GameState game_state) {
        state = game_state;
        log.info("GAME STATE: {}", state);
    }
}
