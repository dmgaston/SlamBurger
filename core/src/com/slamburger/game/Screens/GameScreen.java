package com.slamburger.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
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

    Topping top;
    BitmapFont bmf;

    Player player;
    FitViewport fitViewPort;
    FitViewport backgroundFVP;
    Texture bkgrdTexture;
    Deck deck;
    int backgroundIndex = 0;
    String[] backgrounds = {"atoms.png", "bloodcells.png", "waterdrops.png", "basketballs.png", "earth.png", "sun.png", "supernova.png", "milkyway.png"};


    public GameScreen(SlamburgerGame game){

        this.game = game;
        toppingImg = new Texture(Gdx.files.internal("tomato.png"));
        bottomBun = new Texture(Gdx.files.internal("bottom.png"));




        deck = new Deck(50, 13);
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
        bmf = new BitmapFont();
        bmf.getData().setScale(5f,5f);





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
        bkgrdTexture = new Texture(Gdx.files.internal(backgrounds[backgroundIndex]));
        game.batch.draw(bkgrdTexture,camera.position.x - (bkgrdTexture.getWidth()/2), camera.position.y - (bkgrdTexture.getHeight()/2));
        game.batch.end();

        game.batch.setProjectionMatrix(camera.combined);


        //background.draw(game.batch);

        game.batch.begin();

        game.batch.draw(bottomBun,camera.position.x - (bottomBun.getWidth()/2), camera.position.y - (bottomBun.getHeight()/2));

        for (Topping topping : toppings) {
            game.batch.draw(topping.getTexture(), camera.position.x - (toppingImg.getWidth()/2), camera.position.y - (toppingImg.getHeight()/2));
            top = topping;

        }

       /* if (top.isToppingBad()){
            float delay = 1; // seconds

            Timer.schedule(new Timer.Task(){
                @Override
                public void run() {
                    toppings.clear();
                }
            }, delay);
        }*/


        bmf.draw(game.batch, "SCORE: "+ player.getPoints(), 50  , 100);
        bmf.draw(game.batch, "CARDS LEFT: "+ deck.cardsInDeck(), 50  , 170);
        bmf.draw(game.batch, "BUNS LEFT: "+ player.bunLeft(), 50  , Gdx.graphics.getHeight()-50);
        game.batch.end();
        handleInput();


        if (TimeUtils.nanoTime()/1000000000 - lastToppingTime > 1)

            spawnTopping();
        if(!player.hasBuns() || !deck.hasCard()) {
            float delay = 1; // seconds

            Timer.schedule(new Timer.Task(){
                @Override
                public void run() {
                    game.setScreen(new MenuScreen(game));
                }
            }, delay);

        }



    }

    @Override
    public void resize(int width, int height) {
        backgroundFVP.update(width, height, true);
        backgroundCam.update();
        fitViewPort.update(width, height, true);
        camera.update();

    }

    private void spawnTopping() {

    if(top == null || !top.isToppingBad()) {
        if (deck.hasCard()) {
            toppings.add(deck.deal());
            burgerSize++;
            lastToppingTime = TimeUtils.nanoTime() / 1000000000;
            backgroundIndex++;


        }
    }else{
        toppings.clear();
        top = null;
        burgerSize = 0;
        backgroundIndex = 0;
    }




    }
    public void handleInput(){
        if(Gdx.input.justTouched() && !top.isToppingBad() && player.hasBuns()){

            player.addPoints(burgerSize);
            toppings.clear();
            burgerSize = 0;
            backgroundIndex = 0;
            top = null;
            player.useBun();




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
