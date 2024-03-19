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

public class MainMenuScreen extends ScreenAdapter {
    private final Stage stage;
    private Skin skin;
    private final HeslingtonHustle game;

    public MainMenuScreen(HeslingtonHustle game) {
        this.game = game;
        // create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        skin = new Skin(Gdx.files.internal("skin/cloud-form-ui.json"));

        // Game title
        Label titleLabel = new Label("Heslington Hustle", skin, "title", Color.WHITE);

        // Create buttons
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

        // Create button listeners
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

    @Override
    public void render(float delta) {
        // Clear the screen ready for next set of images to be drawn
        ScreenUtils.clear(0, 0, 0, 0);

        // Tell our stage to do actions and draw itself
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // change the stage's viewport when the screen size is changed
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
