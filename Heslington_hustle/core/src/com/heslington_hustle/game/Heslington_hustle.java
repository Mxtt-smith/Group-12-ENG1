package com.heslington_hustle.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class Heslington_hustle extends ApplicationAdapter {
	private Texture player;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Rectangle playerModel;

	@Override
	public void create() {
		// load the images for the droplet and the bucket, 64x64 pixels each
		player = new Texture(Gdx.files.internal("player.png"));

		// create the camera and the SpriteBatch
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();

		// create a Rectangle to logically represent the bucket
		playerModel = new Rectangle();
		playerModel.x = 800 / 2 - 64 / 2; // center the player horizontally
		playerModel.y = 480 / 2 - 64 / 2; // center the player vertically
		playerModel.width = 64;
		playerModel.height = 64;

	}
	@Override
	public void render() {
		// clear the screen with a dark blue color. The
		// arguments to clear are the red, green
		// blue and alpha component in the range [0,1]
		// of the color to be used to clear the screen.
		ScreenUtils.clear(0, 0, 0.2f, 1);

		// tell the camera to update its matrices.
		camera.update();

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
		if(playerModel.y > 480 - 64) playerModel.y = 480 - 64;
	}

	@Override
	public void dispose() {
		// dispose of all the native resources
		player.dispose();
		batch.dispose();
	}
}
