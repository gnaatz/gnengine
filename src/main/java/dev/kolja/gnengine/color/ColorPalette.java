package dev.kolja.gnengine.color;

import dev.kolja.gnengine.core.Engine;

import java.util.HashMap;
import java.util.Map;

public class ColorPalette {
    private final Map<String, Color> colors;

    public ColorPalette() {
        this.colors = new HashMap<>();
    }

    public void addColor(String name, Color color) {
        if(colors.containsKey(name)) {
            Engine.LOGGER.warn("Overriding color " + name);
        } else if(colors.containsValue(color)) {
            Engine.LOGGER.warn("Color r(" + color.r() + "), g(" + color.g() + "), b(" + color.b() + ") already exists");
        }
        colors.put(name, color);
    }

    public void removeColor(String name) {
        if(!colors.containsKey(name)) {
            Engine.LOGGER.warn("Color " + name + " doesn't exist");
            return;
        }
        colors.remove(name);
    }

    public Color get(String name) {
        Color color = colors.get(name);
        if(!colors.containsKey(name)) {
            Engine.LOGGER.warn("Color " + name + " doesn't exist");
            color = new Color(0.0f, 0.0f, 0.0f);
        }
        return color;
    }
}
