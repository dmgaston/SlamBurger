package com.slamburger.game.GameObjects;


import java.util.Random;

public class Deck {


    int size;
    int percentBad;
    public Topping cards[];
    int dealIndex = 0;
    int badCardPicker;
    int cardPicker;
    int swapIndex;
    Topping topping;
    Random rand;
    public Deck(int size, int percentBad){
        this.size = size;
        this.percentBad = percentBad;
        cards = new Topping[size];
        topping = new Topping(true, "monkey.png");
        rand = new Random();
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
        /*for (int j =0; j < size; j ++){

                cardPicker = rand.nextInt(4);
                switch(cardPicker){
                    case 0:
                        topping = new Topping(false, "lettuce.png");
                        break;
                    case 1:
                        topping = new Topping(true, "biohazard.png");
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
        shuffle();*/


    }


    /* public void shuffle(){

         Topping temp;
         for(int i = 0; i < size; i++){
             temp=cards[i];
             swapIndex = rand.nextInt(size);
             cards[i] = cards[swapIndex];
             cards[swapIndex] = temp;
         }
     }*/
    public Topping deal(){

        int randNum = rand.nextInt(100)+1;
        if(randNum < percentBad){
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

        return topping;
    }
    public boolean hasCard(){
        if(dealIndex < size) return true;
        return false;
    }
    public int cardsInDeck(){
        return size - dealIndex+1;
    }

}
