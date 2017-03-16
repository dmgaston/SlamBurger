package com.slamburger.game.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by 123 on 3/13/2017.
 */

public class Topping {

    final private boolean isBad;
    final private String imageLocation;
    Texture texture;
    public Topping(boolean isBad, String imageLocation){
        this.isBad = isBad;
        this.imageLocation = imageLocation;
        texture = new Texture(Gdx.files.internal(imageLocation));

    }
    public Texture getTexture(){
         
        return texture;
    }
    public boolean isToppingBad(){
        return isBad;
    }
}
