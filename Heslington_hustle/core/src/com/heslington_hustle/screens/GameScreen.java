package com.heslington_hustle.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.heslington_hustle.game.Player;
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
import static com.heslington_hustle.game.Player.character;
import static com.heslington_hustle.game.Heslington_hustle.Energy;

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
    Texture blank;
    Texture orange;
    BitmapFont font = new BitmapFont();

    public GameScreen(final Heslington_hustle game) {
        this.game = game;

        overviewCam = new OrthographicCamera();
        //overviewCam.setToOrtho(false, 50, 50);

        orange = new Texture("orange.png");
        blank = new Texture("blank.png");

        // load the tiled map
        map = new TmxMapLoader().load("map1.tmx");

        // create the spriteBatch
        batch = new SpriteBatch();
        // create the renderer
        renderer = new OrthogonalTiledMapRenderer(map, 1/16f);
        renderer.setView(overviewCam);

        atlas = new TextureAtlas(Gdx.files.internal("characters/"+character+".atlas"));
        player = new Player(atlas, (TiledMapTileLayer)map.getLayers().get("Collisions"));
        player.setTexture(character+"sd");
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


        batch.begin();
        player.draw(batch);
        batch.draw(blank,55,783,200,10);
        batch.draw(orange,55,783,200*(Energy/100),10);
        font.draw(batch, "Energy", 5, 795);
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