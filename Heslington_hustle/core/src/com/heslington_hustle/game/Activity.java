package com.heslington_hustle.game;

import com.badlogic.gdx.math.Rectangle;

abstract class Activity {
    enum ActivityType {
        SLEEP, RECREATION, EAT, STUDY
    }
    ActivityType type;
    public Rectangle zone;

    public Activity() {
        super();
        zone = new Rectangle();
    }

    public void set(float x, float y, float width, float height) {
        zone.set(x, y, width, height);
    }
}