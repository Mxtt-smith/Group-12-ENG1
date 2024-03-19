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

import static com.heslington_hustle.game.HeslingtonHustle.Day;

/**
 * NewDayScreen class represents the screen displayed at the beginning of a new day in the game.
 * It allows players to start the day and proceed to the game screen.
 */
public class NewDayScreen extends ScreenAdapter {

    /** The stage for displaying UI components. */
    private final Stage stage;

    /** The skin for styling UI elements. */
    Skin skin;

    /** The game instance. */
    private final HeslingtonHustle game;

    /** The font for text rendering. */
    BitmapFont font;

    /** The sprite batch for rendering. */
    private final SpriteBatch batch;

    /**
     * Constructs a NewDayScreen object with the specified game instance.
     *
     * @param game The HeslingtonHustle game instance.
     */
    public NewDayScreen (final HeslingtonHustle game) {
        this.game = game;
        batch = game.batch;
        font = game.font;

        // Create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Called when this screen becomes the current screen for the Game.
     * Sets up the UI elements for displaying the new day message and an accept button.
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

        // Title
        Label label = new Label("Day " + Day, skin, "title", Color.WHITE);
        // Create button
        TextButton ok = new TextButton("Start Day!", skin);

        // Format table layout of UI
        table.defaults().uniformX().center();
        table.add(label).colspan(2);
        table.row().pad(30, 0, 0, 0);

        // Show how many activities of each have been completed so far
        if (HeslingtonHustle.Day != 1) {
            // Make labels for the counters
            // {study, recreation, eat}
            int[] stats = game.stats.getTally();
            Label studiedTitle = new Label("Studied:", skin, "font", Color.WHITE);
            Label recreationTitle = new Label("Recreational activities:", skin, "font", Color.WHITE);
            Label eatTitle = new Label("Ate:", skin, "font", Color.WHITE);
            Label studyCount = new Label(String.valueOf(stats[0]), skin, "font", Color.WHITE);
            Label recCount = new Label(String.valueOf(stats[1]), skin, "font", Color.WHITE);
            Label eatCount = new Label(String.valueOf(stats[2]), skin, "font", Color.WHITE);

            // Add to the table
            table.add(studiedTitle);
            table.add(studyCount);
            table.row();
            table.add(recreationTitle);
            table.add(recCount);
            table.row();
            table.add(eatTitle);
            table.add(eatCount);
            table.row().pad(30, 0, 0, 0);
        }
        table.add(ok).colspan(2);

        // Create button listeners
        ok.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                    game.changeScreen(HeslingtonHustle.GAME);
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
        // Clear the screen ready for next set of images to be drawn
        ScreenUtils.clear(0, 0, 0, 0);

        // Tell our stage to do actions and draw itself
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
        // Change the stage's viewport when the screen size is changed
        stage.getViewport().update(width, height, true);
    }

    /**
     * Called when the screen is hidden. Sets the input processor to null.
     */
    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    /**
     * Disposes of resources used by the screen.
     */
    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}


