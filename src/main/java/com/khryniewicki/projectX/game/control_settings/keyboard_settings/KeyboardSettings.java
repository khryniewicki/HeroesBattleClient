package com.khryniewicki.projectX.game.control_settings.keyboard_settings;

import com.khryniewicki.projectX.Game;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import lombok.Getter;

import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;

@Getter
public class KeyboardSettings {
    private StringBuilder sb = new StringBuilder();

    public void insert(MenuSymbol heroName) {

        glfwSetKeyCallback(Game.window, (window, key, scancode, action, mods) -> {
            if (key >= 65 && key <= 90 && action != GLFW_RELEASE) {

                String letter = Character.toString((char) key);
                if (sb.length() <= 15) {
                    sb.append(letter);
                }
                heroName.setAction(sb.toString());
            } else if (key == 259 && action != GLFW_RELEASE) {
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                    heroName.setAction(sb.toString());
                }
            }
        });
    }
}
