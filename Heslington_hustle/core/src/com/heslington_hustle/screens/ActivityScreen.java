package com.heslington_hustle.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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

import static com.heslington_hustle.game.HeslingtonHustle.*;

/**
 * The ActivityScreen class extends the {@link ScreenAdapter} class and is displayed if the player wishes
 * to perform a specific activity.
 * It displays a message with the activity description and provides options to confirm or go back.
 */
public class ActivityScreen extends ScreenAdapter {

    /** The stage for displaying UI components. */
    private final Stage stage;

    /** The game instance. */
    private final HeslingtonHustle game;

    /** The font used for text rendering. */
    BitmapFont font;

    /** The sprite batch for rendering. */
    private final SpriteBatch batch;

    /** The activity to be performed. */
    Activity activity;

    /** The skin for UI elements. */
    Skin skin;

    /**
     * Constructs an ActivityScreen with the given game instance and activity.
     *
     * @param game the game instance
     * @param activity the activity to be performed
     */
    public ActivityScreen (final HeslingtonHustle game, Activity activity) {
        this.game = game;
        batch = game.batch;
        font = game.font;
        this.activity = activity;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Called when this screen becomes the current screen for the Game.
     * Sets up the UI elements for displaying activity information and accept or reject buttons.
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        skin = new Skin(Gdx.files.internal("skin/cloud-form-ui.json"));

        Label label = new Label("Do you want to " + activity.getDescription() + "?", skin, "title", Color.WHITE);
        label.setFontScale(1.2f);

        TextButton yes = new TextButton("Yes", skin);
        TextButton back = new TextButton("Back", skin);

        // Format table layout
        table.add(label).center().colspan(2);
        table.row().pad(50, 0, 0, 0);
        table.add(back).uniformX().padRight(10).right();
        table.add(yes).uniformX().left();

        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(GAME);
            }
        });
        yes.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // Check if player has enough energy
                if ((HeslingtonHustle.Energy - activity.getEnergy()) < 0) {
                    game.setScreen(new ErrorScreen(game, "energy"));
                // Check if they have enough time
                } else if (hoursLeft - activity.getTimeUse() < 0) {
                    game.setScreen(new ErrorScreen(game, "time"));
                } else {
                    if (activity.getType() == ActivityType.SLEEP) {
                        game.stats.addDay(game.stats.getStats());
                        game.stats.newDay();
                        if (Day <= 7) {
                            game.setScreen(new NewDayScreen(game));
                        } else {
                            game.setScreen(new EndGameScreen(game));
                        }
                    } else {
                        HeslingtonHustle.Energy -= activity.getEnergy();
                        hoursLeft -= activity.getTimeUse();
                        Time = Time.plusHours((long)activity.getTimeUse());
                        try {
                            game.stats.log(activity.getType());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        game.changeScreen(GAME);
                    }
                }
                dispose();
            }
        });

    }

    /**
     * Renders the activity screen by clearing the screen and drawing the stage.
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

    /**
     * Disposes of resources used by the activity screen, such as the stage and skin.
     * This method is called when the screen is no longer needed.
     */
    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
