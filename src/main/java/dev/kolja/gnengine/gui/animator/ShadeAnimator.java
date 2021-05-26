package dev.kolja.gnengine.gui.animator;

import dev.kolja.gnengine.color.Color;
import dev.kolja.gnengine.gui.element.IColoredComponent;

public class ShadeAnimator extends Animator {
    private int animationState;
    private boolean animating;
    private final IColoredComponent component;

    public ShadeAnimator(IColoredComponent component) {
        this.component = component;
        animationState = 0;
    }

    @Override
    public void startAnimation() {
        animating = true;
    }

    @Override
    public void stopAnimation() {
        animating = false;
        animationState = 0;
        component.setColor(new Color(component.getColor().asVector().add(0.2f, 0.2f, 0.2f)));
    }

    @Override
    public void animate() {
        animationState = animationState == 0 ? 1 : animationState;
        if(animating && animationState < 2) {
            component.setColor(new Color(component.getColor().asVector().add(-0.2f, -0.2f, -0.2f)));
            animationState++;
        }
    }
}
