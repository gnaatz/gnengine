package dev.kolja.gnengine.texture;

import dev.kolja.gnengine.core.Engine;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.stbi_load;

public class Texture implements ITexture {

    private final int ID;
    private final int width;
    private final int height;

    public Texture(String name, Type type) {
        this("", name, type);
    }

    public Texture(String prefix, String name, Type type) {
        int[] width = new int[1], height = new int[1], nrChnl = new int[1];
        ByteBuffer data = stbi_load("src/main/resources/" + prefix + "/" + name + ".png", width, height, nrChnl, 0);

        Engine.LOGGER.debug(name + " " + nrChnl[0]);

        this.width = width[0];
        this.height = height[0];

        if(data == null) {
            Engine.LOGGER.error("Texture [" + prefix + "/" + name + ".png] could not be loaded");
        }

        ID = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, ID);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        glTexImage2D(GL_TEXTURE_2D, 0, type.type, width[0], height[0], 0, type.type, GL_UNSIGNED_BYTE, data);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void use() {
        glBindTexture(GL_TEXTURE_2D, ID);
    }

    public enum Type {
        RGB(GL_RGB), RGBA(GL_RGBA), RGBA16(GL_RGBA16), RGB8(GL_RGB8), R(GL_ALPHA);

        public int type;

        Type(int type) {
            this.type = type;
        }
    }
}
