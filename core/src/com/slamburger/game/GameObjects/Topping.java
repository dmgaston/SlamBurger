package com.slamburger.game.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by 123 on 3/13/2017.
 */

public class Topping {

    final private boolean isBad;
    final private String imageFileName;
    Texture texture;
    public Topping(boolean isBad, String imageFileName){
        this.isBad = isBad;
        this.imageFileName = imageFileName;
        texture = new Texture(Gdx.files.internal(imageFileName));

    }
    public Texture getTexture(){
         
        return texture;
    }
    public boolean isToppingBad(){
        return isBad;
    }
}
