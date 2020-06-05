package com.khryniewicki.projectX.game.heroes.Factory;

import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.heroes.wizards.FireWizard;
import com.khryniewicki.projectX.game.heroes.wizards.IceWizard;
import com.khryniewicki.projectX.game.heroes.wizards.ThunderWizard;

public class WizardFactory {

    public SuperHero createWizard(String s){
        switch (s){
            case "FireWizard":
                return new FireWizard();
            case "IceWizard":
                return new IceWizard();
            case "ThunderWizard":
                return new ThunderWizard();
            default:
                throw new IllegalArgumentException("Wrong input ");
        }

    }
}
