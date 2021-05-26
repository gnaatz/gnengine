package dev.kolja.gnengine.gui.element;

import dev.kolja.gnengine.color.Color;
import dev.kolja.gnengine.gui.HitBox;
import dev.kolja.gnengine.gui.text.Font;
import dev.kolja.gnengine.gui.text.TextCharacter;
import org.joml.Vector3f;
import org.joml.Vector4f;

public interface IText {
    Font getFont();
    Color getTextColor();
    HitBox getHitBox();
    TextCharacter[] getCharHitBoxes();
    boolean includesCursor();
}
