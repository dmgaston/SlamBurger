package com.slamburger.game.Screens;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.slamburger.game.GameObjects.PreferencesLoader;
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



    BitmapFont bmf;
    int highScore;
    PreferencesLoader preferencesLoader;
    Texture background;

    public MenuScreen(SlamburgerGame game){


        bmf = new BitmapFont();
        bmf.setColor(Color.BLACK);
        bmf.getData().setScale(5f,5f);
        this.game = game;
        background = new Texture(Gdx.files.internal("chimp.png"));
        /*get the saved high score*/
        preferencesLoader = new PreferencesLoader();
        highScore = preferencesLoader.getHighScore();



    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);





        game.batch.begin();
        game.batch.draw(background, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        /*write the words sort of in the middle, will switch to labels and buttons on a table
        * in the future*/
        bmf.draw(game.batch, "TAP TO START", 275, Gdx.graphics.getHeight()/2);
        bmf.draw(game.batch, "SlamBurger", 340, Gdx.graphics.getHeight()/1-190);
        bmf.draw(game.batch, "HIGHSCORE: "+highScore, 269, Gdx.graphics.getHeight()/2-70);



        if (Gdx.input.justTouched()) {
            /*if screen is touched, switch to the game screen*/

            game.setScreen(new GameScreen(game));
            dispose();




        }
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

    }

    @Override
    public void dispose() {

    }
}
