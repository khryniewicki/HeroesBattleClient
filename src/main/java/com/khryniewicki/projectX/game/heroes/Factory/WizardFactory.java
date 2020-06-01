package com.khryniewicki.projectX.game.heroes.Factory;

import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.heroes.wizards.FireWizard;
import com.khryniewicki.projectX.game.heroes.wizards.IceWizard;
import com.khryniewicki.projectX.game.heroes.wizards.ThunderWizard;

public class WizardFactory {

    public SuperHero createWizard(String s){
        switch (s){
            case "1":
                return new FireWizard();
            case "2":
                return new IceWizard();
            case "3":
                return new ThunderWizard();
            default:
                throw new IllegalArgumentException("Wrong input ");
        }

    }
}
