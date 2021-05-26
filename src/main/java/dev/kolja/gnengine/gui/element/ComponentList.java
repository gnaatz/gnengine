package dev.kolja.gnengine.gui.element;

import java.util.ArrayList;
import java.util.List;

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

    public abstract void addComponent(GUIComponent component);

    public abstract void removeComponent(GUIComponent component);
}
