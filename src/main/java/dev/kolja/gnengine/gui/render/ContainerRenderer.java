package dev.kolja.gnengine.gui.render;

import dev.kolja.gnimage.Main;
import dev.kolja.gnengine.gui.element.Container;
import dev.kolja.gnengine.model.Model;
import dev.kolja.gnengine.model.ModelFactory;
import dev.kolja.gnengine.model.SimpleModel;
import dev.kolja.gnengine.render.Renderable;
import dev.kolja.gnengine.render.Shader;
import dev.kolja.gnengine.render.ShaderFactory;
import org.joml.Vector2f;
import org.joml.Vector4f;

public class ContainerRenderer implements Renderable {
    private final Model model;
    private final Shader shader;
    private final Container container;

    public ContainerRenderer(Container container) {
        this.container = container;
        this.model = ModelFactory.retrieveModel(SimpleModel.class);
        this.shader = ShaderFactory.retrieveShader("rounded_rect");
    }

    @Override
    public void render() {
        shader.use();
        shader.setFloat("rounding", container.getRounding());
        shader.setVec2f("screen", new Vector2f(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT));
        shader.setVec4f("transform", new Vector4f().set(container.getHitBox().asVector()));
        shader.setVec3f("color", container.getColor().asVector());
        model.draw();
    }
}
