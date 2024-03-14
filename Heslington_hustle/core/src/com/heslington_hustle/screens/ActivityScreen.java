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

import static com.heslington_hustle.game.Activity.TimeUse;
import static com.heslington_hustle.game.HeslingtonHustle.*;

public class ActivityScreen extends ScreenAdapter {

    private final Stage stage;
    private final HeslingtonHustle game;
    BitmapFont font;
    private final SpriteBatch batch;
    Activity activity;


    public ActivityScreen (final HeslingtonHustle game, Activity activity) {
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
        table.add(yes).fillX().uniformX();

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
                if (HeslingtonHustle.Energy - Activity.Energy < 0) {
                    game.setScreen(new ErrorScreen(game));
                } else if (hoursLeft - TimeUse < 0) {
                    game.setScreen(new ErrorScreen(game));
                }
                String activity;
                switch (Activity.type) {
                    case STUDY:
                        activity = "study";
                        break;
                    case EAT:
                        activity = "eat";
                        break;
                    case RECREATION:
                        activity = "recreation";
                        break;
                    default:
                        activity = "sleep";
                        break;
                }
                if (activity == "sleep") {
                        game.stats.addDay(game.stats.getStats());
                        game.stats.newDay();
                        if (Day <= 7) {
                            game.setScreen(new NewDayScreen(game));
                        } else {
                            // End the game
                            game.setScreen(new EndGameScreen(game));
                        }
                } else {
                    HeslingtonHustle.Energy -= Activity.Energy;
                    hoursLeft -= TimeUse;
                    try {
                        game.stats.log(activity);
                        System.out.println("Logged " + activity);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    game.setScreen(new GameScreen(game));
                }
            }
        });

    }

    @Override
    public void render(float delta) {
        // clear the screen ready for next set of images to be drawn
        ScreenUtils.clear(0, 0, 0, 0);

        if (Activity.type == ActivityType.EAT) {

            // tell our stage to do actions and draw itself
            stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
            stage.draw();
            batch.begin();
            font.draw(batch, "Do you want to eat?", 200, 600);
            batch.end();
        }
        if (Activity.type == ActivityType.RECREATION) {

            // tell our stage to do actions and draw itself
            stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
            stage.draw();
            batch.begin();
            font.draw(batch, "Do you want to feed the ducks?", 200, 600);
            batch.end();
        }
        if (Activity.type == ActivityType.STUDY) {

            // tell our stage to do actions and draw itself
            stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
            stage.draw();
            batch.begin();
            font.draw(batch, "Do you want to Study?", 200, 600);
            batch.end();
        }
        if (Activity.type == ActivityType.SLEEP) {

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
