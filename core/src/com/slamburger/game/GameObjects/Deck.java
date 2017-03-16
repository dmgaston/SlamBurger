package com.slamburger.game.GameObjects;



public class Deck {


    int size;
    int badCardsInDeck;
    Topping cards[];
    public Deck(int size, int badCardsInDeck){
        this.size = size;
        this.badCardsInDeck = badCardsInDeck;
        cards = new Topping[size];
    }

    public void shuffle(){

    }
    public void deal(){

    }
    public boolean hasCard(){
        return false;
    }

}
