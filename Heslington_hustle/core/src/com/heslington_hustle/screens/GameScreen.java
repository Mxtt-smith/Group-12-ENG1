package com.heslington_hustle.screens;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.heslington_hustle.game.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.heslington_hustle.game.Activity;

import java.awt.*;

import static com.heslington_hustle.game.Player.character;

// This is the screen that will show the actual game
public class GameScreen extends ScreenAdapter {
    final HeslingtonHustle game;
    // fetch the assets
    TiledMap map;
    OrthogonalTiledMapRenderer renderer;
    SpriteBatch batch;
    OrthographicCamera overviewCam;
    TextureAtlas atlas;
    Player player;
    Texture blank;
    Texture orange;
    BitmapFont font;
    Activity study, eat, sleep, recreation;

    public GameScreen(HeslingtonHustle game) {
        this.game = game;

        overviewCam = new OrthographicCamera();
        overviewCam.update();

        // load the tiled map
        map = new TmxMapLoader().load("map1.tmx");
        // create the renderer
        renderer = new OrthogonalTiledMapRenderer(map, 1/16f);

        // create the spriteBatch and font
        batch = game.batch;
        font = game.font;

        // Create the player
        atlas = new TextureAtlas(Gdx.files.internal("characters/"+character+".atlas"));
        player = new Player(atlas, (TiledMapTileLayer)map.getLayers().get("Collisions"));
        player.setTexture(character+"sd");
        // Multiply by 16 as all assets are 16 bit
        player.setPosition(400, 400);

        // Energy bar
        orange = new Texture("orange.png");
        blank = new Texture("blank.png");

        // Create the activities on the map
        study = new Activity(50, 4, Activity.ActivityType.STUDY, "make flashcards");
        study.set(39*16, 4*16, 2*16, 16);

        recreation = new Activity(20, 2, Activity.ActivityType.RECREATION, "feed the ducks");
        recreation.set(11*16, 8*16, 2*16, 2*16);

        eat = new Activity(10, 1, Activity.ActivityType.EAT, "eat lunch");
        eat.set(35*16, 39*16, 2*16, 16);

        sleep = new Activity(0, 0, Activity.ActivityType.SLEEP, "get an early night");
        sleep.set(11*16, 35*16, 2*16, 16);

        // Set the game's state
        this.game.setState(HeslingtonHustle.GameState.FREE_ROAM);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);
        overviewCam.update();
        renderer.setView(overviewCam);
        renderer.render();

        // Player movement
        // Collision detection first
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && !player.collision(-2, 0)) {
            player.moveLeft();
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !player.collision(2, 0)) {
            player.moveRight();
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP) && !player.collision(0, 2)) {
            player.moveUp();
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && !player.collision(0, -2)) {
            player.moveDown();
        } else {
            player.stationary();
        }

        // Activity detection
        Rectangle playerRect = player.getBoundingRectangle();
        if (playerRect.overlaps(eat.getZone()) || playerRect.overlaps(sleep.getZone()) || playerRect.overlaps(study.getZone()) || playerRect.overlaps(recreation.getZone())) {
            game.setState(HeslingtonHustle.GameState.ACTIVITY);
        } else game.setState(HeslingtonHustle.GameState.FREE_ROAM);

        // If a player wishes to do an activity
        if (game.getState() == HeslingtonHustle.GameState.ACTIVITY) {
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                if (playerRect.overlaps(eat.getZone())) game.setScreen(new ActivityScreen(game, eat));
                else if (playerRect.overlaps(recreation.getZone())) game.setScreen(new ActivityScreen(game, recreation));
                else if (playerRect.overlaps(study.getZone())) game.setScreen(new ActivityScreen(game, study));
                else if (playerRect.overlaps(sleep.getZone())) game.setScreen(new ActivityScreen(game, sleep));
            }
        }

        // Pause game
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.changeScreen(HeslingtonHustle.PAUSE);
        }

        batch.begin();
        player.draw(batch);
        batch.draw(blank,55,783,200,10);

        // Show the player's energy and time
        if (HeslingtonHustle.Energy > 0) batch.draw(orange,55,783,200*(HeslingtonHustle.Energy/100),10);
        if (game.getState() == HeslingtonHustle.GameState.ACTIVITY) font.draw(batch, "Press SPACE for activity", 330, 50);
        font.draw(batch, "Energy", 5, 795);
        font.draw(batch, HeslingtonHustle.Time.toString(), 750, 795);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        overviewCam.setToOrtho(false, width/16f, height/16f);
    }

    @Override
    public void resume() {
        game.setState(HeslingtonHustle.GameState.FREE_ROAM);
    }

    @Override
    public void dispose() {
        blank.dispose();
        orange.dispose();
        map.dispose();
        renderer.dispose();
    }
}