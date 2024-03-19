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

import java.util.Objects;

/**
 * This class extends the {@link ScreenAdapter} class and represents an error screen displayed
 * when the player encounters certain errors during gameplay, such as running out of time or energy.
 */
public class ErrorScreen extends ScreenAdapter {

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

    /** The type of error encountered. */
    String errorType;


    /**
     * Constructs a new ErrorScreen with the specified game instance and error type.
     *
     * @param game      The game instance
     * @param errorType The type of error encountered
     */
    public ErrorScreen (final HeslingtonHustle game, String errorType) {
        this.game = game;
        batch = game.batch;
        font = game.font;
        this.errorType = errorType;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Called when this screen becomes the current screen for the Game.
     * Sets up the UI elements for displaying the error message and a back button.
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        skin = new Skin(Gdx.files.internal("skin/cloud-form-ui.json"));

        TextButton back = new TextButton("Back", skin);

        String message = "";
        if (Objects.equals(this.errorType, "time")) {
            message = "It's getting late, you should go to bed.";
        } else {
            message = "You're too tired to do that!";
        }
        Label error = new Label(message, skin, "title", Color.WHITE);

        table.add(error).center();
        table.row().pad(50, 0, 0, 0);
        table.add(back);

        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(HeslingtonHustle.GAME);
            }
        });

    }

    /**
     * Renders the error screen by clearing the screen and drawing the stage.
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
     * Disposes of resources used by the error screen, such as the stage and skin.
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

