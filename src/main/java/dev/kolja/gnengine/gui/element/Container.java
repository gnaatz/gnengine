package dev.kolja.gnengine.gui.element;

import dev.kolja.gnengine.color.Color;
import dev.kolja.gnengine.core.Engine;
import dev.kolja.gnengine.gui.*;
import dev.kolja.gnengine.gui.render.ContainerRenderer;
import org.joml.Vector3f;

public class Container extends ParentComponent implements IColoredComponent {

    private float rounding;
    private ComponentList list;

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

    public Container(int xPos, int yPos, int width, int height) {
        this(xPos, yPos, width, height, 5, new Color(1.0f, 1.0f, 1.0f));
    }

    public Container(int xPos, int yPos) {
        this(xPos, yPos, 200, 40);
    }

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

    public void setAxis(ContainerAxis axis) {
        if(axis == ContainerAxis.HORIZONTAL) {
            list = new HorizontalComponentList(this);
        } /*else if(axis == ContainerAxis.VERTICAL) {
            list = new VerticalComponentList(this);
        }*/
    }

    public void addComponent(GUIComponent component) {
        if(list == null) {
            Engine.LOGGER.warn("ContainerAxis not set properly. Defaulting to HORIZONTAL");
            list = new HorizontalComponentList(this);
        }
        list.addComponent(component);
    }

    public void setRounding(float rounding) {
        this.rounding = rounding;
    }

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

    public float getRounding() {
        return rounding;
    }
}
