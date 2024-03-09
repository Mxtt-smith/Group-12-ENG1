package com.heslington_hustle.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.heslington_hustle.game.Heslington_hustle;
import com.heslington_hustle.game.Player;

// This is the screen that will show the actual game
public class GameScreen implements Screen {
    final Heslington_hustle game;
    // fetch the assets
    private final TiledMap map;
    private final OrthogonalTiledMapRenderer renderer;
    private final SpriteBatch batch;
    private final OrthographicCamera overviewCam;
    private final TextureAtlas atlas;
    Player player;

    public GameScreen(final Heslington_hustle game) {
        this.game = game;

        overviewCam = new OrthographicCamera();
        overviewCam.setToOrtho(false, 50, 50);

        // load the tiled map
        map = new TmxMapLoader().load("map1.tmx");
        // create the spriteBatch
        batch = new SpriteBatch();
        // create the renderer
        renderer = new OrthogonalTiledMapRenderer(map, 1/16f);

        atlas = new TextureAtlas(Gdx.files.internal("characters/char1.atlas"));
        player = new Player(atlas);
        player.setTexture("char1sd");
        // Multiply by 16 as all assets are 16 bit
        player.setPosition(12*16, (50-15)*16);
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
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.moveLeft();
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.moveRight();
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.moveUp();
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.moveDown();
        } else {
            player.stationary();
        }

        batch.begin();
        player.draw(batch);
        batch.end();
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
    }

    @Override
    public void hide() {
        System.out.println("Game hiding");
        dispose();
    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        atlas.dispose();
        batch.dispose();
    }
}