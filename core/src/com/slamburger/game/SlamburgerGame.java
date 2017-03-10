package com.slamburger.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.slamburger.game.Screens.MenuScreen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
/*Extends Game instead of ApplicationAdapter in order to manage multiple screens*/
public class SlamburgerGame extends Game {
	public SpriteBatch batch;
	Texture img;
	public BitmapFont font;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		/*Start the game on the menu screen*/
		this.setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {

		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
