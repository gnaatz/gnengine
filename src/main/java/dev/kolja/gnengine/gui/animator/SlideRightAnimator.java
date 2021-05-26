package dev.kolja.gnengine.gui.animator;

import dev.kolja.gnengine.gui.element.GUIComponent;

public class SlideRightAnimator extends Animator {

    private int animationState;
    private AnimationSize size;
    private boolean animating;
    private final GUIComponent component;
    private int init;

    public SlideRightAnimator(GUIComponent component) {
        this.component = component;
        animationState = 0;
        size = AnimationSize.SMALL;
    }

    public void setSize(AnimationSize size) {
        this.size = size;
    }

    @Override
    public void startAnimation() {
        animating = true;
        init = component.getXPos();
    }

    @Override
    public void stopAnimation() {
        animating = false;
        animationState = 0;
        component.setXPos(init);
    }

    @Override
    public void animate() {
        animationState = animationState == 0 ? 1 : animationState;
        if(animating && animationState < 20) {
            component.setXPos(component.getXPos() + size.asInt() / animationState);
            animationState++;
        }
    }
}
