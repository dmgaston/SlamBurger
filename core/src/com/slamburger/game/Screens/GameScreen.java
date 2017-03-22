package com.slamburger.game.Screens;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.slamburger.game.GameObjects.Deck;
import com.slamburger.game.GameObjects.Player;
import com.slamburger.game.GameObjects.Topping;
import com.slamburger.game.SlamburgerGame;

public class GameScreen implements Screen {
    final SlamburgerGame game;




    Deck deck;
    Sprite sprite;
    Texture img;
    World world;
    Body body;
    BitmapFont bmf;
    Player player;
    ShapeRenderer shapeRenderer;

    long lastDropTime;
    int burgerSize;

    Topping topping;
    int highScore;

    public GameScreen(final SlamburgerGame game) {
        this.game = game;
        deck = new Deck(50,10);

        img = new Texture("bottom.png");
        sprite = new Sprite(img);

        // Center the sprite in the top/middle of the screen
        sprite.setPosition(Gdx.graphics.getWidth() / 2 - sprite.getWidth() / 2,
                Gdx.graphics.getHeight() / 2);




        bmf = new BitmapFont();
        bmf.getData().setScale(5f,5f);
        bmf.setColor(Color.BLACK);
        shapeRenderer = new ShapeRenderer();

        player = new Player();
        burgerSize = 0;
        topping = new Topping(false, "monkey.png");
        /*try{
            Scanner scanner = new Scanner(new File("highscore.txt"));
            highScore = scanner.nextInt();
            scanner.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }*/


    }

    private void spawnRaindrop() {
       /* Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, 800 - 64);
        raindrop.y = 480;
        raindrop.width = 64;
        raindrop.height = 64;
        raindrops.add(raindrop);*/
        lastDropTime = TimeUtils.nanoTime();
    }

    @Override
    public void render(float delta) {



        sprite.setPosition(sprite.getX(), sprite.getY()-17);
        if(sprite.getY() < -600){
            topping = deck.deal();
            sprite = new Sprite(topping.getTexture());
            sprite.setPosition(Gdx.graphics.getWidth() / 2 - sprite.getWidth() / 2,
                    Gdx.graphics.getHeight() / 2);
            burgerSize++;
        }

        if(Gdx.input.justTouched() && !topping.isToppingBad() ){
            player.addPoints(burgerSize);
            player.useBun();
            burgerSize = 0;
            topping = new Topping(false, "bottom.png");
            sprite = new Sprite(topping.getTexture());
            sprite.setPosition(Gdx.graphics.getWidth() / 2 - sprite.getWidth() / 2,
                    Gdx.graphics.getHeight() / 2);
        }
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(25, 25, 75, burgerSize*35);
        shapeRenderer.end();

        game.batch.begin();

        game.batch.draw(sprite, sprite.getX(), sprite.getY());

        bmf.draw(game.batch, "SCORE: "+ player.getPoints(),50, Gdx.graphics.getHeight()-120);
        bmf.draw(game.batch, "BUNS LEFT: "+ player.bunLeft(), 50  , Gdx.graphics.getHeight()-50);

        game.batch.end();


        if(topping.isToppingBad()){
            burgerSize = 0;
        }
        if(!deck.hasCard() || !player.hasBuns()){
           float delay = 1; // seconds
            /*try{
                Writer wr = new FileWriter("highscore.txt");
                wr.write(Integer.toString(highScore));
                wr.close();
            }catch(IOException e){
                e.printStackTrace();
            }*/

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
    }

    @Override
    public void show() {
        // start the playback of the background music
        // when the screen is shown
        //rainMusic.play();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {

    }

}