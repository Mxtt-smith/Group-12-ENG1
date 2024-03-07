package com.heslington_hustle.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.heslington_hustle.screens.MainMenu;

// Our core class will extend the Game class
// (implementing the ApplicationListener interface.)
// This is easier to use when setting up different screens (Startup, pause, etc.)
public class Heslington_hustle extends Game {
    // load the assets
    public SpriteBatch batch;
    public BitmapFont font;
    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        // For now, set the startup screen to the playing game screen
        this.setScreen(new MainMenu(this));
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