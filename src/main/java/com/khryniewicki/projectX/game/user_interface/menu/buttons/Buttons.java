package com.khryniewicki.projectX.game.user_interface.menu.buttons;

public enum Buttons {
    START("Start"),SELECT_CHARACTER("ChooseCharacter"), CONTROL_SETTINGS("ControlSettings"),
    QUIT("QuitGame"), CONROL_SETTINGS_RETURN("Return2"),
    TABLE_WITH_SKILLS("showTable"), WRITE_HERO_NAME("typeYourName"), SELECT_CHARACTER_RETURN("Return"),
    FALLEN_MONK("Fallen Monk"),FALLEN_WITCHER("Fallen Witcher"),FALLEN_KING("Fallen King"),
    FIRE_WIZARD("Fire Wizard"),ICE_WIZARD( "Ice Wizard"),THUNDER_WIZARD("Thunder Wizard"),
    RESTART_GO_TO_MAIN_MENU("main_menu"),RESTART_QUIT("quit");

    private String name;

    Buttons(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
