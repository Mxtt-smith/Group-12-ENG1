package com.heslington_hustle.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
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

import static com.heslington_hustle.game.Activity.TimeUse;
import static com.heslington_hustle.game.Player.character;
import static com.heslington_hustle.game.Activity.Energy;

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
    Study study;
    Eat eat;
    Sleep sleep;
    Recreation recreation;
    enum GameState {
        FREE_ROAM,
        DOING_ACTIVITY,
        LOSE,
        NEW_DAY,
        TOO_TIRED
    }
    GameState state;


    public GameScreen(final HeslingtonHustle game) {
        this.game = game;
        System.out.println("New game screen");

        overviewCam = new OrthographicCamera();

        // Energy bar
        orange = new Texture("orange.png");
        blank = new Texture("blank.png");

        // load the tiled map
        map = new TmxMapLoader().load("map1.tmx");
        // create the renderer
        renderer = new OrthogonalTiledMapRenderer(map, 1/16f);
        renderer.setView(overviewCam);

        // create the spriteBatch and font
        batch = game.batch;
        font = game.font;

        // Create the player
        atlas = new TextureAtlas(Gdx.files.internal("characters/"+character+".atlas"));
        player = new Player(atlas, (TiledMapTileLayer)map.getLayers().get("Collisions"));
        player.setTexture(character+"sd");
        // Multiply by 16 as all assets are 16 bit
        player.setPosition(400, 400);

        // Create the activities on the map
        study = new Study();
        study.set(39*16, 4*16, 2*16, 16);

        recreation = new Recreation();
        recreation.set(11*16, 8*16, 2*16, 2*16);

        eat = new Eat();
        eat.set(35*16, 39*16, 2*16, 16);

        sleep = new Sleep();
        sleep.set(11*16, 35*16, 2*16, 16);

        // Set the game's state
        state = GameState.FREE_ROAM;
    }

    @Override
    public void show() {
        System.out.println("Game showing");
        renderer.setView(overviewCam);
        renderer.render();
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


        if (state == GameState.FREE_ROAM) {
            // Check if player is hovering over an activity
            // and open a new activity screen
            if (player.getBoundingRectangle().overlaps(eat.zone)) {
                Activity.type = Activity.ActivityType.EAT;
                System.out.println("Player wants to eat");
                TimeUse = 1;
                Energy = 10;
                player.setPosition(400, 400);
                game.setScreen(new ActivityScreen(game, eat));
            } else if (player.getBoundingRectangle().overlaps(study.zone)) {
                Activity.type = Activity.ActivityType.STUDY;
                System.out.println("Player wants to study");
                TimeUse = 4;
                Energy = 50;
                game.setScreen(new ActivityScreen(game, study));
            } else if (player.getBoundingRectangle().overlaps(sleep.zone)) {
                Activity.type = Activity.ActivityType.SLEEP;
                System.out.println("Player wants to sleep");
                TimeUse = 16;
                Energy = 0;
                game.setScreen(new ActivityScreen(game, sleep));
            } else if (player.getBoundingRectangle().overlaps(recreation.zone)) {
                Activity.type = Activity.ActivityType.RECREATION;
                System.out.println("Player wants to feed the ducks");
                TimeUse = 2;
                Energy = 20;
                game.setScreen(new ActivityScreen(game, recreation));
            }
        }

        batch.begin();
        player.draw(batch);
        batch.draw(blank,55,783,200,10);
        batch.draw(orange,55,783,200*(HeslingtonHustle.Energy/100),10);
        font.draw(batch, "Energy", 5, 795);
        batch.end();
    }

    // Getter and Setter of game's state
    public void setState(GameState newState) {
        state = newState;
    }
    public GameState getState() {
        return state;
    }

    @Override
    public void resize(int width, int height) {
        overviewCam.setToOrtho(false, width/16f, height/16f);
    }

    @Override
    public void pause() {
        System.out.println("Game paused");
    }

    @Override
    public void resume() {
        System.out.println("Game resuming");
        state = GameState.FREE_ROAM;
    }

    @Override
    public void hide() {
        System.out.println("Game hiding");
    }
}