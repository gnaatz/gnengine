package dev.kolja.gnengine.gui;

import dev.kolja.gnengine.gui.element.GUIComponent;
import dev.kolja.gnengine.render.Renderable;

import java.util.LinkedList;
import java.util.List;

public class GUIHandler {

    private static GUIHandler guiHandler;

    private final List<GUIComponent> components;

    public static GUIHandler getInstance() {
        if(guiHandler == null) {
            guiHandler = new GUIHandler();
        }
        return guiHandler;
    }

    private GUIHandler() {
        components = new LinkedList<>();
    }

    public void addElement(GUIComponent component) {
        components.add(component);
    }

    public void render() {
        for(GUIComponent component : components) {
            component.render();
        }
    }

    public void tick() {
        for(GUIComponent component : components) {
            component.tick();
        }
    }
}
