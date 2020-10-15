package com.khryniewicki.projectX.game.user_interface.menu.menus;

import com.khryniewicki.projectX.Game;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.game.settings.MousePosition;
import com.khryniewicki.projectX.game.user_interface.menu.buttons.Button;
import com.khryniewicki.projectX.game.user_interface.menu.buttons.ButtonTransferObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import static com.khryniewicki.projectX.Game.window;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

@Data
@Slf4j
public class MenuImp implements PropertyChangeListener, Menu {

    private ButtonTransferObject buttonTransferObject;
    private List<Button> buttons;
    private MousePosition mousePosition;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        setButtonTransferObject((ButtonTransferObject) evt.getNewValue());
    }

    @Override
    public void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        buttons.forEach(Button::render);
        swapBuffers();
    }

    @Override
    public void swapBuffers() {
        int error = glGetError();
        if (error != GL_NO_ERROR)
            System.out.println(error);
        glfwSwapBuffers(window);
    }

    @Override
    public void changeButton(Button button, ButtonTransferObject buttonTransferObject) {
        button.setNews(buttonTransferObject);
    }

    @Override
    public void addEventClick() {
        mousePosition = new MousePosition();
        glfwSetMouseButtonCallback(Game.window, (window, key, action, mods) -> {
            Position cursorPosition = mousePosition.getCursorPosition();
            if (key == 0) {
                if (action != GLFW_RELEASE) {
                    buttons.forEach(btn -> {
                        if (mousePosition.getWindowPositionX() > btn.getPositionX0() && mousePosition.getWindowPositionX() < btn.getPositionX1()) {
                            if (mousePosition.getWindowPositionY() > btn.getPositionY0() && mousePosition.getWindowPositionY() < btn.getPositionY1()) {
                                changeButton(btn, new ButtonTransferObject(btn.getName(), cursorPosition));
                                log.info("{}: [{}, {}]", buttonTransferObject.getName(), buttonTransferObject.getPosition().getPositionXD(), buttonTransferObject.getPosition().getPositionYD());
                            }
                        }
                    });
                }
            }
        });
    }
}
