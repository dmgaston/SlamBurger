package com.slamburger.game.Screens;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.slamburger.game.SlamburgerGame;
import com.badlogic.gdx.graphics.OrthographicCamera;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by 123 on 3/10/2017.
 */

public class MenuScreen implements Screen {
    final SlamburgerGame game;
    OrthographicCamera camera;
    Texture img;
    FitViewport fitViewPort;
    BitmapFont bmf;
    int highScore = 0;
    public MenuScreen(SlamburgerGame game){
        img = new Texture("tomato.png");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        fitViewPort = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        bmf = new BitmapFont();
        this.game = game;
        /*try{
            Scanner scanner = new Scanner(new File("highscore.txt"));
            highScore = scanner.nextInt();
            scanner.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }*/


    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        bmf.getData().setScale(5f,5f);
        game.batch.begin();

         bmf.draw(game.batch, "TAP TO START", Gdx.graphics.getWidth()+50, Gdx.graphics.getHeight()/2);
         bmf.draw(game.batch, "HIGHSCORE: "+highScore, Gdx.graphics.getWidth()+50, Gdx.graphics.getHeight()/2-50);



        if (Gdx.input.justTouched()) {


            game.setScreen(new GameScreen(game));
            dispose();




        }
        game.batch.end();
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
