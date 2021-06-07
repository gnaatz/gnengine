package dev.kolja.gnengine.texture;

import dev.kolja.gnengine.core.Engine;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.stb.STBImage.stbi_set_flip_vertically_on_load;

public class TextureFactory {

    private static final Map<String, Texture> REGISTRY = new HashMap<>();

    public static void register(String prefix, String name, Texture.Type type) {
        REGISTRY.put(name, new Texture(prefix, name, type));
    }

    public static void init() {
        stbi_set_flip_vertically_on_load(true);
    }

    public static Texture retrieveTexture(String name) {
        if(!REGISTRY.containsKey(name)) {
            register("texture", name, Texture.Type.RGBA);
        }
        return REGISTRY.get(name);
    }
}
