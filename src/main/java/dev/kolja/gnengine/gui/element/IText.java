package dev.kolja.gnengine.gui.element;

import dev.kolja.gnengine.color.Color;
import dev.kolja.gnengine.gui.HitBox;
import dev.kolja.gnengine.gui.text.Font;
import dev.kolja.gnengine.gui.text.TextCharacter;

/**
 * Implement if a Component displays text.
 */
public interface IText {
    /**
     * Current font of the text.
     * @return current font
     */
    Font getFont();

    /**
     * Color of the text.
     * @return color of the text
     */
    Color getTextColor();

    /**
     * Text HitBox from start to end
     * @return text HitBox
     */
    HitBox getHitBox();

    /**
     * Array of TextCharacters. Use to get HitBoxes of single chars.
     * @return array of TextCharacters
     */
    TextCharacter[] getCharHitBoxes();

    /**
     * True if a cursor should be displayed.
     * @return true if a cursor should be displayed
     */
    boolean includesCursor();
}
