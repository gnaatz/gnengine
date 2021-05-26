package dev.kolja.gnengine.gui.animator;

import dev.kolja.gnengine.gui.HitBox;

public interface Animatable {
    HitBox getHitBox();
    void updateHitBox();
}
