package com.khryniewicki.projectX.game.heroes.factory;

import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.heroes.character.fallens.Fallen1;
import com.khryniewicki.projectX.game.heroes.character.fallens.Fallen2;
import com.khryniewicki.projectX.game.heroes.character.fallens.Fallen3;
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
            case "Fallen1":
                return new Fallen1();
            case "Fallen2":
                return new Fallen2();
            case "Fallen3":
                return new Fallen3();
            default:
                throw new IllegalArgumentException("Wrong input ");
        }

    }
}
