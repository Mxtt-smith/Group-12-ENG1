package com.heslington_hustle.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.heslington_hustle.screens.*;

import java.time.LocalTime;

// Our core class will extend the Game class
// (implementing the ApplicationListener interface.)
// This is easier to use when setting up different screens (Startup, pause, etc.)
public class HeslingtonHustle extends Game {
    public SpriteBatch batch;
    public BitmapFont font;

    // The game's core factors
    public static float Energy;
    public static float hoursLeft;
    public static int Day;
    public static LocalTime Time;
    public Stats stats;


    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();

        Energy = 100;
        hoursLeft = 16;
        Day = 1;
        Time = LocalTime.of(7, 30);
        stats = new Stats();

        this.setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        super.dispose();
    }
}
