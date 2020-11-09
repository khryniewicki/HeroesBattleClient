package com.khryniewicki.projectX.game.user_interface.menu.graphic_factory;

import com.khryniewicki.projectX.Game;
import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.game.heroes.factory.HeroFactory;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.game.user_interface.menu.menus.CharacterMenu;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import com.khryniewicki.projectX.game.user_interface.symbols.Symbol;
import com.khryniewicki.projectX.graphics.Texture;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextMenuFactory.ANIMATION_DUMMY;
import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextMenuFactory.ANIMATION_HERO;
import static org.lwjgl.glfw.GLFW.*;

@Slf4j
@Data
public class Animation {

    private boolean running = true;
    private boolean spellActive;
    private boolean tmpHeroSide;
    private float left_boundary = -7.0f;
    private float right_boundary = 2.5f;
    private int spellingCounter;
    private int randomCasting;
    private int loopCycles;
    private float speed = 0.2f;
    private AnimationSpell spell;
    private SuperHero superHero;
    private HeroFactory heroFactory;
    private CharacterMenu characterMenu;
    private MenuSymbol animation_hero;
    private MenuSymbol dummy;
    private Random random;
    private List<Symbol> animationSymbols;

    public Animation() {
        heroFactory = HeroFactory.getInstance();
        animation_hero = ANIMATION_HERO;
        dummy = ANIMATION_DUMMY;
        random = new Random(331);
        this.randomCasting = drawRandomNumber();
    }

    public void play(String hero) {
        setRunning(true);
        superHero = heroFactory.create(hero);
        spell = new AnimationSpell(superHero, animation_hero);
        characterMenu = CharacterMenu.getInstance();
        animationSymbols = characterMenu.getAnimationSymbols();
        animationSymbols.add(animation_hero);
        long lastTime = System.nanoTime();
        double delta = 0.0;
        double ns = 1000000000.0 / 60.0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1.0) {
                update();
                updates++;
                delta--;
            }
            render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                glfwSetWindowTitle(Game.window, "HeroesBattle  | " + updates + " ups, " + frames + " fps ");
                updates = 0;
                frames = 0;
            }
            if (glfwWindowShouldClose(Game.window))
                running = false;
        }
    }

    private void render() {
        characterMenu.render();
    }


    public void update() {
        if (!spellActive) {
            spellAndDummyControl();
            heroControl();
        } else {
            resetCounting();
        }
        spell.update();
        loopCycles++;
    }

    private void heroControl() {
        if (loopCycles == 5) {
            move();
            action(superHero.getHeroRight());
        }
        if (loopCycles == 9) {
            action(superHero.getHeroIdle());
            spellingCounter++;
            loopCycles = 0;
        }
    }

    private void spellAndDummyControl() {
        if (this.spellingCounter == (randomCasting - 40)) {
            addDummy();
            this.spellingCounter++;
        } else if (this.spellingCounter == (randomCasting - 20)) {
            action(superHero.getHeroAttack());
            castingSpell();
            this.spellingCounter++;
        } else if (this.spellingCounter > randomCasting) {
            animationSymbols.remove(dummy);
            removeIfSpellExists();
            this.randomCasting = drawRandomNumber();
            this.spellingCounter = 0;
        }
    }

    private void addDummy() {
        if (((left_boundary + right_boundary) / 2) <= getHeroX()) {
            dummy.setPositionX(left_boundary - 0.5f);
            dummy.setMovingLeft(true);
        } else {
            dummy.setPositionX(right_boundary + 0.5f);
            dummy.setMovingLeft(false);
        }
        dummy.updateMesh();
        animationSymbols.add(dummy);
    }


    private void castingSpell() {

        tmpHeroSide = animation_hero.isMovingLeft();
        animation_hero.setMovingLeft(dummy.getPositionX() < 0);
        animation_hero.updateMesh();
        spell.newSpell(randomCasting);
        spell.setTarget(new Position(dummy.getPositionX(), dummy.getPositionY()));
        animationSymbols.add(spell);
        setSpellActive(true);
        loopCycles = 0;
    }


    private void resetCounting() {
        if (loopCycles == 20) {
            this.loopCycles = 0;
            setSpellActive(false);
            animation_hero.setMovingLeft(tmpHeroSide);
            animation_hero.updateMesh();
        }
    }

    private void action(Texture texture) {
        glfwPollEvents();
        animation_hero.setTexture(texture);
        animation_hero.setSIZE(superHero.getSIZE());
        animation_hero.updateMesh();
    }

    private void move() {
        Float x = getHeroX();
        if (x > right_boundary) {
            speed = -0.2f;
            animation_hero.setMovingLeft(true);
        } else if (x < left_boundary) {
            speed = 0.2f;
            animation_hero.setMovingLeft(false);
        }
        spellingCounter++;
        animation_hero.getPosition().x += speed;
    }

    private int drawRandomNumber() {
        return random.ints(60, 100).findFirst().getAsInt();
    }

    private Float getHeroX() {
        return animation_hero.getPosition().x;
    }

    public void removeIfSpellExists() {
        if (Objects.nonNull(spell)) {
            animationSymbols.removeIf(s -> s.getName().equals(spell.getName()));
        }
    }

    public void stop() {
        characterMenu.setAnimationSymbols(new ArrayList<>());
        setRunning(false);
    }
}