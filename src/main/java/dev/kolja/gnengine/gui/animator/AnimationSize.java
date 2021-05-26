package dev.kolja.gnengine.gui.animator;

public enum AnimationSize {

    SMALL(5), MEDIUM(10), LARGE(20);

    private final int size;

    AnimationSize(int size) {
        this.size = size;
    }

    public int asInt() {
        return size;
    }
}
