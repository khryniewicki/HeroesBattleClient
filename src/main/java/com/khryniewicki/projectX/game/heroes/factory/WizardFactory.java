package com.khryniewicki.projectX.game.heroes.factory;

import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.heroes.wizards.FireWizard;
import com.khryniewicki.projectX.game.heroes.wizards.IceWizard;
import com.khryniewicki.projectX.game.heroes.wizards.ThunderWizard;
import org.springframework.stereotype.Component;

@Component
public class WizardFactory implements CharacterFactory{

    public SuperHero create(String character){
        switch (character){
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
