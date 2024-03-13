package com.heslington_hustle.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.heslington_hustle.game.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.heslington_hustle.game.Activity.ActivityType;

import static com.heslington_hustle.game.Activity.EnergyUse;
import static com.heslington_hustle.game.Activity.TimeUse;
import static com.heslington_hustle.game.Heslington_hustle.*;

public class ActivityScreen extends ScreenAdapter {

    private final Stage stage;
    private final Heslington_hustle game;
    BitmapFont font;
    private final SpriteBatch batch;
    Activity activity;


    public ActivityScreen (final Heslington_hustle game, Eat activity) {
        this.game = game;
        batch = game.batch;
        font = game.font;
        this.activity = activity;

        // create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }
    public ActivityScreen (final Heslington_hustle game, Sleep activity) {
        this.game = game;
        batch = game.batch;
        font = game.font;
        this.activity = activity;

        // create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }
    public ActivityScreen (final Heslington_hustle game, Recreation activity) {
        this.game = game;
        batch = game.batch;
        font = game.font;
        this.activity = activity;

        // create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }
    public ActivityScreen (final Heslington_hustle game, Study activity) {
        this.game = game;
        batch = game.batch;
        font = game.font;
        this.activity = activity;

        // create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        System.out.println("Show activity screen");
        Gdx.input.setInputProcessor(stage);
        // Create a table that fills the screen
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);

        // assign skin to the menu
        Skin skin = new Skin(Gdx.files.internal("skin.json"));

        //create button
        TextButton yes = new TextButton("Yes", skin);
        TextButton back = new TextButton("Back", skin);

        //add buttons to table
        table.add(back).fillX().uniformX();

        // create button listeners
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game));
            }
        });
        yes.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (Energy - EnergyUse < 0) {
                    game.setScreen(new ErrorScreen(game));
                }
                else if (Time - TimeUse < 0) {
                    Day += 1;
                    game.setScreen(new NewDayScreen(game));
                }
                else {
                    Energy -= EnergyUse;
                    Time -= TimeUse;
                    game.setScreen(new GameScreen(game));
                }
            }
        });

    }

    @Override
    public void render(float delta) {
        // clear the screen ready for next set of images to be drawn
        ScreenUtils.clear(0, 0, 0, 0);

        if (activity.getType() == ActivityType.EAT) {

            // tell our stage to do actions and draw itself
            stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
            stage.draw();
            batch.begin();
            font.draw(batch, "Do you want to eat?", 200, 600);
            batch.end();
        }
        if (activity.getType() == ActivityType.RECREATION) {

            // tell our stage to do actions and draw itself
            stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
            stage.draw();
            batch.begin();
            font.draw(batch, "Do you want to feed the ducks?", 200, 600);
            batch.end();
        }
        if (activity.getType() == ActivityType.STUDY) {

            // tell our stage to do actions and draw itself
            stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
            stage.draw();
            batch.begin();
            font.draw(batch, "Do you want to Study?", 200, 600);
            batch.end();
        }
        if (activity.getType() == ActivityType.SLEEP) {

            // tell our stage to do actions and draw itself
            stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
            stage.draw();
            batch.begin();
            font.draw(batch, "Do you want to Sleep?", 200, 600);
            batch.end();
        }
    }

    @Override
    public void resize(int width, int height) {
        // change the stage's viewport when the screen size is changed
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void hide() {
        System.out.println("Activity screen hiding");
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
        font.dispose();
        batch.dispose();
    }
}
