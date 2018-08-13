package com.paypal.bootcamp;

import java.util.Collections;
import java.util.List;

public class Deck {

    List<Card> cards;

    Deck (Integer numberPacks, boolean withJokers) {

        for (int i = 0; i < numberPacks; i++) {
            cards.addAll(new Pack(withJokers).getCards());
        }

    }

    public void shuffle (){
        Collections.shuffle(this.cards);
    }

    public List<Card> getNCards (Integer n) {
        if ( n > cards.size())
            n = cards.size()-1;

        List<Card> nCards =  cards.subList(0, n);
        cards.removeAll(nCards);
        return nCards;

    }

}
