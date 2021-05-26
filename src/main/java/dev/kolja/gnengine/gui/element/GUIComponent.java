package dev.kolja.gnengine.gui.element;

import dev.kolja.gnengine.gui.GUIHandler;
import dev.kolja.gnengine.gui.HitBox;
import dev.kolja.gnengine.input.MouseInputHandler;
import dev.kolja.gnengine.render.Renderable;
import org.joml.Vector3f;

import java.util.List;

public abstract class GUIComponent {
    protected GUIComponent parent;
    protected List<GUIComponent> children;
    protected MouseInputHandler mouseInputHandler;
    protected int xPos;
    protected int yPos;

    protected int width;
    protected int height;
    protected Renderable renderer;
    protected HitBox hitBox;

    protected GUIComponent() {
        mouseInputHandler = MouseInputHandler.getInstance();
        GUIHandler.getInstance().addElement(this);
    }

    public abstract void render();

    public abstract void tick();

    public abstract HitBox getHitBox();

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public abstract void setXPos(int xPos);

    public abstract void setYPos(int yPos);

    public abstract void setWidth(int width);

    public abstract void setHeight(int height);

    public abstract void setHitBox(HitBox hitBox);
}
