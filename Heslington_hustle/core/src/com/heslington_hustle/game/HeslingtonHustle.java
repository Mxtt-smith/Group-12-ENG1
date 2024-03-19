package com.heslington_hustle.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.heslington_hustle.screens.*;

import java.time.LocalTime;

/**
 * The HeslingtonHustle class represents the main game class for the Heslington Hustle game.
 * It extends the Game class and manages the game's core components, screens, and state.
 */
public class HeslingtonHustle extends Game {

    /** The sprite batch used for rendering. */
    public SpriteBatch batch;

    /** The font used for rendering text. */
    public BitmapFont font;

    // The game's core factors

    /** Energy level of the player. */
    public static float Energy;

    /** Hours left in the day. */
    public static float hoursLeft;

    /** Current day in the game. */
    public static int Day;

    /** Current time in the game. */
    public static LocalTime Time;

    /** Current player stats, times studied, times eaten etc. */
    public Stats stats;

    // The game's screens

    /** Main menu screen. */
    MainMenuScreen menuScreen;

    /** Character selection screen. */
    CharacterSelectionScreen characterScreen;

    /** How to play screen. */
    HowToPlayScreen howToPlayScreen;

    /** Game screen. */
    GameScreen gameScreen;

    /** New day screen. */
    NewDayScreen newDayScreen;

    /** End game screen. */
    EndGameScreen endScreen;

    /** Pause screen. */
    PauseScreen pauseScreen;

    /** Numbers representing different game screens. */
    public final static int MENU = 0;
    public final static int CHARACTER = 1;
    public final static int HOWTOPLAY = 2;
    public final static int GAME = 3;
    public final static int NEWDAY = 4;
    public final static int END = 5;
    public final static int PAUSE = 6;

    /**
     * Resets the game by disposing the game screen and resetting game state and statistics.
     */
    public void reset() {
        // Remove game screen
        gameScreen.dispose();
        gameScreen = null;

        state = GameState.MENU;
        stats.reset();
    }

    /** Enum representing the game's state. */
    public enum GameState {
        MENU,
        FREE_ROAM,
        ACTIVITY,
        END
    }

    /** Current state of the game. */
    GameState state;

    /**
     * Called when the game is first created. Initializes the game's components and sets the initial state.
     */
    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();

        state = GameState.MENU;
        Energy = 100;
        hoursLeft = 16;
        Day = 1;
        Time = LocalTime.of(7, 30);
        stats = new Stats();

        menuScreen = new MainMenuScreen(this);
        setScreen(menuScreen);
    }

    /**
     * Called when the game should render itself. Delegates the rendering to the active screen.
     */
    @Override
    public void render() {
        super.render();
    }

    /**
     * Called when the game window is resized. Delegates the resizing to the active screen.
     *
     * @param width the new width of the window
     * @param height the new height of the window
     */
    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    /**
     * Called when the game is paused, usually when it's not active or visible. Delegates the pause event to the active screen.
     */
    @Override
    public void pause() {
        super.pause();
    }

    /**
     * Called when the game is resumed from a paused state. Delegates the resume event to the active screen.
     */
    @Override
    public void resume() {
        super.resume();
    }

    /**
     * Changes the current screen of the game based on the provided screen identifier.
     * If the screen is null it creates a new one.
     *
     * @param screen the identifier of the screen to be changed to
     */
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
                else if (getState() == GameState.END) {
                    gameScreen.dispose();
                    gameScreen = new GameScreen(this);
                }
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

    /**
     * Sets the state of the game.
     *
     * @param state the state of the game
     */
    public void setState(GameState state) {
        this.state = state;
    }

    /**
     * Returns the current state of the game.
     *
     * @return the current state of the game
     */
    public GameState getState() {
        return state;
    }

    /**
     * Disposes of the resources used
     */
    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
