package com.slamburger.game.GameObjects;


import java.util.Random;

public class Deck {


    int size;
    int badCardsInDeck;
    public Topping cards[];
    int dealIndex = 0;
    int badCardPicker;
    int goodCardPicker;
    int swapIndex;
    Topping topping;
    public Deck(int size, int badCardsInDeck){
        this.size = size;
        this.badCardsInDeck = badCardsInDeck;
        cards = new Topping[size];
        topping = new Topping(false, "monkey.png");
        Random rand = new Random();
       //cards[0] = topping;
        /*for(int i = 0; i < size-1; i++){
            topping = new Topping(false, "tomato.png");
            cards[i] = topping;
        }*/
        /*for(int i = 0; i < badCardsInDeck; i++ ){
            badCardPicker = rand.nextInt(4);

            switch (badCardPicker){
                case 0:
                topping = new Topping(true, "biohazard.png");
                    break;
                case 1:
                    topping = new Topping(true, "monkey.png");
                    break;
                case 2:
                    topping = new Topping(true, "monkey.png");
                    break;
                case 3:
                    topping = new Topping(true, "biohazard.png");
                    break;
                case 4:
                    topping = new Topping(true, "biohazard.png");
                    break;
            }


            cards[i] = topping;

        }*/
        for (int j =0; j < size; j ++){

                goodCardPicker = rand.nextInt(4);
                switch(goodCardPicker){
                    case 0:
                        topping = new Topping(false, "lettuce.png");
                        break;
                    case 1:
                        topping = new Topping(false, "biohazard.png");
                        break;
                    case 2:
                        topping = new Topping(false, "pickle.png");
                        break;
                    case 3:
                        topping = new Topping(false, "monkey.png");
                        break;
                    case 4:
                        topping = new Topping(false, "tomato.png");
                        break;
                }
                cards[j] = topping;


        }
        shuffle();


    }


    public void shuffle(){
        Random rand = new Random();
        Topping temp;
        for(int i = 0; i < size; i++){
            temp=cards[i];
            swapIndex = rand.nextInt(size);
            cards[i] = cards[swapIndex];
            cards[swapIndex] = temp;
        }
    }
    public Topping deal(){

        return cards[dealIndex++];
    }
    public boolean hasCard(){
        if(dealIndex < size) return true;
        return false;
    }

}
