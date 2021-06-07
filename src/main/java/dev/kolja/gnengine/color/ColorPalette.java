package dev.kolja.gnengine.color;

import dev.kolja.gnengine.core.Engine;

import java.util.HashMap;
import java.util.Map;

/**
 * ColorPalettes hold named Colors which make for consistent use in the Application
 */
public class ColorPalette {
    private final Map<String, Color> colors;

    /**
     * Initializes a new palette
     */
    public ColorPalette() {
        this.colors = new HashMap<>();
    }

    /**
     * Adds a color to the palette
     * @param name name of the color
     * @param color definition of the color
     */
    public void addColor(String name, Color color) {
        if(colors.containsKey(name)) {
            Engine.LOGGER.warn("Overriding color " + name);
        } else if(colors.containsValue(color)) {
            Engine.LOGGER.warn("Color r(" + color.r() + "), g(" + color.g() + "), b(" + color.b() + ") already exists");
        }
        colors.put(name, color);
    }

    /**
     * Removes a color from the palette
     * @param name name of the color to be removed
     */
    public void removeColor(String name) {
        if(!colors.containsKey(name)) {
            Engine.LOGGER.warn("Color " + name + " doesn't exist");
            return;
        }
        colors.remove(name);
    }

    /**
     * Returns the color
     * @param name name of the color
     * @return color or black if not existing; prompts a warning
     */
    public Color get(String name) {
        Color color = colors.get(name);
        if(!colors.containsKey(name)) {
            Engine.LOGGER.warn("Color " + name + " doesn't exist");
            color = new Color(0.0f, 0.0f, 0.0f);
        }
        return color;
    }
}
