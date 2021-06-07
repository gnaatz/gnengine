package dev.kolja.gnimage;

import dev.kolja.gnengine.color.Color;
import dev.kolja.gnengine.core.Engine;
import dev.kolja.gnengine.core.Window;
import dev.kolja.gnengine.gui.GUIHandler;
import dev.kolja.gnengine.gui.HitBox;
import dev.kolja.gnengine.gui.element.*;
import dev.kolja.gnengine.input.KeyboardInputHandler;
import dev.kolja.gnengine.input.MouseInputHandler;
import dev.kolja.gnengine.model.Model;
import dev.kolja.gnengine.model.ModelFactory;
import dev.kolja.gnengine.model.SimpleModel;
import dev.kolja.gnengine.model.TexturedModel;
import dev.kolja.gnengine.render.Framebuffer;
import dev.kolja.gnengine.render.Shader;
import dev.kolja.gnengine.render.ShaderFactory;
import dev.kolja.gnengine.texture.Texture;
import dev.kolja.gnengine.texture.TextureFactory;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import javax.jws.WebParam;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Main {

    public static final String TITLE = "test";
    public static final int SCREEN_WIDTH = 1600;
    public static final int SCREEN_HEIGHT = 900;

    private final static GUIHandler guiHandler = GUIHandler.getInstance();

    public static void main(String[] args) {
        Engine engine = Engine.gnInit();
        Window window = engine.createWindow(TITLE, SCREEN_WIDTH, SCREEN_HEIGHT);
        TextureFactory.register("texture", "sheep", Texture.Type.RGBA);
        Texture texture = TextureFactory.retrieveTexture("sheep");

        window.setBackgroundColor(new Color(0.2f, 0.2f, 0.2f));

        float xPos = 50;
        float yPos = 50;
        float width = 1000;
        float height = 800;
        Container container = new Container();
        container.setHitBox(new HitBox(xPos, yPos, width + 20, height + 20));
        container.setColor(new Color(0.1f, 0.1f, 0.1f));
        container.setRounding(0.01f);
        TextButton button = new TextButton(new Button(SCREEN_WIDTH - 300, 140), "Apply Filter");
        TextField textField1 = new TextField(new Container(SCREEN_WIDTH - 420, 420, 100, 100));
        TextField textField2 = new TextField(new Container(SCREEN_WIDTH - 310, 420, 100, 100));
        TextField textField3 = new TextField(new Container(SCREEN_WIDTH - 200, 420, 100, 100));
        TextField textField4 = new TextField(new Container(SCREEN_WIDTH - 420, 310, 100, 100));
        TextField textField5 = new TextField(new Container(SCREEN_WIDTH - 310, 310, 100, 100));
        TextField textField6 = new TextField(new Container(SCREEN_WIDTH - 200, 310, 100, 100));
        TextField textField7 = new TextField(new Container(SCREEN_WIDTH - 420, 200, 100, 100));
        TextField textField8 = new TextField(new Container(SCREEN_WIDTH - 310, 200, 100, 100));
        TextField textField9 = new TextField(new Container(SCREEN_WIDTH - 200, 200, 100, 100));

        Framebuffer buffer = new Framebuffer((int) width, (int) height);
        Image image = new Image(buffer);
        image.setHitBox(new HitBox(xPos + 10, yPos + 10, width, height));

        Model model = ModelFactory.retrieveModel(SimpleModel.class);
        texture.use();
        Shader shader = ShaderFactory.retrieveShader("convolutional");
        shader.use();
        shader.setVec1fv("kernel", new float[]{0, 0, 0, 0, 1, 0, 0, 0, 0});
        shader.setVec4f("transform", new Vector4f(0, 0, width, height));
        shader.setVec2f("screen", new Vector2f(SCREEN_WIDTH, SCREEN_HEIGHT));
        buffer.renderToFramebuffer(model::draw);

        button.setButtonCallback(button1 -> {
            float[] kernel = new float[9];
            kernel[0] = Float.parseFloat(textField1.getText());
            kernel[1] = Float.parseFloat(textField2.getText());
            kernel[2] = Float.parseFloat(textField3.getText());
            kernel[3] = Float.parseFloat(textField4.getText());
            kernel[4] = Float.parseFloat(textField5.getText());
            kernel[5] = Float.parseFloat(textField6.getText());
            kernel[6] = Float.parseFloat(textField7.getText());
            kernel[7] = Float.parseFloat(textField8.getText());
            kernel[8] = Float.parseFloat(textField9.getText());
            texture.use();
            shader.use();
            shader.setVec1fv("kernel", kernel);
            shader.setVec4f("transform", new Vector4f(0, 0, width, height));
            shader.setVec2f("screen", new Vector2f(SCREEN_WIDTH, SCREEN_HEIGHT));
            buffer.renderToFramebuffer(model::draw);
            Engine.LOGGER.debug(kernel[0]);
        });

        while(!glfwWindowShouldClose(window.getGLHandle())) {
            KeyboardInputHandler.getInstance().clearCallbacks();
            window.pollEvents();

            guiHandler.tick();
            MouseInputHandler.getInstance().tick();

            glClear(GL_COLOR_BUFFER_BIT);
            guiHandler.render();

            /*buffer.renderToFramebuffer(guiHandler::render);
            shader.use();
            shader.setVec4f("transform", new Vector4f(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT));
            shader.setVec2f("screen", new Vector2f(SCREEN_WIDTH, SCREEN_HEIGHT));
            buffer.use();
            model.draw();*/

            window.swapBuffers();
        }

        engine.terminate();
    }
}
