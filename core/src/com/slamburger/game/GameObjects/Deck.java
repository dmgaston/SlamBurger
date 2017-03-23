package com.slamburger.game.GameObjects;


import java.util.Random;

public class Deck {


    int size;
    int percentBad;

    int dealIndex = 0;

    int cardPicker;

    Topping topping;
    Random rand;
    int cardsDealt;
    public Deck(int size, int percentBad){
        this.size = size;
        this.percentBad = percentBad;

        /*topping gets initialized with arbitrary values; they will be changed when we deal*/
        topping = new Topping(true, "monkey.png");
        rand = new Random();
        cardsDealt = 0;

    }



    public Topping deal(){

        int randNum = rand.nextInt(100)+1;
      /*  Picks a random number up to 100, if lower than percentBad, we pick a bad card,
      * if higher, we pick a good card*/
        if(randNum < percentBad){
            /*Randomly pick one of the bad cards we have in the assets folder*/
            cardPicker = rand.nextInt(4)+1;
            switch(cardPicker){
                case 0:
                    topping = new Topping(true, "spider.png");
                    break;
                case 1:
                    topping = new Topping(true, "biohazard.png");
                    break;
                case 2:
                    topping = new Topping(true, "radioactive.png");
                    break;
                case 3:
                    topping = new Topping(true, "skull.png");
                    break;
                case 4:
                    topping = new Topping(true, "monkey.png");
                    break;
            }
        }else{
            /*Pick a good card*/
            cardPicker = rand.nextInt(4)+1;
            switch(cardPicker){
                case 0:
                    topping = new Topping(false, "lettuce.png");
                    break;
                case 1:
                    topping = new Topping(false, "sausage.png");
                    break;
                case 2:
                    topping = new Topping(false, "pickle.png");
                    break;
                case 3:
                    topping = new Topping(false, "egg.png");
                    break;
                case 4:
                    topping = new Topping(false, "tomato.png");
                    break;
            }
        }
        cardsDealt++;
        return topping;
    }
    public boolean hasCard(){
        if(dealIndex < size) return true;
        return false;
    }
    /*this may be used for an on screen counter later*/
    public int cardsInDeck(){
        return size -cardsDealt;
    }

}
