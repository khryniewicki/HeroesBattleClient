package com.khryniewicki.projectX.game.ui.menu.graphic.factory;

import com.khryniewicki.projectX.graphics.Texture;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.text.AttributedString;


public class TextFactory {
    private static Color defaultColor = Color.WHITE;
    private static Color bg = new Color(0, 0, 0, 1);
    static Graphics2D g2d;

    public static Texture textToImageWithLine(String text, int fontSize) {
        BufferedImage image = createTexture(BLANK_IMAGES.BLANK_WINDOW_WITH_LINE).getImage();
        Font font = new Font("Open Sans", Font.BOLD, fontSize);
        setImage(text, font, defaultColor, image);
        return new Texture(image);
    }

    public static Texture textToImageMenu(String text) {
        BufferedImage image = createTexture(BLANK_IMAGES.BLANK_WINDOW).getImage();
        Font font = new Font("Open Sans", Font.BOLD, 24);
        setImage(text, font, defaultColor, image);
        return new Texture(image);
    }

    public static Texture textInLoadingMenuToImage(String text, Color color, Integer size) {
        BufferedImage image = createTexture(BLANK_IMAGES.BLANK_WINDOW).getImage();
        Font font = new Font("Open Sans", Font.BOLD, size);
        setImage(text, font, color, image);
        return new Texture(image);
    }

    protected static Texture createTexture(BLANK_IMAGES blank_images) {
        return new Texture(blank_images.getPath());
    }

    public static Texture textInPlayersMenuToImage(String text, Color color) {
        BufferedImage image = createTexture(BLANK_IMAGES.BLANK_WINDOW).getImage();
        Font font = new Font("Open Sans", Font.BOLD, 20);
        setImage(text, font, color, image);
        return new Texture(image);
    }

    public static Texture textInPlayerBar(String text, Color color) {
        BufferedImage image = createTexture(BLANK_IMAGES.BLANK_WINDOW_FOR_NUMBER).getImage();
        Font font = new Font("Arial", Font.BOLD, 30);
        setImage(text, font, color, image);
        return new Texture(image);
    }
    public static Texture textInPlayerBarHeroName(String text, Color color) {
        BufferedImage image = createTexture(BLANK_IMAGES.BLANK_WINDOW_FOR_HER0_NAME).getImage();
        Font font = new Font("Arial", Font.BOLD, 25);
        setImage(text, font, color, image);
        return new Texture(image);
    }

    public static Texture textInControlSettingsToImage(String text, Color color) {
        BufferedImage image = createTexture(BLANK_IMAGES.BLANK_WINDOW).getImage();
        Font font = new Font("Arial", Font.BOLD, 30);
        setImage(text, font, color, image);
        return new Texture(image);
    }

    public static Texture textInConnectionWindow(String text) {
        BufferedImage image = createTexture(BLANK_IMAGES.BLANK_WINDOW_CONNECTION).getImage();
        Font font = new Font("Open Sans", Font.BOLD, 20);
        setImage(text, font, defaultColor, image);
        return new Texture(image);
    }

    public static Texture textToPlayerName(String text) {

        BufferedImage image = new BufferedImage(400, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        Font font = new Font("Comic Sans FTW", Font.BOLD, 35);
        FontRenderContext frc = graphics.getFontRenderContext();
        TextLayout textTl = new TextLayout(text, font, frc);
        Shape outline = textTl.getOutline(null);

        FontMetrics fm = graphics.getFontMetrics(font);
        int x = 5;
        int y = (image.getHeight() - fm.getHeight()) / 2 + fm.getAscent();
        graphics.translate(x, y);

//            Stroke stroke = g2d.getStroke();
        graphics.setColor(Color.BLUE);
        graphics.fill(outline);
        graphics.setStroke(new BasicStroke(0.8f));
        // Comic Sans FTW
        graphics.setFont(font);
        graphics.setColor(Color.BLACK);
        graphics.draw(outline);


        // Dispose of the context
        graphics.dispose();
        return new Texture(image);
    }

    private static void setImage(String text, Font font, Color color, BufferedImage image) {
        if (text.length() > 0) {
            g2d = (Graphics2D) image.getGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
            g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);            g2d.setBackground(bg);
            g2d.setColor(color);
            FontMetrics metrics = g2d.getFontMetrics(font);
            AttributedString attributedText = new AttributedString(text);
            attributedText.addAttribute(TextAttribute.FONT, font);
            int positionX = 30;
            int positionY = (image.getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
            g2d.drawString(attributedText.getIterator(), positionX, positionY);
        }
    }



    enum BLANK_IMAGES {
        BLANK_WINDOW("blankTextWindow.png"), BLANK_WINDOW_WITH_LINE("blankTextWindowWithLine.png"),
        BLANK_WINDOW_FOR_NUMBER("blankTextWindowNumber.png"), BLANK_WINDOW_FOR_HER0_NAME("blankTextWindowHeroName.png"),BLANK_WINDOW_CONNECTION("blankTextWindowConnection.png");

        private String path;

        BLANK_IMAGES(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }
    }

}
