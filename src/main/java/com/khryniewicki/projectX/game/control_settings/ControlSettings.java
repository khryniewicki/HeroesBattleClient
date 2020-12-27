package com.khryniewicki.projectX.game.control_settings;

import com.khryniewicki.projectX.game.control_settings.keyboard_settings.MoveSettings;
import com.khryniewicki.projectX.game.control_settings.mouse_settings.MouseSettings;

public class ControlSettings {
    private MoveSettings moveSettings;
    private MouseSettings mouseSettings;

    public ControlSettings() {
        this.moveSettings = new MoveSettings();
        this.mouseSettings = new MouseSettings();
        add_control_settings();
    }

    private void add_control_settings() {
        mouseSettings.cast_spells();
        moveSettings.move();
    }
}
