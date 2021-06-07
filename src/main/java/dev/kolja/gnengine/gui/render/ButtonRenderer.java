package dev.kolja.gnengine.gui.render;

import dev.kolja.gnimage.Main;
import dev.kolja.gnengine.gui.element.Button;
import dev.kolja.gnengine.model.Model;
import dev.kolja.gnengine.model.ModelFactory;
import dev.kolja.gnengine.model.SimpleModel;
import dev.kolja.gnengine.render.Renderable;
import dev.kolja.gnengine.render.Shader;
import dev.kolja.gnengine.render.ShaderFactory;
import org.joml.Vector2f;
import org.joml.Vector4f;

/**
 * Renderer for a Button.
 */
public class ButtonRenderer implements Renderable {

    private final Model model;
    private final Shader shader;
    private final Button button;

    public ButtonRenderer(Button button) {
        this.button = button;
        this.model = ModelFactory.retrieveModel(SimpleModel.class);
        this.shader = ShaderFactory.retrieveShader("rounded_rect");
    }

    @Override
    public void render() {
        shader.use();
        shader.setVec2f("screen", new Vector2f(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT));
        shader.setVec4f("transform", new Vector4f().set(button.getHitBox().asVector()));
        shader.setFloat("rounding", 0.1f);
        shader.setVec3f("color", button.getColor().asVector());
        model.draw();
    }
}
