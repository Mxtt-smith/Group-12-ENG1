package com.heslington_hustle.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.ScreenUtils;
import com.heslington_hustle.game.Player;
import com.badlogic.gdx.Gdx;
import com.heslington_hustle.game.HeslingtonHustle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import static com.heslington_hustle.game.HeslingtonHustle.Day;
import static com.heslington_hustle.game.HeslingtonHustle.Energy;
import static com.heslington_hustle.game.HeslingtonHustle.hoursLeft;
public class CharacterSelectionScreen extends ScreenAdapter {

    private final Stage stage;
    private final HeslingtonHustle game;

    public CharacterSelectionScreen(final HeslingtonHustle game) {
        this.game = game;
        /// create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        System.out.println("Character selection showing");
        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);

        // temporary skin
        Skin skin = new Skin(Gdx.files.internal("skin.json"));

        //create buttons
        TextButton Char1 = new TextButton("Character 1", skin);
        TextButton Char2 = new TextButton("Character 2", skin);
        TextButton back = new TextButton("Back", skin);

        //add buttons to table
        table.add(Char1).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(Char2).fillX().uniformX();
        table.row();
        table.add(back).fillX().uniformX();

        // create button listeners
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game));
            }
        });

        Char1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Player.character = "char1";
                game.setScreen(new NewDayScreen(game));
            }
        });

        Char2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Player.character = "char2";
                game.setScreen(new GameScreen(game));
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
    public void hide() {
        System.out.println("Character Selection hiding");
        Gdx.input.setInputProcessor(null);
    }
}
