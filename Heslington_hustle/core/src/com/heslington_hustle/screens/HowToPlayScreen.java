package com.heslington_hustle.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.ScreenUtils;
import com.heslington_hustle.game.HeslingtonHustle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * This class extends the {@link ScreenAdapter} class, representing the screen that provides instructions to play the game.
 * It explains the basic controls and objectives to the player.
 */
public class HowToPlayScreen extends ScreenAdapter {

    /** The stage for displaying UI components. */
    private final Stage stage;

    /** The skin for styling UI elements. */
    Skin skin;

    /** The game instance. */
    private final HeslingtonHustle game;

    /** The font for text rendering. */
    BitmapFont font;

    /** The sprite batch for rendering. */
    SpriteBatch batch;

    /**
     * Constructs a new HowToPlayScreen with the specified game instance.
     *
     * @param game The game instance
     */
    public HowToPlayScreen(final HeslingtonHustle game) {
        this.game = game;
        batch = game.batch;
        font = game.font;
        // create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Sets up the screen with instructions on how to play the game.
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        // Create a table that fills the screen
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Assign skin to the menu
        skin = new Skin(Gdx.files.internal("skin/cloud-form-ui.json"));

        Label title = new Label("How to play!", skin, "title", Color.WHITE);

        Label description = new Label("Welcome to Heslington Hustle" +
                "\nNavigate around the map with your WASD or arrow keys" +
                "\nYou'll find different buildings scattered around the map!" +
                "\nPress the [SPACE] to interact with them to see what they do." +
                "\nYou have 7 days in game to do what it takes to pass your exams," +
                "\nStrategise and manage your time wisely but most importantly, enjoy!", skin, "font", Color.WHITE);

        TextButton back = new TextButton("Back", skin);

        // Format the table
        table.add(title).center();
        table.row().pad(10, 0, 0, 0);
        table.add(description);
        table.row().pad(10, 0, 0, 0);
        table.add(back).uniformX();

        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(HeslingtonHustle.MENU);
            }
        });

    }

    /**
     * Renders the screen.
     *
     * @param delta the time in seconds since the last render
     */
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    /**
     * Called when the screen is resized. Updates the stage's viewport accordingly.
     *
     * @param width  the new width of the screen
     * @param height the new height of the screen
     */
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    /**
     * Disposes of resources used by the screen.
     */
    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

    /**
     * Called when the screen is hidden. Sets the input processor to null.
     */
    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
