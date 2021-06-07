package dev.kolja.gnengine.render;

import dev.kolja.gnengine.core.Engine;

import java.util.HashMap;
import java.util.Map;

public class ShaderFactory {

    private static final Map<String, Shader> REGISTRY;

    public static void registerFromName(String name) {
        REGISTRY.put(name, Shader.fromFile(name));
    }

    public static void register(String name, Shader shader) {
        REGISTRY.put(name, shader);
    }

    static {
        REGISTRY = new HashMap<>();
        registerFromName("rounded_rect");
        registerFromName("textured");
        registerFromName("text");
        registerFromName("rect");
        registerFromName("convolutional");
    }


    public static Shader retrieveShader(String name) {
        if(!REGISTRY.containsKey(name)) {
            Engine.LOGGER.error("Shader " + name + " not registered");
        }
        return REGISTRY.get(name);
    }
}
