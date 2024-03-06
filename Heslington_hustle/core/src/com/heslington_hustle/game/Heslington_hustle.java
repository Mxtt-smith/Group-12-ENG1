package com.heslington_hustle.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.heslington_hustle.screens.Play;

// Our core class will extend the Game class
// (implementing the ApplicationListener interface.)
// This is easier to use when setting up different screens (Startup, pause, etc)
public class Heslington_hustle extends Game {
    // load the assets
    private Texture player;
    private Texture world;
    private OrthographicCamera camera;
    @Override
    public void create() {
        // For now, set the startup screen to the playing game screen
        setScreen(new Play());
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
        super.dispose();
    }
}