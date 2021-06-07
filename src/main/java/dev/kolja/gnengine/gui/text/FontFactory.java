package dev.kolja.gnengine.gui.text;

import dev.kolja.gnengine.core.Engine;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages all fonts available in the application. Per Standard a simple font named 'font' is added.
 * New fonts can be added with register. This requires a bitmap font in the resources/font folder. A bitmap font
 * requires two files:
 *  - A texture atlas (.png)
 *  - A font file containing information about the characters (.fnt)
 * The standard font can be used as reference
 */
public class FontFactory {
    private static final Map<String, Font> REGISTRY;

    /**
     * Register new font.
     * @param name name (must be same as the files) of the font
     */
    public static void register(String name) {
        REGISTRY.put(name, new Font(name));
    }

    static {
        REGISTRY = new HashMap<>();
    }

    /**
     * Has to be called to static load the Factory.
     */
    public static void init() {
        register("font");
    }

    /**
     * Returns font or null if not existing.
     * @param name name of registered font
     * @return font or null if not existing
     */
    public static Font retrieveFont(String name) {
        if(!REGISTRY.containsKey(name)) {
            Engine.LOGGER.error("Font " + name + " not registered");
        }
        return REGISTRY.get(name);
    }
}
