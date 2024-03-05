package com.heslington_hustle.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class Heslington_hustle extends ApplicationAdapter {
	private Texture player;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Rectangle playerModel;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;


	@Override
	public void create() {
		// load the image for player, 64x64 pixels
		player = new Texture(Gdx.files.internal("char1d.png"));

		// load the map
		map = new TmxMapLoader().load("map1.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);

		// create the camera and the SpriteBatch
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 800);
		batch = new SpriteBatch();

		// create a Rectangle to logically represent the bucket
		playerModel = new Rectangle();
		playerModel.x = 800 / 2 - 64 / 2; // center the player horizontally
		playerModel.y = 800 / 2 - 64 / 2; // center the player vertically
		playerModel.width = 64;
		playerModel.height = 64;

	}
	@Override
	public void render() {

		// clear the screen
		ScreenUtils.clear(0.7f, 0.7f, 1.0f, 1);

		// tell the camera to update its matrices.
		camera.update();

		renderer.setView(camera);
		renderer.render();

		// tell the SpriteBatch to render in the
		// coordinate system specified by the camera.
		batch.setProjectionMatrix(camera.combined);

		// begin a new batch and draw the player
		batch.begin();
		batch.draw(player, playerModel.x, playerModel.y);
		batch.end();

		// process user input
		if(Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			playerModel.x = touchPos.x - 64 / 2;
			playerModel.y = touchPos.y - 64 / 2;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) playerModel.x -= 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) playerModel.x += 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.UP)) playerModel.y += 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) playerModel.y -= 200 * Gdx.graphics.getDeltaTime();

		// make sure the bucket stays within the screen bounds
		if(playerModel.x < 0) playerModel.x = 0;
		if(playerModel.x > 800 - 64) playerModel.x = 800 - 64;
		if(playerModel.y < 0) playerModel.y = 0;
		if(playerModel.y > 800 - 64) playerModel.y = 480 - 64;
	}

	@Override
	public void dispose() {
		// dispose of all the native resources
		player.dispose();
		batch.dispose();
	}
}