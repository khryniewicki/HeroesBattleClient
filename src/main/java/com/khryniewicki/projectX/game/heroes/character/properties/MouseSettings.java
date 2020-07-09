package com.khryniewicki.projectX.game.heroes.character.properties;

import com.khryniewicki.projectX.Game;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.SpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.UltraSpell;
import com.khryniewicki.projectX.game.heroes.character.SuperHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.services.DTO.DTO;
import com.khryniewicki.projectX.services.DTO.SpellDTO;
import com.khryniewicki.projectX.services.SendingService;
import com.khryniewicki.projectX.utils.StackEvent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.lwjgl.BufferUtils;

import java.nio.DoubleBuffer;
import java.util.concurrent.ConcurrentLinkedDeque;

import static org.lwjgl.glfw.GLFW.*;
@Data
@Slf4j
public class MouseSettings {

    private final StackEvent stackEvent;
    private Position cursorPosition;
    private SpellInstance spellInstance;
    private SuperHero hero;
    private final SendingService sendingService;
    private UltraSpell spell;
    private UltraSpell basicSpell;
    private UltraSpell ultimateSpell;

    private MouseSettings() {
        this.stackEvent = StackEvent.getInstance();
        this.hero = HeroesInstances.getInstance().getHero();
        this.sendingService = new SendingService();
        this.basicSpell=hero.getBasicSpell();
        this.ultimateSpell=hero.getUltimateSpell();
    }

    public void setMouseCallBack() {
        glfwSetMouseButtonCallback(Game.window, (window, key, action, mods) -> {
            cursorPosition = getCursorPosition();
            log.info("SpellActivated[Basic {}],[Ultimate {}]", basicSpell.isSpellActivated(), ultimateSpell.isSpellActivated());

            if ((key == 0 || key == 1)) {
                    getHeroInstance();
                        if (key == GLFW_MOUSE_BUTTON_1 && action != GLFW_RELEASE && !basicSpell.isSpellActivated()) {
                            setSpell(hero.getBasicSpell());
                            send();

                        } else if (key == GLFW_MOUSE_BUTTON_2 && action != GLFW_RELEASE && !ultimateSpell.isSpellActivated()) {
                            setSpell(hero.getUltimateSpell());
                            send();

                        }
            }
        });
    }

    private void send() {
        spell.setStartingTimeSpell(System.currentTimeMillis());

        if (isEnoughManaToCast()) {
            setSpellTarget(spell);
            spell.setSpellActivated(true);
            consumeSpellMana();
            sendSpellDTO();
        }
    }


    private void setSpellTarget(UltraSpell ultraSpell ) {
        float factor = 1.1f;
        float finalX = (float) (cursorPosition.getPositionXD() - Game.width / 2) / (Game.width / 20);
        float finalY = (float) ((Game.height / 2 - cursorPosition.getPositionYD()) * factor) / (Game.height / 10);
        ultraSpell.setTarget(new Position(finalX, finalY));
    }

    private void consumeSpellMana() {
        Integer heroMana = hero.getMana();
        hero.setMana(heroMana - spell.getManaConsumed());

        ManaBar manaBar = hero.getManaBar();
        manaBar.updateManaBar();

        sendingService.updatePosition();
    }

    private boolean isEnoughManaToCast() {
        return hero.getMana() >= spell.getManaConsumed();
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
        Position target = spell.getTarget();
        heroDTOS.offerLast(new SpellDTO(spell.getName(), target.getX(), target.getY()));
    }

    public static MouseSettings getInstance() {
        return MouseSettings.HELPER.INSTANCE;
    }

    private static class HELPER {
        private final static MouseSettings INSTANCE = new MouseSettings();
    }
}
