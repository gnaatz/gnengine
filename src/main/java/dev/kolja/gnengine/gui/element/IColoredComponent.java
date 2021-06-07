package dev.kolja.gnengine.gui.element;

import dev.kolja.gnengine.color.Color;

/**
 * Should be implemented by all components which are solid color.
 */
public interface IColoredComponent {
    Color getColor();
    void setColor(Color color);
}
