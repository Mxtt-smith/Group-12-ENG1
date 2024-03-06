package com.heslington_hustle.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

// This is the screen that will show the actual game
public class Play implements Screen {
    // fetch the assets
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    @Override
    public void show() {
        // load the tiled map
        map = new TmxMapLoader().load("map1.tmx");

        // create the renderer
        renderer = new OrthogonalTiledMapRenderer(map);
    }

    @Override
    public void render(float delta) {
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {

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
