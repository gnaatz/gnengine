package dev.kolja.gnengine.gui.text;

import dev.kolja.gnengine.core.Engine;

import java.util.HashMap;
import java.util.Map;

public class FontFactory {
    private static final Map<String, Font> REGISTRY;

    public static void register(String name) {
        REGISTRY.put(name, new Font(name));
    }

    static {
        REGISTRY = new HashMap<>();
    }

    public static void init() {
        register("font");
    }

    public static Font retrieveFont(String name) {
        if(!REGISTRY.containsKey(name)) {
            Engine.LOGGER.error("Font " + name + " not registered");
        }
        return REGISTRY.get(name);
    }
}
