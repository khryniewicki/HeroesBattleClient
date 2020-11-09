package com.khryniewicki.projectX.game.user_interface.menu.graphic_factory;

import com.khryniewicki.projectX.game.engine.GameLoopImp;
import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.game.heroes.factory.HeroFactory;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.Position;
import com.khryniewicki.projectX.game.user_interface.menu.menus.CharacterMenu;
import com.khryniewicki.projectX.game.user_interface.symbols.MenuSymbol;
import com.khryniewicki.projectX.game.user_interface.symbols.Symbol;
import com.khryniewicki.projectX.graphics.Texture;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextMenuFactory.ANIMATION_DUMMY;
import static com.khryniewicki.projectX.game.user_interface.menu.graphic_factory.TextMenuFactory.ANIMATION_HERO;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;

@Slf4j
@Getter
@Setter
public class Animation extends GameLoopImp {

    private boolean spellActive;
    private boolean tmpHeroSide;
    private int spellingCounter;
    private int randomCasting;
    private int loopCycles;
    private float left_boundary = -7.0f;
    private float right_boundary = 2.5f;
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
        drawRandomNumber();
    }

    public void play(String hero) {
        prepare(hero);
        loop();
    }

    private void prepare(String hero) {
        begin();
        superHero = heroFactory.create(hero);
        spell = new AnimationSpell(superHero, animation_hero);
        characterMenu = CharacterMenu.getInstance();
        addAnimationSymbol(animation_hero);
    }

    @Override
    public void loop() {
        super.loop();
    }

    @Override
    public void render() {
        characterMenu.render();
    }

    @Override
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
            addSpellingCounter();
            resetLoopCounter();
        }
    }

    @Override
    public void stop() {
        super.stop();
        characterMenu.setAnimationSymbols(new ArrayList<>());
    }

    private void spellAndDummyControl() {
        if (this.spellingCounter == (randomCasting - 40)) {
            addDummy();
            addSpellingCounter();
        } else if (this.spellingCounter == (randomCasting - 20)) {
            action(superHero.getHeroAttack());
            castingSpell();
            addSpellingCounter();
        } else if (this.spellingCounter > randomCasting) {
            removeSymbol(dummy);
            removeSpell();
            drawRandomNumber();
            this.spellingCounter = 0;
        }
    }


    private void addDummy() {
        if (((left_boundary + right_boundary) / 2) <= getHeroX()) {
            setPosition(dummy, left_boundary - 0.5f);
            turnLeft(dummy, true);
        } else {
            setPosition(dummy, right_boundary + 0.5f);
            turnLeft(dummy, false);
        }
        updateMesh(dummy);
        addAnimationSymbol(dummy);
    }


    private void castingSpell() {
        tmpHeroSide = animation_hero.isTurningLeft();
        turnLeft(animation_hero, dummy.getPositionX() < 0);
        updateMesh(animation_hero);
        spell.newSpell(randomCasting);
        spell.setTarget(new Position(dummy.getPositionX(), dummy.getPositionY()));
        addAnimationSymbol(spell);
        setSpellActive(true);
        resetLoopCounter();
    }


    private void resetCounting() {
        if (loopCycles == 20) {
            resetLoopCounter();
            setSpellActive(false);
            turnLeft(animation_hero, tmpHeroSide);
            updateMesh(animation_hero);
        }
    }

    private void resetLoopCounter() {
        this.loopCycles = 0;
    }


    private void action(Texture texture) {
        glfwPollEvents();
        animation_hero.setTexture(texture);
        animation_hero.setSIZE(superHero.getSIZE());
        updateMesh(animation_hero);
    }

    private void move() {
        Float x = getHeroX();
        if (x > right_boundary) {
            speed = -0.2f;
            turnLeft(animation_hero, true);
        } else if (x < left_boundary) {
            speed = 0.2f;
            turnLeft(animation_hero, false);
        }
        addSpellingCounter();
        animation_hero.getPosition().x += speed;
    }

    private void drawRandomNumber() {
        this.randomCasting = random.ints(60, 100).findFirst().getAsInt();
    }

    private Float getHeroX() {
        return animation_hero.getPosition().x;
    }

    private void updateMesh(MenuSymbol animation_hero) {
        animation_hero.updateMesh();
    }

    private void setPosition(MenuSymbol symbol, float v) {
        symbol.setPositionX(v);
    }

    private void turnLeft(MenuSymbol symbol, boolean b) {
        symbol.setTurningLeft(b);
    }

    private void addAnimationSymbol(Symbol symbol) {
        animationSymbols = characterMenu.getAnimationSymbols();
        animationSymbols.add(symbol);
    }

    public void removeSymbol(Symbol menuSymbol) {
        if (Objects.nonNull(menuSymbol)) {
            animationSymbols.removeIf(s -> s.getName().equals(menuSymbol.getName()));
        }
    }

    private void addSpellingCounter() {
        this.spellingCounter++;
    }

    public void removeSpell() {
        removeSymbol(spell);
    }

}