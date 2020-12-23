package com.khryniewicki.projectX.game.heroes.character.properties;

import com.khryniewicki.projectX.game.attack.spells.spell_instances.BasicSpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.SpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spell_instances.UltimateSpellInstance;
import com.khryniewicki.projectX.game.attack.spells.spell_settings.UltraSpell;
import com.khryniewicki.projectX.game.control_settings.keyboard_settings.MoveSettings;
import com.khryniewicki.projectX.game.multiplayer.heroStorage.positions.StartingPosition;
import com.khryniewicki.projectX.graphics.Shader;
import com.khryniewicki.projectX.graphics.Texture;
import com.khryniewicki.projectX.graphics.VertexArray;
import com.khryniewicki.projectX.math.Matrix4f;
import com.khryniewicki.projectX.math.Vector;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Data
@Slf4j
@Service
public class SuperHero implements UltraHero {

    private boolean isTurningLeft;
    private VertexArray mesh;
    private Texture texture, heroUp, heroDown, heroLeft, heroRight, heroIdle, heroAttack;
    private volatile Vector position = new Vector();
    protected HeroAttributes heroAttributes;
    private String name;
    private Integer life;
    private Integer mana;
    private Long manaRenegeration = 1000L;

    public static float hero_positionX0;
    public static float hero_positionY0;
    private float hero_left_offset, hero_right_offset, hero_bottom_offset;
    private float hero_top_offset;
    public float SIZE = 1f;


    private MoveSettings moveSettings;
    private StartingPosition startingPosition;

    private SpellInstance spellInstance;
    private UltraSpell basicSpell;
    private UltraSpell ultimateSpell;
    private BasicSpellInstance basicSpellInstance;
    private UltimateSpellInstance ultimateSpellInstance;
    private ManaBar manaBar;

    public void addProperties(SuperHero superHero) {
        this.name = superHero.name;
        this.texture = superHero.heroIdle;
        this.heroUp = superHero.heroUp;
        this.heroDown = superHero.heroDown;
        this.heroLeft = superHero.heroLeft;
        this.heroRight = superHero.heroRight;
        this.heroIdle = superHero.heroIdle;
        this.heroAttack = superHero.heroAttack;
        this.hero_left_offset = superHero.hero_left_offset;
        this.hero_top_offset = superHero.hero_top_offset;
        this.basicSpellInstance = superHero.basicSpellInstance;
        this.ultimateSpellInstance = superHero.ultimateSpellInstance;
        this.mana = superHero.mana;
        this.life = superHero.life;
        this.SIZE = superHero.SIZE;
    }

    public VertexArray createHero() {
        int i = isTurningLeft ? -1 : 1;

        float[] vertices = new float[]{
                hero_positionX0 + -SIZE / 2.0f, hero_positionY0 + -SIZE / 2.0f, 0.8f,
                hero_positionX0 + -SIZE / 2.0f, hero_positionY0 + SIZE / 2.0f, 0.8f,
                hero_positionX0 + SIZE / 2.0f, hero_positionY0 + SIZE / 2.0f, 0.8f,
                hero_positionX0 + SIZE / 2.0f, hero_positionY0 + -SIZE / 2.0f, 0.8f
        };

        byte[] indices = new byte[]{
                0, 1, 2,
                2, 3, 0
        };

        float[] tcs = new float[]{
                0, 1,
                0, 0,
                i, 0,
                i, 1
        };

        return new VertexArray(vertices, indices, tcs);
    }

//    public void update() {
//        if (position.y>5.0f){
//        }else {
//            if (position.x >= 10f) {
//                setPositionY(position.y + 0.2f);
//                setPositionX(-10f);
//            }
//            setPositionX(position.x + 0.2f);
//        }
//    }

    public void update() {
        regenerateMana();
    }

    private void regenerateMana() {
        if (Objects.isNull(manaBar)) {
            manaBar = getManaBar();
        }
        manaBar.renegerateMana();
    }


    public void render() {
        Shader.HERO.enable();
        Shader.HERO.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
        texture.bind();
        mesh.render();
        Shader.HERO.disable();
        heroAttributes.render();
    }


    public synchronized Float getX() {
        return position.x;
    }

    public synchronized Float getY() {
        return position.y;
    }

    @Override
    public void setPositionX(Float positionX) {
        this.position.x = positionX;
    }

    @Override
    public void setPositionY(Float positionY) {
        this.position.y = positionY;
    }

    @Override
    public void setMesh() {
        setMesh(createHero());
    }

    @Override
    public void setPosition() {
        setPosition(new Vector());
    }

    @Override
    public void setHeroIdle() {
        setTexture(heroIdle);
    }

    @Override
    public void setHeroRun() {
        setTexture(heroRight);
    }

    @Override
    public void setHeroAttack() {
        setTexture(heroAttack);
    }

    @Override
    public LifeBar getLifeBar() {
        return heroAttributes.getLifeBar();
    }

    @Override
    public ManaBar getManaBar() {
        return heroAttributes.getManaBar();
    }

    @Override
    public PlayerNameBar getPlayerNameBar() {
        return heroAttributes.getPlayerNameBar();
    }

}
