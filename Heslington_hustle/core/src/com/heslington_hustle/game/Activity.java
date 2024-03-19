package com.heslington_hustle.game;

import com.badlogic.gdx.math.Rectangle;

/**
 * The Activity class represents an activity that can be performed by the player.
 * Each activity has attributes such as energy required, time duration, type, description, and a zone where
 * the activity can be performed.
 */
public class Activity {
    /** Description of the activity. */
    String description;
    /** Energy required for the activity. */
    float energy;
    /** Time required for the activity. */
    float timeUse;
    /** Type of the activity. */
    public enum ActivityType { EAT, RECREATION, SLEEP, STUDY }
    /** Zone where the activity can be performed. */
    Rectangle zone;
    /** Type of the activity. */
    ActivityType type;

    /**
     * Constructs an activity with the specified energy, time duration, type, and description.
     *
     * @param energy the energy required for the activity
     * @param timeUse the time duration of the activity
     * @param type the type of the activity
     * @param description the description of the activity
     */

    public Activity(float energy, float timeUse, ActivityType type, String description) {
        zone = new Rectangle();
        this.energy = energy;
        this.timeUse = timeUse;
        this.type = type;
        this.description = description;
    }

    /**
     * Sets the zone where the player can perform the activity.
     *
     * @param x the x-coordinate of the zone
     * @param y the y-coordinate of the zone
     * @param width the width of the zone
     * @param height the height of the zone
     */
    public void set(float x, float y, float width, float height) {
        zone.set(x, y, width, height);
    }

    /**
     * Returns the type of the activity.
     *
     * @return the type of the activity
     */
    public ActivityType getType() {
        return type;
    }

    /**
     * Returns the energy required for the activity.
     *
     * @return the energy required for the activity
     */
    public float getEnergy() {
        return energy;
    }

    /**
     * Sets the energy required for the activity.
     *
     * @param energy the energy required for the activity
     */
    public void setEnergy(float energy) {
        this.energy = energy;
    }

    /**
     * Returns the time duration of the activity.
     *
     * @return the time duration of the activity
     */
    public float getTimeUse() {
        return timeUse;
    }

    /**
     * Sets the time duration of the activity.
     *
     * @param timeUse the time duration of the activity
     */
    public void setTimeUse(float timeUse) {
        this.timeUse = timeUse;
    }

    /**
     * Returns the description of the activity.
     *
     * @return the description of the activity
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the activity.
     *
     * @param description the description of the activity
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the zone where the activity can be performed.
     *
     * @return the zone where the activity can be performed
     */
    public Rectangle getZone() {
        return zone;
    }
}