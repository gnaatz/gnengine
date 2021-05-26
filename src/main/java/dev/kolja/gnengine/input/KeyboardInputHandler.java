package dev.kolja.gnengine.input;

import dev.kolja.gnengine.core.Engine;
import org.lwjgl.system.CallbackI;

import java.util.*;

import static org.lwjgl.glfw.GLFW.*;

public class KeyboardInputHandler {

    private static KeyboardInputHandler instance;

    public static void create(long window) {
        if(instance != null) {
            Engine.LOGGER.error("Tried creating a second MouseInputHandler. Returning instance.");
            return;
        }
        instance = new KeyboardInputHandler(window);
    }

    public static KeyboardInputHandler getInstance() {
        if(instance == null) {
            Engine.LOGGER.error("MouseInputHandler not created");
        }
        return instance;
    }


    private final Map<String, ICharCallback> charCallbacks;
    private final Map<Integer, HashMap<String, IKeyCallback>> keyCallbacks;
    private final Stack<String> unregisterQueueChars;
    private final HashMap<Integer, Stack<String>> unregisterQueueKeys;

    private KeyboardInputHandler(long window) {
        this.charCallbacks = new HashMap<>();
        this.keyCallbacks = new HashMap<>();
        this.unregisterQueueChars = new Stack<>();
        this.unregisterQueueKeys = new HashMap<>();
        glfwSetKeyCallback(window, this::keyCallback);
        glfwSetCharCallback(window, this::charCallback);
    }

    public void clearCallbacks() {
        for(String name : unregisterQueueChars) {
            charCallbacks.remove(name);
        }
        unregisterQueueChars.clear();
        unregisterQueueKeys.forEach((integer, strings) -> strings.forEach(s -> keyCallbacks.get(integer).remove(s)));
        unregisterQueueKeys.clear();
    }

    public void registerKeyCallback(String name, Key key, IKeyCallback callbackFn) {
        if(!keyCallbacks.containsKey(key.code)) {
            keyCallbacks.put(key.code, new HashMap<>());
        }
        keyCallbacks.get(key.code).put(name, callbackFn);
    }

    public void registerCharCallback(String name, ICharCallback callbackFn) {
        charCallbacks.put(name, callbackFn);
    }

    public void unregisterCharCallback(String name) {
        unregisterQueueChars.add(name);
    }

    public void unregisterKeyCallback(String name, Key key) {
        if(keyCallbacks.containsKey(key.code)) {
            if(!unregisterQueueKeys.containsKey(key.code)) {
                unregisterQueueKeys.put(key.code, new Stack<>());
            }
            unregisterQueueKeys.get(key.code).add(name);
        }
    }

    private void charCallback(long win, int code) {
        for(ICharCallback callback : charCallbacks.values()) {
            callback.callbackFn((char) code);
        }
    }

    private void keyCallback(long win, int key, int code, int action, int mods) {
        if(action == GLFW_PRESS) {
            if(keyCallbacks.containsKey(key)) {
                keyCallbacks.get(key).values().forEach(IKeyCallback::callback);
            }
        }
    }

    public enum Key {
        DEL(GLFW_KEY_DELETE),
        BACKSPACE(GLFW_KEY_BACKSPACE),
        ENTER(GLFW_KEY_ENTER),
        ESC(GLFW_KEY_ESCAPE);

        public int code;

        Key(int code) {
            this.code = code;
        }
    }
}
