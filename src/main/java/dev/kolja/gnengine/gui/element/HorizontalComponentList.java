package dev.kolja.gnengine.gui.element;

import dev.kolja.gnengine.gui.HitBox;

public class HorizontalComponentList extends ComponentList {

    HorizontalComponentList(ParentComponent parent) {
        super(parent);
    }

    @Override
    public void addComponent(GUIComponent component) {
        HitBox hitBox = new HitBox();
        hitBox.updateX(parent.xPos + parent.padding);
        hitBox.updateWidth(parent.width - 2 * parent.padding);
        if(components.size() < 1) {
            hitBox.updateY(parent.yPos + parent.padding);
        } else {
            GUIComponent last = components.get(components.size() - 1);
            hitBox.updateY(last.yPos + last.height + spacing);
        }
        hitBox.updateHeight(component.height);
        component.setHitBox(hitBox);
        component.parent = parent;
        components.add(component);
    }

    @Override
    public void removeComponent(GUIComponent component) {

    }
}
