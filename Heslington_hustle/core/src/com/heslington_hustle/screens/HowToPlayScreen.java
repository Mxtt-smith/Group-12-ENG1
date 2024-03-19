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

public class HowToPlayScreen extends ScreenAdapter {

    private final Stage stage;
    Skin skin;
    private final HeslingtonHustle game;
    BitmapFont font;
    SpriteBatch batch;

    public HowToPlayScreen(final HeslingtonHustle game) {
        this.game = game;
        batch = game.batch;
        font = game.font;
        // create stage and set it as input processor
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

        Label title = new Label("How to play!", skin, "title", Color.WHITE);

        Label description = new Label("Welcome to Heslington Hustle" +
                "\nNavigate around the map with your WASD or arrow keys" +
                "\nYou'll find different buildings scattered around the map!" +
                "\nPress the space bar to interact with them to see what they do." +
                "\nYou have 7 days in game to do what it takes to pass your exams," +
                "\nStrategise and manage your time wisely but most importantly, enjoy!", skin, "font", Color.WHITE);
        //create button
        TextButton back = new TextButton("Back", skin);

        // Format the table
        table.add(title).center();
        table.row().pad(10, 0, 0, 0);
        table.add(description);
        table.row().pad(10, 0, 0, 0);
        table.add(back).uniformX();

        // Create button listeners
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(HeslingtonHustle.MENU);
            }
        });

    }

    @Override
    public void render(float delta) {
        // Clear the screen ready for next set of images to be drawn
        ScreenUtils.clear(0, 0, 0, 0);

        // Tell our stage to do actions and draw itself
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // Change the stage's viewport when the screen size is changed
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
