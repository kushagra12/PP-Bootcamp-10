package com.paypal.bootcamp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck<T extends Card>{

    List<T> cards;

    public Deck(final DeckSpecification<T> deckSpecification) {
        cards = new ArrayList<T>();
        for (int i = 0; i < deckSpecification.noOfPacks(); i++) {
            Pack<T> pack = new Pack<T>(deckSpecification.getJokerValue() > 1) {
                @Override
                public T generate(Suit s, Pip p) {
                    return deckSpecification.generate(s, p);
                }
            };

            cards.addAll(pack.getCards());
        }

    }

    public void shuffle (){
        Collections.shuffle(this.cards);
    }

    public List<T> getNCards (Integer n) {
        if ( n > cards.size())
            n = cards.size()-1;

        List<T> nCards =  cards.subList(0, n);
        cards = cards.subList(n, cards.size());
        return nCards;

    }

}
