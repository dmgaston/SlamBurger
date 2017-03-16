package com.slamburger.game.GameObjects;


import java.util.Random;

public class Round {

    int badToppingFrequency;
    int deckSize;

    public Round(int deckSize){
        this.deckSize = deckSize;
        Random rand = new Random();
        badToppingFrequency = rand.nextInt(8-5)+5;

    }
    /*public Topping dealNext(){
        deckSize--;

        return;
    }*/
    public boolean roundOver(){
        if(deckSize == 0){
            return true;
        }

        return false;
    }

}
