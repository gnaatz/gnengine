package dev.kolja.gnengine.render;

import dev.kolja.gnengine.color.Color;
import dev.kolja.gnengine.core.Engine;
import dev.kolja.gnengine.texture.ITexture;
import dev.kolja.gnimage.Main;

import static org.lwjgl.opengl.GL30.*;

public class Framebuffer implements ITexture {

    private final int id;
    private final int textureId;

    private final int width;
    private final int height;

    private Color clearColor;

    public Framebuffer(int width, int height) {
        clearColor = new Color();

        this.width = width;
        this.height = height;

        id = glGenFramebuffers();
        textureId = glGenTextures();

        glBindFramebuffer(GL_FRAMEBUFFER, id);
        glBindTexture(GL_TEXTURE_2D, textureId);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, (double[]) null);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D, textureId, 0);

        if(glCheckFramebufferStatus(GL_FRAMEBUFFER) != GL_FRAMEBUFFER_COMPLETE) {
            Engine.LOGGER.error("Framebuffer creation error.");
        }
        glBindFramebuffer(GL_FRAMEBUFFER, 0);
    }

    public Color getClearColor() {
        return clearColor;
    }

    public void setClearColor(Color color) {
        this.clearColor = color;
        glBindFramebuffer(GL_FRAMEBUFFER, id);
        glClearColor(color.r(), color.g(), color.b(), 1f);
        glBindFramebuffer(GL_FRAMEBUFFER, 0);
    }

    public void renderToFramebuffer(Renderable renderer) {
        glBindFramebuffer(GL_FRAMEBUFFER, id);
        glClear(GL_COLOR_BUFFER_BIT);
        renderer.render();
        glBindFramebuffer(GL_FRAMEBUFFER, 0);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void use() {
        glBindTexture(GL_TEXTURE_2D, textureId);
    }
}
