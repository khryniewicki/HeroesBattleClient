package com.khryniewicki.projectX.game.control_settings.mouse_settings;

import com.khryniewicki.projectX.game.engine.Game;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.game.user_interface.menu.menus.LoadingMenu;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.lwjgl.BufferUtils;

import java.nio.DoubleBuffer;

import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;

@Data
@Slf4j
public class MousePosition {
    private float windowPositionX;
    private float windowPositionY;

    public void setWindowPositions(double x, double y) {
        windowPositionX = (float) ((x - Game.width / 2) / (Game.width / 20f));
        windowPositionY = (float) (((Game.height+Game.bar) / 2 - y)) / ((Game.height+Game.bar) / 11f);
    }

    public Position getCursorPosition() {
        DoubleBuffer xBuffer = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer yBuffer = BufferUtils.createDoubleBuffer(1);
        glfwGetCursorPos(LoadingMenu.window, xBuffer, yBuffer);
        double x = xBuffer.get(0);
        double y = yBuffer.get(0);
        setWindowPositions(x, y);
        return new Position(x, y);
    }

    @Override
    public String toString() {
        return "MousePosition{" +
                "windowPositionX=" + windowPositionX +
                ", windowPositionY=" + windowPositionY +
                '}';
    }
}
