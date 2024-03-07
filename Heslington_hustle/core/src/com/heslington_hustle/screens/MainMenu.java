package com.heslington_hustle.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.heslington_hustle.game.Heslington_hustle;

public class MainMenu implements Screen {

    private final int w = Gdx.graphics.getWidth();
    private final int h = Gdx.graphics.getHeight();
    private final Heslington_hustle game;

    public MainMenu (final Heslington_hustle game) {
        this.game = game;
    }
    @Override
    public void show() {
        System.out.println("Main menu showing");
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.SPACE) {
                    game.setScreen(new GameScreen(game));
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.25f, 1);

        game.batch.begin();
        game.font.draw(game.batch, "Welcome to Heslington Hustle!", w * .25f, h * .75f);
        game.font.draw(game.batch, "Press space to play!", w * .25f, h * .5f);
        game.batch.end();
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
        System.out.println("Main menu hiding");
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
    }
}
