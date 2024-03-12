package com.heslington_hustle.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;

abstract class Activity extends RectangleMapObject {
    enum ActivityType {
        SLEEP, RECREATION, EAT, STUDY
    }
    ActivityType type;

    public Activity() {
        super();
    }
}