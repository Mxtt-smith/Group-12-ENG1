package com.heslington_hustle.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.heslington_hustle.screens.*;

import java.time.LocalTime;

// Our core class will extend the Game class
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

    // The game's screens
    MainMenuScreen menuScreen;
    CharacterSelectionScreen characterScreen;
    HowToPlayScreen howToPlayScreen;
    GameScreen gameScreen;
    NewDayScreen newDayScreen;
    EndGameScreen endScreen;
    PauseScreen pauseScreen;
    public final static int MENU = 0;
    public final static int CHARACTER = 1;
    public final static int HOWTOPLAY = 2;
    public final static int GAME = 3;
    public final static int NEWDAY = 4;
    public final static int END = 5;
    public final static int PAUSE = 6;


    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();

        Energy = 100;
        hoursLeft = 16;
        Day = 1;
        Time = LocalTime.of(7, 30);
        stats = new Stats();

        menuScreen = new MainMenuScreen(this);
        setScreen(menuScreen);
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

    public void changeScreen(int screen) {
        switch (screen) {
            case MENU:
                if (menuScreen == null) menuScreen = new MainMenuScreen(this);
                this.setScreen(menuScreen);
                break;
            case CHARACTER:
                if (characterScreen == null) characterScreen = new CharacterSelectionScreen(this);
                this.setScreen(characterScreen);
                break;
            case HOWTOPLAY:
                if (howToPlayScreen == null) howToPlayScreen = new HowToPlayScreen(this);
                this.setScreen(howToPlayScreen);
                break;
            case GAME:
                if (gameScreen == null) gameScreen = new GameScreen(this);
                this.setScreen(gameScreen);
                break;
            case NEWDAY:
                if (newDayScreen == null) newDayScreen = new NewDayScreen(this);
                this.setScreen(newDayScreen);
                break;
            case END:
                if (endScreen == null) endScreen = new EndGameScreen(this);
                this.setScreen(endScreen);
                break;
            case PAUSE:
                if (pauseScreen == null) pauseScreen = new PauseScreen(this);
                this.setScreen(pauseScreen);
                break;
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        super.dispose();
    }
}
