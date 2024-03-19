package com.heslington_hustle.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
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
 * The MainMenuScreen extends the {@link ScreenAdapter} class and represents the main menu screen of the game.
 * It allows users to navigate to different sections of the game, such as starting a new game,
 * accessing the "How to Play" section, or exiting the game.
 */
public class MainMenuScreen extends ScreenAdapter {

    /** The stage for displaying UI components. */
    private final Stage stage;

    /** The skin for styling UI elements. */
    private Skin skin;

    /** The game instance. */
    private final HeslingtonHustle game;

    /**
     * Constructs a MainMenuScreen object with the specified game instance.
     *
     * @param game The HeslingtonHustle game instance.
     */
    public MainMenuScreen(HeslingtonHustle game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Called when this screen becomes the current screen for the Game.
     * Sets up the UI elements for displaying the main menu and buttons to character selection, how to play and exit.
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        skin = new Skin(Gdx.files.internal("skin/cloud-form-ui.json"));

        Label titleLabel = new Label("Heslington Hustle", skin, "title", Color.WHITE);

        TextButton newGame = new TextButton("New Game", skin);
        TextButton howToPlay = new TextButton("How to Play", skin);
        TextButton exit = new TextButton("Exit", skin);

        // Format table
        table.add(titleLabel).fillX().center();
        table.row().pad(10, 0, 0, 0);
        table.add(newGame);
        table.row().pad(10, 0, 10, 0);
        table.add(howToPlay);
        table.row();
        table.add(exit);

        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        howToPlay.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(HeslingtonHustle.HOWTOPLAY);
            }
        });

        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(HeslingtonHustle.CHARACTER);
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

        stage.act();
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

    /**
     * Disposes of resources used by the screen.
     */
    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
