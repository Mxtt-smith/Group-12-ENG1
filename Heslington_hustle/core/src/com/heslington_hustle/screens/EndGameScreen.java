package com.heslington_hustle.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

public class EndGameScreen extends ScreenAdapter {
    HeslingtonHustle game;
    private final Stage stage;
    Skin skin;
    BitmapFont font;

    public EndGameScreen (final HeslingtonHustle game) {
        this.game = game;
        font = game.font;
        /// create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        game.setState(HeslingtonHustle.GameState.END);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        // Create a table that fills the screen
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // assign skin to the exit
        skin = new Skin(Gdx.files.internal("skin/cloud-form-ui.json"));

        Label title = new Label("You made it through the week!", skin, "title", Color.WHITE);

        //create button
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

        // create button listeners
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.reset();
                game.changeScreen(HeslingtonHustle.MENU);
                dispose();
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
