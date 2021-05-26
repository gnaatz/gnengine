package dev.kolja.gnengine.render;

import dev.kolja.gnengine.core.Engine;

import java.util.HashMap;
import java.util.Map;

public class ShaderFactory {

    private static final Map<String, Shader> REGISTRY;

    public static void register(String name) {
        REGISTRY.put(name, new Shader(name));
    }

    static {
        REGISTRY = new HashMap<>();
        register("rounded_rect");
        register("textured");
        register("text");
        register("rect");
        register("convolutional");
    }


    public static Shader retrieveShader(String name) {
        if(!REGISTRY.containsKey(name)) {
            Engine.LOGGER.error("Shader " + name + " not registered");
        }
        return REGISTRY.get(name);
    }
}
