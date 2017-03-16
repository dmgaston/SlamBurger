package com.slamburger.game.Screens;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.slamburger.game.SlamburgerGame;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by 123 on 3/10/2017.
 */

public class MenuScreen implements Screen {
    final SlamburgerGame game;
    OrthographicCamera camera;
    Texture img;
    FitViewport fitViewPort;
    BitmapFont bmf;
    public MenuScreen(SlamburgerGame game){
        img = new Texture("tomato.png");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        fitViewPort = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        bmf = new BitmapFont();
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
        //game.batch.enableBlending();
        bmf.getData().setScale(5f,5f);
        game.batch.begin();
        //game.batch.draw(img, camera.position.x - (img.getWidth()/2), camera.position.y - (img.getHeight()/2));
        bmf.draw(game.batch, "MAIN MENU", 200, 400);

        game.batch.end();

        if (Gdx.input.justTouched()) {
            //if(Gdx.input.getX() <100 && Gdx.input.getY() <100){
                game.setScreen(new GameScreen(game));
                dispose();
           // }

        }
    }

    @Override
    public void resize(int width, int height) {
        fitViewPort.update(width, height, true);
        camera.update();
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
