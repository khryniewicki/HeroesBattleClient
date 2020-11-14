package com.khryniewicki.projectX.game.user_interface.menu.graphic_factory;

import com.khryniewicki.projectX.graphics.Texture;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.text.AttributedString;

import static com.khryniewicki.projectX.graphics.Colors.BOTTLE_GREEN;
import static com.khryniewicki.projectX.graphics.Colors.BRIGHT_GREEN;

public class TextFactory {
    private static Color defaultColor = Color.WHITE;
    private static Color bg=new Color(0,0,0,1);

    public static Texture textToImageWithLine(String text, int fontSize) {
        BufferedImage image = new Texture("blankTextWindow.png").getImage();
        Font font = new Font("Open Sans", Font.BOLD, fontSize);
        setImage(text, font, defaultColor, image);
        return new Texture(image);
    }
    public static Texture textToPlayerName(String text, int fontSize) {

        BufferedImage image = new BufferedImage(400, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        Font font = new Font("Comic Sans FTW", Font.BOLD, fontSize);
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
        graphics.setStroke(new BasicStroke(0.5f));
        // Comic Sans FTW
        graphics.setFont(font);
        graphics.setColor(Color.BLACK);
        graphics.draw(outline);


        // Dispose of the context
        graphics.dispose();
        return new Texture(image);
    }
    public static Texture textToImageMenu(String text) {
        BufferedImage image = new Texture("blankTextWindow.png").getImage();
        Font font = new Font("Open Sans", Font.BOLD, 24);

        setImage(text, font, defaultColor, image);
        return new Texture(image);
    }
    public static Texture textInLoadingMenuToImage(String text, Color color) {
        BufferedImage image = new Texture("blankTextWindow.png").getImage();
        Font font = new Font("Open Sans", Font.BOLD, 22);
        setImage(text, font, color, image);
        return new Texture(image);
    }
    public static Texture textInPlayersMenuToImage(String text, Color color) {
        BufferedImage image = new Texture("blankTextWindow.png").getImage();
        Font font = new Font("Open Sans", Font.BOLD, 20);
        setImage(text, font, color, image);
        return new Texture(image);
    }
    public static Texture textInControlSettingsToImage(String text, Color color) {
        BufferedImage image = new Texture("blankTextWindow.png").getImage();
        Font font = new Font("Arial", Font.BOLD, 30);

        setImage(text, font, color, image);
        return new Texture(image);
    }
    public static Texture textInConnectionWindow(String text) {
        BufferedImage image = new Texture("blankTextWindowConnection.png").getImage();
        Font font = new Font("Open Sans", Font.BOLD, 20);
        setImage(text, font, defaultColor, image);
        return new Texture(image);
    }
    private static void setImage(String text, Font font, Color color, BufferedImage image) {
        if (text.length() > 0) {

            Graphics2D g2d = (Graphics2D) image.getGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
            g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
            g2d.setBackground(bg);
            g2d.setColor(color);
            FontMetrics metrics = g2d.getFontMetrics(font);
            AttributedString attributedText = new AttributedString(text);
            attributedText.addAttribute(TextAttribute.FONT, font);
            int positionX = 30;
            int positionY = (image.getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
            g2d.drawString(attributedText.getIterator(), positionX, positionY);
        }
    }


}
