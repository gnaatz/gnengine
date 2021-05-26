package dev.kolja.gnengine.gui.element;

import dev.kolja.gnengine.color.Color;

abstract class ParentComponent extends GUIComponent{
    protected Color color;
    protected int padding;

    abstract int getPadding();
    abstract Color getColor();

    abstract void setPadding(int padding);
    abstract void setColor(Color color);
}
