package com.khryniewicki.projectX.game.user_interface.menu.buttons;

public enum Buttons {
    START("Start"),SELECT_CHARACTER("ChooseCharacter"), CONTROL_SETTINGS("ControlSettings"),
    QUIT("QuitGame"), CONROL_SETTINGS_RETURN("Return2"),
    TABLE_WITH_SKILLS("showTable"), WRITE_HERO_NAME("typeYourName"), SELECT_CHARACTER_RETURN("Return"),
    FALLEN_MONK("FallenMonk"),FALLEN_WITCHER("FallenWitcher"),FALLEN_KING("FallenKing"),
    FIRE_WIZARD("FireWizard"),ICE_WIZARD( "IceWizard"),THUNDER_WIZARD("ThunderWizard"),
    RESTART_GO_TO_MAIN_MENU("main_menu"),RESTART_QUIT("quit");

    private String name;

    Buttons(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
