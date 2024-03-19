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

public class ErrorScreen extends ScreenAdapter {
    private final Stage stage;
    Skin skin;
    private final HeslingtonHustle game;
    BitmapFont font;
    private final SpriteBatch batch;
    String errorType;


    public ErrorScreen (final HeslingtonHustle game, String errorType) {
        this.game = game;
        batch = game.batch;
        font = game.font;
        this.errorType = errorType;
        /// create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        // Create a table that fills the screen
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Assign skin to the menu
        skin = new Skin(Gdx.files.internal("skin/cloud-form-ui.json"));

        // Create back button
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

        // Create button listeners
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(HeslingtonHustle.GAME);
            }
        });

    }

    @Override
    public void render(float delta) {
        // clear the screen ready for next set of images to be drawn
        ScreenUtils.clear(0, 0, 0, 0);

        // tell our stage to do actions and draw itself
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // change the stage's viewport when the screen size is changed
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}

