package dev.kolja.gnengine.core;

import dev.kolja.gnengine.gui.text.FontFactory;
import dev.kolja.gnengine.input.KeyboardInputHandler;
import dev.kolja.gnengine.input.MouseInputHandler;
import dev.kolja.gnengine.texture.TextureFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFWErrorCallback;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Engine is the base class of gnengine. To use its features, use gnInit(). Otherwise it won't (!) work.
 * The Engine class also provides a Logger, which can be used for engine related logging.
 * This class is responsible for the window management.
 */
public class Engine {

    public static final Logger LOGGER = LogManager.getLogger();

    private final Map<String, Window> windows;

    private boolean isInit;
    private static Engine instance;

    /**
     * Sets up a new Engine, if there is none existing already. Don't use this twice.
     * @return returns a new engine or the old one with an error log
     */
    public static Engine gnInit() {
        if(instance != null) {
            LOGGER.error("Engine is already initialized.");
            return instance;
        }
        instance = new Engine();
        return instance;
    }

    private Engine() {
        isInit = false;
        windows = new HashMap<>();

        if(!glfwInit()) {
            LOGGER.error("GLFW could not be initialized");
            return;
        }
        LOGGER.info("GLFW initialized");
        glfwSetErrorCallback((code, msg) -> LOGGER.error("GLFW Error: " + GLFWErrorCallback.getDescription(msg)));
    }

    /**
     * Creates a new Window. The window is shown instantly after method call. Initializes both MouseInputHandler and
     * KeyboardInputHandler for the window
     * @param title Title of the window (shown at the top of the window)
     * @param width width of the window in pixels
     * @param height height of the window in pixels
     * @return returns the newly created window instance
     */
    public Window createWindow(String title, int width, int height) {
        Window window = new Window(title, width, height);
        windows.put(title, window);
        MouseInputHandler.create(window.getGLHandle());
        KeyboardInputHandler.create(window.getGLHandle());
        tryInit();
        return window;
    }

    /**
     * Use this to close existing windows and terminate the engine.
     */
    public void terminate() {
        windows.values().forEach(Window::close);
        glfwTerminate();
    }

    /**
     * Returns the instance of the window with the given title.
     * @param title title of the requested window
     * @return returns the window or null
     */
    public Window getWindow(String title) {
        if(!windows.containsKey(title)) {
            LOGGER.error("No such window: " + title);
        }
        return windows.get(title);
    }

    private void tryInit() {
        if(!isInit) {
            TextureFactory.init();
            FontFactory.init();
            isInit = true;
        }
    }
}
