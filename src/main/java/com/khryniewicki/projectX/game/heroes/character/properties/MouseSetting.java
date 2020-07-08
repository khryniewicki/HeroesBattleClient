package com.khryniewicki.projectX.game.heroes.character.properties;

import com.khryniewicki.projectX.Game;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.SpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spell_properties.UltraSpell;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.services.DTO.DTO;
import com.khryniewicki.projectX.services.DTO.SpellDTO;
import com.khryniewicki.projectX.services.SendingService;
import com.khryniewicki.projectX.utils.StackEvent;
import org.lwjgl.BufferUtils;

import java.nio.DoubleBuffer;
import java.util.concurrent.ConcurrentLinkedDeque;

import static org.lwjgl.glfw.GLFW.*;

public class MouseSetting {

    private final StackEvent stackEvent;
    private Position cursorPosition;
    private SpellInstance spellInstance;
    private SuperHero hero;
    private final SendingService sendingService;
    private UltraSpell ultraSpell;

    private MouseSetting() {
        this.stackEvent = StackEvent.getInstance();
        this.hero = HeroesInstances.getInstance().getHero();
        this.sendingService = new SendingService();
    }

    public void setMouseCallBack() {
        glfwSetMouseButtonCallback(Game.window, (window, key, action, mods) -> {
            cursorPosition = getCursorPosition();

            if (stackEvent.isCastingSpellsActivated()) {

                if ((key == 0 || key == 1)) {
                    getHeroInstance();
                    getUltraSpellInstance();
                    if (key == GLFW_MOUSE_BUTTON_1 && action != GLFW_RELEASE) {
                        ultraSpell.setSpellInstance(hero.getBasicSpell());
                    } else if (key == GLFW_MOUSE_BUTTON_2 && action != GLFW_RELEASE) {
                        ultraSpell.setSpellInstance(hero.getUltimateSpell());
                    }
                    if (isEnoughManaToCast()) {
                        setFinalSpellPosition(cursorPosition);
                        consumeSpellMana();
                        stackEvent.setCastingSpellsActivated(false);
                        sendSpellDTO();
                    }
                }

            }
        });
    }


    private void setFinalSpellPosition(Position position) {
        float factor = 1.1f;
        float finalX= (float) (position.getPositionXD() - Game.width / 2) / (Game.width / 20);
        float finalY= (float) ((Game.height / 2 - position.getPositionYD()) * factor) / (Game.height / 10);
        ultraSpell.setTarget(new Position(finalX,finalY));
    }

    private void consumeSpellMana() {
        Integer heroMana = hero.getMana();
        hero.setMana(heroMana - spellInstance.getManaConsumed());

        ManaBar manaBar = hero.getManaBar();
        manaBar.updateManaBar();

        sendingService.updatePosition();
    }
    private void getUltraSpellInstance() {
        if (ultraSpell==null){
            ultraSpell=hero.getUltraSpell();
        }
    }
    private void getSpellInstance() {
        if (spellInstance==null){
            spellInstance=ultraSpell.getSpellInstance();
        }
    }

    private boolean isEnoughManaToCast() {
        getSpellInstance();
        return hero.getMana() >= spellInstance.getManaConsumed();
    }

    private Position getCursorPosition() {
        DoubleBuffer xBuffer = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer yBuffer = BufferUtils.createDoubleBuffer(1);
        glfwGetCursorPos(Game.window, xBuffer, yBuffer);
        double x = xBuffer.get(0);
        double y = yBuffer.get(0);
        return new Position(x, y);
    }
    private void getHeroInstance() {
        if (hero == null) {
            HeroesInstances instance = HeroesInstances.getInstance();
            hero = instance.getHero();
        }
    }
    private void sendSpellDTO() {
        ConcurrentLinkedDeque<DTO> heroDTOS = stackEvent.getEvents();
        Position target=ultraSpell.getTarget();
        heroDTOS.offerLast(new SpellDTO(ultraSpell.getName(), target.getX(), target.getY()));
    }

    public static MouseSetting getInstance() {
        return MouseSetting.HELPER.INSTANCE;
    }

    private static class HELPER {
        private final static MouseSetting INSTANCE = new MouseSetting();
    }
}
