package dev.kolja.gnengine.gui.element;

import dev.kolja.gnengine.color.Color;
import dev.kolja.gnengine.core.Engine;
import dev.kolja.gnengine.gui.*;
import dev.kolja.gnengine.gui.render.ContainerRenderer;

/**
 * Solid color object which can be used to contain further objects. Implements a component list.
 */
public class Container extends ParentComponent implements IColoredComponent {

    private float rounding;
    private ComponentList list;

    /**
     * Creates new container
     * @param xPos x value of the container position
     * @param yPos y value of the container position
     * @param width width of the container
     * @param height height of the container
     * @param padding padding for included objects
     * @param color color of the container
     */
    public Container(int xPos, int yPos, int width, int height, int padding, Color color) {
        super();
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.padding = padding;
        this.hitBox = new HitBox(xPos, yPos, width, height);
        this.renderer = new ContainerRenderer(this);
        this.rounding = 0.1f;
        this.color = color;
    }

    /**
     * Creates new container
     * @param xPos x value of the container position
     * @param yPos y value of the container position
     * @param width width of the container
     * @param height height of the container
     */
    public Container(int xPos, int yPos, int width, int height) {
        this(xPos, yPos, width, height, 5, new Color(1.0f, 1.0f, 1.0f));
    }

    /**
     *
     * @param xPos x value of the container position
     * @param yPos y value of the container position
     */
    public Container(int xPos, int yPos) {
        this(xPos, yPos, 200, 40);
    }

    /**
     * Creates new container at 0, 0
     */
    public Container() {
        this(0, 0);
    }

    @Override
    public void render() {
        renderer.render();
    }

    @Override
    public void tick() {

    }

    @Override
    int getPadding() {
        return padding;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public HitBox getHitBox() {
        return hitBox;
    }

    /**
     * Sets direction of the included components.
     * @param axis axis or direction of components
     */
    public void setAxis(ContainerAxis axis) {
        if(axis == ContainerAxis.HORIZONTAL) {
            list = new HorizontalComponentList(this);
        } /*else if(axis == ContainerAxis.VERTICAL) {
            list = new VerticalComponentList(this);
        }*/
    }

    /**
     * Adds a new component to the container.
     * @param component component to be added
     */
    public void addComponent(GUIComponent component) {
        if(list == null) {
            Engine.LOGGER.warn("ContainerAxis not set properly. Defaulting to HORIZONTAL");
            list = new HorizontalComponentList(this);
        }
        list.addComponent(component);
    }

    /**
     * Sets the amount of corner rounding
     * @param rounding value of corner rounding (0 < rounding < 1)
     */
    public void setRounding(float rounding) {
        this.rounding = rounding;
    }

    /**
     * Sets the amount of padding in pixels.
     * @param padding new amount of padding
     */
    public void setPadding(int padding) {
        this.padding = padding;
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

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void setHitBox(HitBox hitBox) {
        this.hitBox = hitBox;
        this.xPos = (int) hitBox.x();
        this.yPos = (int) hitBox.y();
        this.width = (int) hitBox.width();
        this.height = (int) hitBox.height();
    }

    /**
     * Returns the amount of corner rounding currently applied.
     * @return amount of corner rounding
     */
    public float getRounding() {
        return rounding;
    }
}
