package com.heslington_hustle.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.heslington_hustle.game.Heslington_hustle;

// This is the screen that will show the actual game
public class GameScreen implements Screen {
    final Heslington_hustle game;
    static final int WORLD_WIDTH = 50;
    static final int WORLD_HEIGHT = 50;

    // fetch the assets
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private Texture playerImage;

    public GameScreen(final Heslington_hustle game) {
        this.game = game;

        camera = new OrthographicCamera();
    }

    @Override
    public void show() {
        // load the tiled map
        map = new TmxMapLoader().load("map1.tmx");
        // create the renderer
        renderer = new OrthogonalTiledMapRenderer(map, 1/16f);
        renderer.setView(camera);
        // Create camera
        camera.update();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setView(camera);
        renderer.render();
        camera.update();
    }

    @Override
    public void resize(int width, int height) {
//        camera.setToOrtho(false, width/16f, height/16f);
        camera.viewportWidth = width;
        camera.viewportHeight = height;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
    }
}
