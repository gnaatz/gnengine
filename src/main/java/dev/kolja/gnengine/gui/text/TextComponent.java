package dev.kolja.gnengine.gui.text;

import dev.kolja.gnengine.color.Color;
import dev.kolja.gnengine.gui.element.GUIComponent;
import dev.kolja.gnengine.gui.HitBox;
import dev.kolja.gnengine.gui.element.IText;
import dev.kolja.gnengine.gui.render.TextRenderer;
import org.joml.Vector2f;

/**
 * Simple Text as a GUI component.
 */
public class TextComponent extends GUIComponent implements IText {

    private final String text;
    private final Font font;
    private final TextCharacter[] charHitBoxes;

    private Color textColor;

    /**
     * Simple Text at 0, 0.
     * @param text text to be displayed
     * @param font font used for the text
     */
    public TextComponent(String text, Font font) {
        super();
        this.text = text;
        this.font = font;
        this.hitBox = new HitBox();
        this.textColor = new Color(1.0f,1.0f,1.0f);
        charHitBoxes = new TextCharacter[text.length()];
        init();
        this.renderer = new TextRenderer(this);
    }

    private void init() {
        int nextCharX = 0;
        for(int i = 0; i < text.length(); i++) {
            CharInfo info = font.getCharInfo(text.charAt(i));
            charHitBoxes[i] = new TextCharacter(nextCharX, 0, text.charAt(i), font);
            charHitBoxes[i].setTextureCoords(new Vector2f(info.getX(), info.getY()));
            nextCharX = nextCharX + info.getWidth();
        }
    }

    @Override
    public void render() {
        renderer.render();
    }

    @Override
    public void tick() {

    }

    @Override
    public Color getTextColor() {
        return textColor;
    }

    @Override
    public HitBox getHitBox() {
        return hitBox;
    }

    @Override
    public TextCharacter[] getCharHitBoxes() {
        return charHitBoxes;
    }

    @Override
    public boolean includesCursor() {
        return false;
    }

    @Override
    public void setXPos(int xPos) {
        this.xPos = xPos;
        hitBox.updateX(xPos);
    }

    @Override
    public void setYPos(int yPos) {
        this.yPos = yPos;
        hitBox.updateY(yPos);
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
        hitBox.updateWidth(width);
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
        hitBox.updateHeight(height);
    }

    public void setTextColor(Color color) {
        this.textColor = color;
    }

    @Override
    public void setHitBox(HitBox hitBox) {
        this.hitBox = hitBox;
        xPos = (int) hitBox.x();
        yPos = (int) hitBox.y();
        width = (int) hitBox.width();
        height = (int) hitBox.height();
    }

    public Font getFont() {
        return font;
    }
}
