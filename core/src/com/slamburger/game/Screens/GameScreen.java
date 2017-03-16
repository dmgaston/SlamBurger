package com.slamburger.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
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
    ArrayList<Topping> toppings;
    int top;
    long lastToppingTime;
    int foodGathered;
    int burgerSize;
    int zoomVal;
    Player player;
    FitViewport fitViewPort;

    public GameScreen(SlamburgerGame game){

        this.game = game;
        toppingImg = new Texture(Gdx.files.internal("tomato.png"));
        bottomBun = new Texture(Gdx.files.internal("bottom.png"));

        top = 50;



        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        camera.position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
        camera.update();

        fitViewPort = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);



        // create the raindrops array and spawn the first raindrop
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


        // clear the screen with a dark blue color. The
        // arguments to glClearColor are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.
        Gdx.gl.glClearColor(135/255f, 206/255f, 235/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell the camera to update its matrices.
        camera.update();

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        game.batch.setProjectionMatrix(camera.combined);

        // begin a new batch and draw the bucket and
        // all drops
        game.batch.begin();

        game.batch.draw(bottomBun,camera.position.x - (bottomBun.getWidth()/2), camera.position.y - (bottomBun.getHeight()/2));
        for (Topping topping : toppings) {
            game.batch.draw(topping.getTexture(), camera.position.x - (toppingImg.getWidth()/2), camera.position.y - (toppingImg.getHeight()/2));
        }
        game.batch.end();
        handleInput();

        // check if we need to create a new raindrop
        if (TimeUtils.nanoTime()/1000000000 - lastToppingTime > 1)

            spawnTopping();

        // move the raindrops, remove any that are beneath the bottom edge of
        // the screen or that hit the bucket. In the later case we increase the
        // value our drops counter and add a sound effect.
        //Iterator<Rectangle> iter = toppings.iterator();
    }

    @Override
    public void resize(int width, int height) {
        fitViewPort.update(width, height, true);
        camera.update();

    }

    private void spawnTopping() {

        Topping tp = new Topping(false, "tomato.png");
        Random rand = new Random();
        int nextTopping = rand.nextInt(4);
        switch (nextTopping){
            case 0:
                tp = new Topping(false, "tomato.png");
                break;
            case 1:
                tp = new Topping(false, "lettuce.png");
                break;
            case 2:
                tp = new Topping(false, "pickle.png");
                break;
            case 3:
                tp = new Topping(true, "biohazard.png");
                break;
        }
        toppings.add(tp);
        burgerSize++;
        lastToppingTime = TimeUtils.nanoTime()/1000000000;

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
       // bucketImage.dispose();
        //dropSound.dispose();
        //rainMusic.dispose();
    }
}
