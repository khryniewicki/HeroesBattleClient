package com.khryniewicki.projectX.game.ui.menu.graphic.factory;

import com.khryniewicki.projectX.game.attack.spell.instance.BasicSpellInstance;
import com.khryniewicki.projectX.game.attack.spell.instance.UltimateSpellInstance;
import com.khryniewicki.projectX.game.heroes.character.properties.SuperHero;
import com.khryniewicki.projectX.game.heroes.factory.HeroFactory;
import com.khryniewicki.projectX.graphics.Texture;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.text.AttributedString;

import static com.khryniewicki.projectX.graphics.Colors.*;
import static java.awt.RenderingHints.*;
import static java.lang.String.format;
import static java.lang.String.valueOf;

@Slf4j
public class TableFactory {

    static Graphics2D g2d;

    public static Texture tableImage(String heroName, int spellInstanceNumber) {
        HeroFactory heroFactory = HeroFactory.getInstance();
        SuperHero superHero = heroFactory.create(heroName);
        BufferedImage image = createBlankImage().getImage();
        createTableImage(image, superHero, spellInstanceNumber);
        return new Texture(image);
    }

    private static Texture createBlankImage() {
        String path = "blankTableWindow.png";
        return new Texture(path);
    }

    public static void createTableImage(BufferedImage image, SuperHero superHero, int spellInstanceNumber) {
        BasicSpellInstance basic = superHero.getBasicSpellInstance();
        UltimateSpellInstance ultimate = superHero.getUltimateSpellInstance();
        Font font = new Font("Open Sans", Font.BOLD, 20);
        g2d = (Graphics2D) image.getGraphics();
        bufferedImage();
        g2d.setColor(Color.BLUE);

        String[] heroHeader = {"Life", "Mana", "Mana Recovery", "Basic Attack", "Ultimate Attack"};
        Float manaRegeneration = superHero.getManaRegeneration();
        String[] heroData = {superHero.getLife().toString(), fmt(superHero.getMana()), fmt(manaRegeneration) + " / sec", basic.getName(), ultimate.getName()};
        String[] spellsHeader = {"Spell", "Damage", "Mana Cost", "Spell Speed", "Cooldown"};
        String[] spelldata1 = {basic.getName(), basic.getPowerAttack().toString(), basic.getManaConsumed().toString(), valueOf(basic.getCastingSpeed() * 10), format("%d sec", basic.getSpellDuration() / 1000)};
        String[] spelldata2 = {ultimate.getName(), ultimate.getPowerAttack().toString(), ultimate.getManaConsumed().toString(), valueOf((ultimate.getCastingSpeed() * 10)), format("%d sec", ultimate.getSpellDuration() / 1000)};

        for (int i = 0; i < heroHeader.length; i++) {
            int y = 20 + i * 40;
            int x = 170;
            int x2 = 350;
            int x3 = 520;
            int positionX = 10;
            int positionY = y + 30;
            g2d.setColor(BRIGHT_BLUE);
            if (i == 0) {
                g2d.draw(new Line2D.Float((float) x, 30f, (float) x, 215f));
                g2d.draw(new Line2D.Float((float) x + x2, 30f, (float) x + x2, 215f));
                g2d.draw(new Line2D.Float((float) x + x3, 30f, (float) x + x3, 215f));

            } else {
                g2d.draw(new Line2D.Float(0f, (float) y, 320, (float) y));
                g2d.draw(new Line2D.Float(x2 + 30, (float) y, x3 + 320, (float) y));
            }

            font(font, positionX, positionY, heroHeader[i]);

            font(font, x2 + positionX + 30, positionY, spellsHeader[i]);
            graphicSetColor(false);

            font(font, x + positionX, positionY, heroData[i]);
            graphicSetColor(spellInstanceNumber != 0 && spellInstanceNumber % 2 == 0);

            font(font, x2 + x + positionX, positionY, spelldata1[i]);
            graphicSetColor(spellInstanceNumber % 2 == 1);

            font(font, x3 + x + positionX, positionY, spelldata2[i]);
        }

    }

    private static void graphicSetColor(boolean isRed) {
        g2d.setColor(isRed ? BRIGHT_RED : BRIGHT_YELLOW);
    }

    private static void font(Font font, int positionX, int positionY, String s) {
        AttributedString header = new AttributedString(s);
        header.addAttribute(TextAttribute.FONT, font);
        g2d.drawString(header.getIterator(), positionX, positionY);
    }


    protected static void bufferedImage() {
        g2d.setRenderingHint(KEY_ALPHA_INTERPOLATION, VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(KEY_COLOR_RENDERING, VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(KEY_DITHERING, VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(KEY_FRACTIONALMETRICS, VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(KEY_INTERPOLATION, VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(KEY_RENDERING, VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(KEY_STROKE_CONTROL, VALUE_STROKE_PURE);
    }

    public static String fmt(double d) {
        if (d == (long) d)
            return format("%d", (long) d);
        else
            return format("%s", d);
    }

}

