package dev.kolja.gnengine.gui.render;

import dev.kolja.gnengine.gui.element.IText;
import dev.kolja.gnimage.Main;
import dev.kolja.gnengine.gui.text.TextCharacter;
import dev.kolja.gnengine.model.Model;
import dev.kolja.gnengine.model.ModelFactory;
import dev.kolja.gnengine.model.SimpleModel;
import dev.kolja.gnengine.render.Renderable;
import dev.kolja.gnengine.render.Shader;
import dev.kolja.gnengine.render.ShaderFactory;
import org.joml.Vector2f;
import org.joml.Vector4f;

/**
 * Renderer for simple Text.
 */
public class TextRenderer implements Renderable {

    private final Model model;
    private final Shader textShader;
    private final Shader cursorShader;
    private final IText text;

    public TextRenderer(IText text) {
        this.model = ModelFactory.retrieveModel(SimpleModel.class);
        this.textShader = ShaderFactory.retrieveShader("text");
        this.cursorShader = ShaderFactory.retrieveShader("rect");
        this.text = text;
    }

    @Override
    public void render() {
        textShader.use();
        text.getFont().use();
        textShader.setVec2f("screen", new Vector2f(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT));
        textShader.setVec3f("color", text.getTextColor().asVector());
        textShader.setVec4f("textPos", new Vector4f().set(text.getHitBox().asVector()));
        TextCharacter[] chars = text.getCharHitBoxes();
        int length = text.includesCursor() ? chars.length - 1 : chars.length;
        for(int i = 0; i < length; i++) {
            TextCharacter character = chars[i];
            textShader.setVec2f("textureXY", character.getGLTextureCoords());
            textShader.setVec2f("textureStride", character.getGLTextureStride());
            textShader.setVec4f("transform", new Vector4f().set(character.asVector()));
            model.draw();
        }
        if(text.includesCursor()) {
            cursorShader.use();
            cursorShader.setVec2f("screen", new Vector2f(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT));
            cursorShader.setVec3f("color", text.getTextColor().asVector());
            Vector4f transform = new Vector4f().set(chars[chars.length - 1].asVector());
            transform.x = (float) (transform.x + text.getHitBox().asVector().x) + 1f;
            transform.y = (float) text.getHitBox().asVector().y + 6f;
            transform.z = 2.0f;
            transform.w = transform.w - 4f;
            cursorShader.setVec4f("transform", transform);
            model.draw();
        }
    }
}
