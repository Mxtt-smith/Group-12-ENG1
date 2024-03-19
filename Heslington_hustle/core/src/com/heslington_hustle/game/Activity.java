package com.heslington_hustle.game;

import com.badlogic.gdx.math.Rectangle;

public class Activity {
    String description;
    float energy;
    float timeUse;
    public enum ActivityType { EAT, RECREATION, SLEEP, STUDY }
    Rectangle zone;
    ActivityType type;

    public Activity(float energy, float timeUse, ActivityType type, String description) {
        zone = new Rectangle();
        this.energy = energy;
        this.timeUse = timeUse;
        this.type = type;
        this.description = description;
    }

    // Set the zone for which the player can perform the activity
    public void set(float x, float y, float width, float height) {
        zone.set(x, y, width, height);
    }

    public ActivityType getType() {
        return type;
    }

    // Getters and Setters for class attributes
    public float getEnergy() {
        return energy;
    }

    public void setEnergy(float energy) {
        this.energy = energy;
    }

    public float getTimeUse() {
        return timeUse;
    }

    public void setTimeUse(float timeUse) {
        this.timeUse = timeUse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Rectangle getZone() {
        return zone;
    }
}