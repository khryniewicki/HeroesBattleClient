package com.khryniewicki.projectX.game.heroes.Factory;

import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.heroes.wizards.FireWizard;
import com.khryniewicki.projectX.game.heroes.wizards.IceWizard;
import com.khryniewicki.projectX.game.heroes.wizards.ThunderWizard;

public class WizardFactory {

    public SuperHero createWizard(String s){
        switch (s){
            case "fire":
                return new FireWizard();
            case "ice":
                return new IceWizard();
            case "thunder":
                return new ThunderWizard();
            default:
                throw new IllegalArgumentException("Wrong input ");
        }

    }
}
