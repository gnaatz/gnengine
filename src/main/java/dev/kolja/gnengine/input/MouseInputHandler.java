package dev.kolja.gnengine.input;


import dev.kolja.gnengine.core.Engine;
import dev.kolja.gnimage.Main;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.glfw.GLFW.*;

public class MouseInputHandler {

    private static MouseInputHandler instance;

    public static void create(long window) {
        if(instance != null) {
            Engine.LOGGER.error("Tried creating a second MouseInputHandler. Returning instance.");
            return;
        }
        instance = new MouseInputHandler(window);
    }

    public static MouseInputHandler getInstance() {
        if(instance == null) {
            Engine.LOGGER.error("MouseInputHandler not created");
        }
        return instance;
    }

    private static Map<Integer, MouseButton> populateMouseButtons() {
        Map<Integer, MouseButton> map = new HashMap<>();
        map.put(GLFW_MOUSE_BUTTON_LEFT, MouseButton.LEFT_MOUSE_BUTTON);
        map.put(GLFW_MOUSE_BUTTON_RIGHT, MouseButton.RIGHT_MOUSE_BUTTON);
        return map;
    }

    private double xPos;
    private double yPos;
    private final Map<MouseButton, MouseButtonAction> buttonAction;
    private final Map<Integer, MouseButton> mouseButtons;

    private MouseInputHandler(long window) {
        buttonAction = new HashMap<>();
        mouseButtons = populateMouseButtons();
        glfwSetMouseButtonCallback(window, (win, button, action, mods) -> mouseButtonCallback(button, action));
        glfwSetCursorPosCallback(window, (win, xPos, yPos) -> mousePosCallback(xPos, yPos));
    }

    public void tick() {
        if(buttonAction.containsValue(MouseButtonAction.RELEASED)) {
            buttonAction.forEach((button, action) -> {
                if(action == MouseButtonAction.RELEASED) {
                    buttonAction.put(button, MouseButtonAction.NONE);
                }
            });
        }
    }

    public MouseButtonAction getButtonAction(MouseButton button) {
        return buttonAction.get(button);
    }

    public double getXPos() {
        return xPos;
    }

    public double getYPos() {
        return yPos;
    }

    private void mouseButtonCallback(int button, int action) {
        MouseButton key;
        if((key = mouseButtons.get(button)) != null) {
            if(action == GLFW_PRESS) {
                buttonAction.put(key, MouseButtonAction.PRESSED);
            } else if(action == GLFW_RELEASE) {
                buttonAction.put(key, MouseButtonAction.RELEASED);
            }
        }
    }

    private void mousePosCallback(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = Main.SCREEN_HEIGHT-yPos;
    }
}
