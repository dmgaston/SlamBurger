package com.slamburger.game.GameObjects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class PreferencesLoader {
/*
    A class for managing persistant game data, for now just the high score
*/
    Preferences prefs;

    public PreferencesLoader(){

        prefs = Gdx.app.getPreferences("SlamBurger");
        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }

    }
    public int getHighScore(){
        return prefs.getInteger("highScore");
    }
    public void setHighScore(int highScore){
        prefs.putInteger("highScore", highScore);
        prefs.flush();

    }
}
