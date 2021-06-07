package dev.kolja.gnengine.color;

import org.joml.Vector3f;

/**
 * Color wraps a Vector3f for easier color management.
 * A color can be created from r, g, b values or a vector containing those.
 */
public class Color {
    private final Vector3f color;

    public Color(Vector3f color) {
        this.color = new Vector3f().set(color);
    }

    public Color(float r, float g, float b) {
        this(new Vector3f(r, g, b));
    }

    /**
     * Creates color black
     */
    public Color() {
        this(new Vector3f(0f, 0f, 0f));
    }

    public void setColor(Vector3f value) {
        color.set(value);
    }

    public void r(float value) {
        color.x = value;
    }

    public void g(float value) {
        color.y = value;
    }

    public void b(float value) {
        color.z = value;
    }

    public Vector3f asVector() {
        return new Vector3f().set(color);
    }

    public float r() {
        return color.x;
    }

    public float g() {
        return color.y;
    }

    public float b() {
        return color.z;
    }
}
