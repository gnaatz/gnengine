import dev.kolja.gnengine.core.Engine;
import dev.kolja.gnengine.core.Window;
import dev.kolja.gnengine.gui.HitBox;
import dev.kolja.gnengine.gui.element.Button;
import dev.kolja.gnengine.gui.element.Container;
import dev.kolja.gnengine.render.Shader;
import dev.kolja.gnengine.render.ShaderFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EngineTest {

    private static Engine engine;
    private static Window window;

    @BeforeAll
    public static void setup() throws Exception {
        engine = Engine.gnInit();
        window = engine.createWindow("test", 100, 100);
    }

    @Test
    @DisplayName("Check Engine creation working")
    public void testEngineCreation() {
        assertNotNull(engine, "Engine should not be null");
    }

    @Test
    @DisplayName("Check window creation")
    public void testWindowCreation() {
        assertNotNull(window, "Window should not be null");
        assertNotEquals(0, window.getGLHandle(), "Window handle should not be 0");
    }

    @Test
    @DisplayName("Check container placement")
    public void testContainerPlacement() {
        Container container = new Container(10, 10, 20, 40);
        HitBox box = container.getHitBox();
        assertEquals(box.x(), container.getXPos());
        assertEquals(box.y(), container.getYPos());
        assertEquals(box.width(), container.getWidth());
        assertEquals(box.height(), container.getHeight());
    }

    @Test
    @DisplayName("Check container containing element")
    public void testContainerContaining() {
        Container container = new Container(10, 10, 20, 40);
        int padding = 5;
        Button button = new Button();
        container.addComponent(button);

        assertEquals(container.getXPos() + padding, button.getXPos());
        assertEquals(container.getYPos() + padding, button.getYPos());
    }

    @Test
    @DisplayName("Check Shader loading")
    public void checkShaderLoading() {
        String shaderV = "#version 330\n" +
                "void main() {\n" +
                "}";
        String shaderF = "#version 330\n" +
                "void main() {\n" +
                "}";
        ShaderFactory.register("test", new Shader("test", shaderV, shaderF));
        Shader shader = ShaderFactory.retrieveShader("test");
        assertNotNull(shader);
    }

    // weitere Tests sind stark Oberflächenbezogen. Hier werden manuelle Tests verwendet um bspw. die onHover-Methode
    // der Buttons zu testen.
}
