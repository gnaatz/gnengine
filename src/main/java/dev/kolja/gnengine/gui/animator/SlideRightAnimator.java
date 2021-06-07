package dev.kolja.gnengine.gui.animator;

import dev.kolja.gnengine.gui.element.GUIComponent;

/**
 * SlideRightAnimator alters the position of an object slightly to the right, over time. It's size can be changed.
 */
public class SlideRightAnimator extends Animator {

    private int animationState;
    private AnimationSize size;
    private boolean animating;
    private final GUIComponent component;
    private int init;

    /**
     * Animator for an animatable GUIComponent
     * @param component to-be-animated component - has to implement an Animator at some point
     */
    public SlideRightAnimator(GUIComponent component) {
        this.component = component;
        animationState = 0;
        size = AnimationSize.SMALL;
    }

    /**
     * Changes the size (the position.x increase) of the animation
     * @param size new size of the animation
     */
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
