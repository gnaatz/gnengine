package dev.kolja.gnengine.gui.element;

import dev.kolja.gnengine.gui.GUIHandler;
import dev.kolja.gnengine.gui.HitBox;
import dev.kolja.gnengine.input.MouseInputHandler;
import dev.kolja.gnengine.render.Renderable;
import org.joml.Vector3f;

import java.util.List;

/**
 * Abstract component of a GUI. Has HitBox, a Renderer and provides access to the MouseInputHandler.
 */
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

    /**
     * Render logic of a component.
     */
    public abstract void render();

    /**
     * Update logic of a component.
     */
    public abstract void tick();

    /**
     * HitBox of a component.
     * @return HitBox of component
     */
    public abstract HitBox getHitBox();

    /**
     * X value of component position.
     * @return x value of component position
     */
    public int getXPos() {
        return xPos;
    }

    /**
     * Y value of component position.
     * @return y value of component position
     */
    public int getYPos() {
        return yPos;
    }

    /**
     * Width of component.
     * @return width of component
     */
    public int getWidth() {
        return width;
    }

    /**
     * Height of component
     * @return height of component
     */
    public int getHeight() {
        return height;
    }

    /**
     * New x value of component position
     * @param xPos new x value of component position
     */
    public abstract void setXPos(int xPos);

    /**
     * New y value of component position
     * @param yPos new y value of component position
     */
    public abstract void setYPos(int yPos);

    /**
     * New width of component
     * @param width new width of component
     */
    public abstract void setWidth(int width);

    /**
     * New height of component
     * @param height new height of component
     */
    public abstract void setHeight(int height);

    /**
     * New HitBox of component
     * @param hitBox new HitBox of component
     */
    public abstract void setHitBox(HitBox hitBox);
}
