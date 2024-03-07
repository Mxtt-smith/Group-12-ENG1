package com.heslington_hustle.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
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
    // the world width and height are 50
    int worldBlockRatioW = Gdx.graphics.getWidth() / 16;
    int worldBlockRatioH = Gdx.graphics.getHeight() / 16;
    int w = Gdx.graphics.getWidth();
    int h = Gdx.graphics.getHeight();
    // fetch the assets
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private SpriteBatch batch;
    private final OrthographicCamera camera;
    private TextureAtlas atlas;
    Player player;

    public GameScreen(final Heslington_hustle game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 50, 50);

        // load the tiled map
        map = new TmxMapLoader().load("map1.tmx");
        // create the spriteBatch
        batch = new SpriteBatch();
        // create the renderer
        renderer = new OrthogonalTiledMapRenderer(map, 1/16f);

        atlas = new TextureAtlas(Gdx.files.internal("characters/char1.atlas"));
        player = new Player(atlas);
        player.setTexture("char1sd");
        player.setPosition(12*16, (50-15)*16);
    }

    @Override
    public void show() {
        System.out.println("Game showing");
        renderer.setView(camera);
        renderer.render();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);

        camera.update();
        renderer.setView(camera);
        renderer.render();

        batch.begin();
        player.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width/16f, height/16f);
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
    }
}
