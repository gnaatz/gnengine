package dev.kolja.gnengine.gui.animator;

/**
 * Abstract Animator
 */
public abstract class Animator {
    /**
     * Called if an animatable object wants to start its animation.
     */
    public abstract void startAnimation();

    /**
     * Called if an animatable object wants to stop its animation.
     */
    public abstract void stopAnimation();

    /**
     * Called if an animatable object wants to do an animation step.
     */
    public abstract void animate();
}
