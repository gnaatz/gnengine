package dev.kolja.gnengine.gui.text;

import dev.kolja.gnengine.gui.HitBox;
import org.joml.Vector2f;

/**
 * TextCharacter containing information necessary for drawing.
 */
public class TextCharacter extends HitBox {
    private final Font font;

    private Vector2f textureCoords;

    /**
     * Creates TextCharacter.
     * @param x x value of character position on screen
     * @param y y value of character position on screen
     * @param code char code of character
     * @param font font to be used
     */
    public TextCharacter(int x, int y, char code, Font font) {
        super(x, y, font.getCharInfo(code).getWidth(), font.getCharInfo(code).getHeight());
        this.font = font;
    }

    /**
     * Sets the texture coordinates of the character.
     * @param textureCoords texture coordinates of character
     */
    public void setTextureCoords(Vector2f textureCoords) {
        this.textureCoords = textureCoords;
    }

    /**
     * Returns GL coordinates (-1 -> 1) of the character.
     * @return GL coordinates of character
     */
    public Vector2f getGLTextureCoords() {
        return new Vector2f( textureCoords.x / (float) font.getTextureWidth(),1f - (textureCoords.y / (float) font.getTextureHeight()));
    }

    /**
     * Returns GL stride (projected to -1 -> 1) of the character.
     * @return GL stride of character
     */
    public Vector2f getGLTextureStride() {
        return new Vector2f((float) width / font.getTextureWidth(), (float) height / font.getTextureHeight());
    }
}
