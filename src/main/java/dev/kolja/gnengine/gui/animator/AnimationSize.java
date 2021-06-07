package dev.kolja.gnengine.gui.animator;

/**
 * Used to quantize animation sizes. For each Small, Medium and Large, the animation size doubles, starting from 5.
 */
public enum AnimationSize {

    SMALL(5), MEDIUM(10), LARGE(20);

    private final int size;

    AnimationSize(int size) {
        this.size = size;
    }

    /**
     * AnimationSize as integer to work with in Animator.
     * @return AnimationSize as Int
     */
    public int asInt() {
        return size;
    }
}
