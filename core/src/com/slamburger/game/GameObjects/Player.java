package com.slamburger.game.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by 123 on 3/13/2017.
 */

public class Player {

    int buns = 2;
    int points = 0;
    public Player(){

    }


    public boolean hasBuns(){
        if(buns != 0){
            return true;
        }
        return false;
    }
    public void useBun(){
         buns -= 1;
    }
    public void addPoints(int points){
        this.points += points;
    }
    public int getPoints(){

        return points;
    }
    public int bunLeft(){
        return buns;
    }


}
