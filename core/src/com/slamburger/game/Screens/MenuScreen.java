package com.slamburger.game.Screens;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.slamburger.game.SlamburgerGame;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by 123 on 3/10/2017.
 */

public class MenuScreen implements Screen {
    final SlamburgerGame game;
    OrthographicCamera camera;
    Texture img;
    public MenuScreen(SlamburgerGame game){
        img = new Texture("menuimg.jpg");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        this.game = game;
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(img, 0, 0);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
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

    }

    @Override
    public void dispose() {

    }
}
