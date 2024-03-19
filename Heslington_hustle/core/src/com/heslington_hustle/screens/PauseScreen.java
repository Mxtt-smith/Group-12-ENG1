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
 * PauseScreen extends the {@link ScreenAdapter} class and represents the screen displayed when the game is paused.
 * It allows players to resume the game from the pause state.
 */
public class PauseScreen extends ScreenAdapter {

    /** The stage for displaying UI components. */
    private final Stage stage;

    /** The game instance. */
    private final HeslingtonHustle game;

    /** The font for text rendering. */
    BitmapFont font;

    /** The sprite batch for rendering. */
    private final SpriteBatch batch;

    /**
     * Constructs a PauseScreen object with the specified game instance.
     *
     * @param game The HeslingtonHustle game instance.
     */
    public PauseScreen (final HeslingtonHustle game) {
        this.game = game;
        batch = game.batch;
        font = game.font;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Called when this screen becomes the current screen for the Game.
     * Sets up the UI elements for displaying the paused message and a resume button.
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Skin skin = new Skin(Gdx.files.internal("skin/cloud-form-ui.json"));

        TextButton resume = new TextButton("Resume", skin);

        Label label = new Label("Paused", skin, "title", Color.WHITE);

        table.add(label).center();
        table.row().pad(50, 0, 0, 0);
        table.add(resume).center();

        resume.addListener(new ChangeListener() {
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
     * Called when the screen is hidden. Sets the input processor to null.
     */
    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
