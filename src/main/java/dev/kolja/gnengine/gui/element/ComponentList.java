package dev.kolja.gnengine.gui.element;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract list for GUIComponents. Used to properly keep track of sorted children.
 */
abstract public class ComponentList {

    final List<GUIComponent> components;
    final ParentComponent parent;

    int spacing;

    ComponentList(ParentComponent parent) {
        this(parent, 5);
    }

    ComponentList(ParentComponent parent, int spacing) {
        components = new ArrayList<>();
        this.parent = parent;
        this.spacing = spacing;
    }

    /**
     * Adds a new component at the end of the list.
     * @param component new component to be added
     */
    public abstract void addComponent(GUIComponent component);

    /**
     * Removes a component from the list.
     * @param component component to be removed
     */
    public abstract void removeComponent(GUIComponent component);
}
