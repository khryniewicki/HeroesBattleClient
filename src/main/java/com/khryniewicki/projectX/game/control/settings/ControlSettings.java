package com.khryniewicki.projectX.game.control.settings;

import com.khryniewicki.projectX.game.control.settings.keyboard.MoveSettings;
import com.khryniewicki.projectX.game.control.settings.mouse.MouseSettings;

public class ControlSettings {
    private MoveSettings moveSettings;
    private MouseSettings mouseSettings;

    public ControlSettings() {
        this.moveSettings = new MoveSettings();
        this.mouseSettings = new MouseSettings();
        addControlSettings();
    }

    private void addControlSettings() {
        mouseSettings.castSpells();
        moveSettings.move();
    }
}
