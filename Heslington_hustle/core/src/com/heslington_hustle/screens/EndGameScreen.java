package com.heslington_hustle.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.heslington_hustle.game.HeslingtonHustle;

/**
 * This class extends the {@link ScreenAdapter} class and represents the end game screen displayed
 * when the player completes the game.
 * It shows the player's overall statistics for the game and provides an option to return to the main menu.
 */
public class EndGameScreen extends ScreenAdapter {

    /** The game instance. */
    HeslingtonHustle game;

    /** The stage for managing UI elements. */
    private final Stage stage;

    /** The skin for styling UI elements. */
    Skin skin;

    /** The font for text rendering. */
    BitmapFont font;

    /**
     * Constructs a new EndGameScreen with the specified game instance.
     *
     * @param game The game instance
     */
    public EndGameScreen (final HeslingtonHustle game) {
        this.game = game;
        font = game.font;
        /// create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        game.setState(HeslingtonHustle.GameState.END);
    }

    /**
     * Called when this screen becomes the current screen for the Game.
     * Sets up the UI elements for displaying end game information and return button.
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        skin = new Skin(Gdx.files.internal("skin/cloud-form-ui.json"));

        Label title = new Label("You made it through the week!", skin, "title", Color.WHITE);

        TextButton exit = new TextButton("Menu", skin);

        // Get the players overall game counters
        int[] stats = game.stats.getTally();
        Label studiedTitle = new Label("Studied:", skin, "font", Color.WHITE);
        Label recreationTitle = new Label("Recreational activities:", skin, "font", Color.WHITE);
        Label eatTitle = new Label("Ate:", skin, "font", Color.WHITE);
        Label studyCount = new Label(String.valueOf(stats[0]), skin, "font", Color.WHITE);
        Label recCount = new Label(String.valueOf(stats[1]), skin, "font", Color.WHITE);
        Label eatCount = new Label(String.valueOf(stats[2]), skin, "font", Color.WHITE);

        // Format the table
        table.defaults().uniformX().center();
        table.add(title).colspan(2);
        table.row().pad(30, 0, 0, 0);
        table.add(studiedTitle);
        table.add(studyCount);
        table.row();
        table.add(recreationTitle);
        table.add(recCount);
        table.row();
        table.add(eatTitle);
        table.add(eatCount);
        table.row().pad(30, 0, 0, 0);
        table.add(exit).colspan(2);

        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.reset();
                game.changeScreen(HeslingtonHustle.MENU);
                dispose();
            }
        });

    }

    /**
     * Renders the end game screen by clearing the screen and drawing the stage.
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
     * Disposes of resources used by the end game screen, such as the stage and skin.
     * This method is called when the screen is no longer needed.
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
