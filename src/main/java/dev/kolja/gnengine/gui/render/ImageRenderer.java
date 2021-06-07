package dev.kolja.gnengine.gui.render;

import dev.kolja.gnengine.gui.element.Image;
import dev.kolja.gnengine.model.Model;
import dev.kolja.gnengine.model.ModelFactory;
import dev.kolja.gnengine.model.TexturedModel;
import dev.kolja.gnengine.render.Framebuffer;
import dev.kolja.gnengine.render.Renderable;
import dev.kolja.gnengine.render.Shader;
import dev.kolja.gnengine.render.ShaderFactory;
import dev.kolja.gnengine.texture.ITexture;
import dev.kolja.gnengine.texture.Texture;
import dev.kolja.gnengine.texture.TextureFactory;
import dev.kolja.gnimage.Main;
import org.joml.Vector2f;
import org.joml.Vector4f;

/**
 * Renderer for an Image.
 */
public class ImageRenderer implements Renderable {

    private final Image image;
    private final Shader shader;
    private final Model model;

    private ITexture texture;

    public ImageRenderer(Image image) {
        this(image, TextureFactory.retrieveTexture(image.getTextureName()));
    }

    public ImageRenderer(Image image, ITexture texture) {
        this.image = image;
        this.shader = ShaderFactory.retrieveShader("textured");
        this.texture = texture;
        this.model = ModelFactory.retrieveModel(TexturedModel.class);
    }

    public void notifyTextureChange() {
        this.texture = TextureFactory.retrieveTexture(image.getTextureName());
    }

    @Override
    public void render() {
        shader.use();
        shader.setVec2f("screen", new Vector2f(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT));
        shader.setVec4f("transform", new Vector4f().set(image.getHitBox().asVector()));
        texture.use();
        model.draw();
    }
}
