package dev.kolja.gnengine.gui.animator;

import dev.kolja.gnengine.gui.HitBox;

/**
 * Used for example for Button animators. Should be implemented for all HitBox altering objects.
 */
public interface Animatable {
    /**
     * Returns the current HitBox of the managed object
     * @return current HitBox of managed object
     */
    HitBox getHitBox();

    /**
     * Updates the HitBox of the managed object.
     */
    void updateHitBox();
}
