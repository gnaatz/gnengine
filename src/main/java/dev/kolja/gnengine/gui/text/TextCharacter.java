package dev.kolja.gnengine.gui.text;

import dev.kolja.gnengine.gui.HitBox;
import org.joml.Vector2f;

public class TextCharacter extends HitBox {
    private final Font font;

    private Vector2f textureCoords;

    public TextCharacter(int x, int y, char code, Font font) {
        super(x, y, font.getCharInfo(code).getWidth(), font.getCharInfo(code).getHeight());
        this.font = font;
    }

    public void setTextureCoords(Vector2f textureCoords) {
        this.textureCoords = textureCoords;
    }

    public Vector2f getGLTextureCoords() {
        return new Vector2f( textureCoords.x / (float) font.getTextureWidth(),1f - (textureCoords.y / (float) font.getTextureHeight()));
    }

    public Vector2f getGLTextureStride() {
        return new Vector2f((float) width / font.getTextureWidth(), (float) height / font.getTextureHeight());
    }
}
