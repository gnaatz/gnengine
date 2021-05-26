package dev.kolja.gnengine.gui;

import org.joml.Vector4d;

public class HitBox {

    protected double x;
    protected double y;
    protected double width;
    protected double height;

    public HitBox(Vector4d hitBox) {
        this.x = hitBox.x;
        this.y = hitBox.y;
        this.width = hitBox.z;
        this.height = hitBox.w;
    }

    public HitBox(double xPos, double yPos, double width, double height) {
        this(new Vector4d(xPos, yPos, width, height));
    }

    public HitBox() {
        this(new Vector4d());
    }

    public HitBox(HitBox hitBox) {
        this.x = hitBox.x;
        this.y = hitBox.y;
        this.width = hitBox.width;
        this.height = hitBox.height;
    }

    public boolean isHit(double xPos, double yPos) {
        double xSignLeft = this.x - xPos;
        double ySignTop = this.y - yPos;
        double xSignRight = this.x + this.width - xPos;
        double ySignBottom = this.y + this.height - yPos;

        return xSignLeft < 0 && xSignRight > 0 && ySignTop < 0 && ySignBottom > 0;
    }

    public Vector4d asVector() {
        return new Vector4d(x, y, width, height);
    }

    public void setHitBox(Vector4d hitBox) {
        this.x = hitBox.x;
        this.y = hitBox.y;
        this.width = hitBox.z;
        this.height = hitBox.w;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public double width() {
        return width;
    }

    public double height() {
        return height;
    }

    public void updateX(double x) {
        this.x = x;
    }

    public void updateY(double y) {
        this.y = y;
    }

    public void updateWidth(double width) {
        this.width = width;
    }

    public void updateHeight(double height) {
        this.height = height;
    }
}
