package com.slamburger.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.slamburger.game.GameObjects.Deck;
import com.slamburger.game.GameObjects.Player;
import com.slamburger.game.GameObjects.Topping;
import com.slamburger.game.SlamburgerGame;

import java.util.ArrayList;
import java.util.Random;


public class GameScreen implements Screen {
    final SlamburgerGame game;
    Texture toppingImg;
    Texture bottomBun;
    OrthographicCamera camera;
    OrthographicCamera backgroundCam;
    ArrayList<Topping> toppings;

    long lastToppingTime;
    int foodGathered;
    int burgerSize;
    int zoomVal;
    Player player;
    FitViewport fitViewPort;
    FitViewport backgroundFVP;
    Texture bkgrdTexture;
    Deck deck;


    public GameScreen(SlamburgerGame game){

        this.game = game;
        toppingImg = new Texture(Gdx.files.internal("tomato.png"));
        bottomBun = new Texture(Gdx.files.internal("bottom.png"));



        bkgrdTexture = new Texture(Gdx.files.internal("background.png"));

        deck = new Deck(5, 3);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        camera.position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
        camera.update();
        backgroundCam = new OrthographicCamera();
        backgroundCam.setToOrtho(false, 800, 480);
        backgroundCam.position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
        backgroundCam.update();

        fitViewPort = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        backgroundFVP = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), backgroundCam);




        toppings = new ArrayList<Topping>();
        spawnTopping();
        burgerSize = 0;
        player = new Player();


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {



        Gdx.gl.glClearColor(135/255f, 206/255f, 235/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell the camera to update its matrices.
        camera.update();
        backgroundCam.update();

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        game.batch.setProjectionMatrix(backgroundCam.combined);
        game.batch.begin();

        game.batch.draw(bkgrdTexture,camera.position.x - (bkgrdTexture.getWidth()/2), camera.position.y - (bkgrdTexture.getHeight()/2));
        game.batch.end();
        game.batch.setProjectionMatrix(camera.combined);


        //background.draw(game.batch);

        game.batch.begin();

        game.batch.draw(bottomBun,camera.position.x - (bottomBun.getWidth()/2), camera.position.y - (bottomBun.getHeight()/2));
        for (Topping topping : toppings) {
            game.batch.draw(topping.getTexture(), camera.position.x - (toppingImg.getWidth()/2), camera.position.y - (toppingImg.getHeight()/2));
        }


        game.batch.end();
        handleInput();


        if (TimeUtils.nanoTime()/1000000000 - lastToppingTime > 1)

            spawnTopping();


    }

    @Override
    public void resize(int width, int height) {
        backgroundFVP.update(width, height, true);
        backgroundCam.update();
        fitViewPort.update(width, height, true);
        camera.update();

    }

    private void spawnTopping() {


        if(deck.hasCard())
        toppings.add(deck.deal());
        burgerSize++;
        lastToppingTime = TimeUtils.nanoTime()/1000000000;
        backgroundCam.zoom += 2f;


    }
    public void handleInput(){
        if(Gdx.input.justTouched()){
            foodGathered += burgerSize;
            toppings.clear();
            burgerSize = 0;

        }
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
        toppingImg.dispose();

    }
}
