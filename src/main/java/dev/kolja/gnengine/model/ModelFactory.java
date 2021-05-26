package dev.kolja.gnengine.model;

import dev.kolja.gnengine.core.Engine;

import java.util.HashMap;
import java.util.Map;

public class ModelFactory {

    private static final Map<Class, Model> REGISTRY;

    static {
        REGISTRY = new HashMap<>();
        SimpleModel.create();
        TexturedModel.create();
    }


    static void register(Class modelClass, Model model) {
        REGISTRY.put(modelClass, model);
    }

    public static Model retrieveModel(Class modelClass) {
        if(!REGISTRY.containsKey(modelClass)) {
            Engine.LOGGER.error("Model " + modelClass + " not registered");
        }
        return REGISTRY.get(modelClass);
    }
}
