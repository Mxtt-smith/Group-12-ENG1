package com.heslington_hustle.game;

import com.badlogic.gdx.math.Rectangle;

public class Activity {

    public static float EnergyUse;
    public static float TimeUse;
    public enum ActivityType {
        SLEEP, RECREATION, EAT, STUDY
    }
    public static ActivityType type;
    public Rectangle zone;

    public Activity() {
        super();
        zone = new Rectangle();
    }

    public void set(float x, float y, float width, float height) {
        zone.set(x, y, width, height);
    }
}