package com.khryniewicki.projectX.game.control_settings.mouse_settings;

import com.khryniewicki.projectX.game.attack.spells.spell_settings.UltraSpell;
import com.khryniewicki.projectX.game.engine.Game;
import com.khryniewicki.projectX.game.heroes.character.properties.ManaBar;
import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.HeroesInstances;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.services.dto.BaseDto;
import com.khryniewicki.projectX.services.dto.SpellDto;
import com.khryniewicki.projectX.services.sending_service.SendingService;
import com.khryniewicki.projectX.services.sending_service.StackEvent;
import com.khryniewicki.projectX.utils.GameUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentLinkedDeque;

import static org.lwjgl.glfw.GLFW.*;

@Data
@Slf4j
public class MouseSettings {

    private final StackEvent stackEvent;
    private final MousePosition mousePosition;
    private final SendingService sendingService;
    private final UltraSpell basicSpell;
    private final UltraSpell ultimateSpell;
    private SuperHero hero;
    private Position cursorPosition;
    private UltraSpell spell;

    public MouseSettings() {
        this.stackEvent = StackEvent.getInstance();
        this.hero = HeroesInstances.getInstance().getHero();
        this.sendingService = new SendingService();
        this.basicSpell = hero.getBasicSpell();
        this.ultimateSpell = hero.getUltimateSpell();
        this.mousePosition = new MousePosition();
    }

    public void cast_spells() {
        glfwSetMouseButtonCallback(GameUtil.window, (window, key, action, mods) -> {
            cursorPosition = mousePosition.getCursorPosition();
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
        if (isSpellCursorInView() && isEnoughManaToCast()) {
            setSpellTarget(spell);
            spell.setSpellActivated(true);
            consumeSpellMana();
            sendSpellDTO();
        }
    }

    private boolean isSpellCursorInView() {
        return cursorPosition.getPositionYD() > 40f;
    }


    private void setSpellTarget(UltraSpell ultraSpell) {
        float factor = 1.1f;
        float finalX = (float) (cursorPosition.getPositionXD() - Game.width / 2) / (Game.width / 20f);
        float finalY = (float) ((Game.height / 2 - cursorPosition.getPositionYD()) * factor) / (Game.height / 10f);
        ultraSpell.setTarget(new Position(finalX, finalY));
    }

    private void consumeSpellMana() {
        Float heroMana = hero.getMana();
        hero.setMana(heroMana - spell.getManaConsumed());

        ManaBar manaBar = hero.getManaBar();
        manaBar.updateManaBar();
        stackEvent.addHeroDto();

    }


    private boolean isEnoughManaToCast() {
        return hero.getMana() >= spell.getManaConsumed();
    }

    private void getHeroInstance() {
        if (hero == null) {
            HeroesInstances instance = HeroesInstances.getInstance();
            hero = instance.getHero();
        }
    }

    private void sendSpellDTO() {
        ConcurrentLinkedDeque<BaseDto> heroBaseDtos = stackEvent.getEvents();
        Position target = spell.getTarget();
        heroBaseDtos.offerLast(new SpellDto(spell.getName(), target.getX(), target.getY()));
    }

}
