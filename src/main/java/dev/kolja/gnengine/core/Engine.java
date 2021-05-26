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

public class Engine {

    public static final Logger LOGGER = LogManager.getLogger();

    private final Map<String, Window> windows;

    private boolean isInit;
    private static Engine instance;

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

    public Window createWindow(String title, int width, int height) {
        Window window = new Window(title, width, height);
        windows.put(title, window);
        MouseInputHandler.create(window.getGLHandle());
        KeyboardInputHandler.create(window.getGLHandle());
        tryInit();
        return window;
    }

    public void terminate() {
        windows.values().forEach(Window::close);
        glfwTerminate();
    }

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
