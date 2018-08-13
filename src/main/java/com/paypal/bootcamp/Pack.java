package com.paypal.bootcamp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pack{

    List<Card> cards;

    public boolean isJoker (Pip p, Suit s){
        return p.equals(Pip.JOKER) || s.equals(Suit.JOKER);
    }

    public Pack(boolean containsJoker){
        cards = new ArrayList<Card>();
        for (Pip p : Pip.values()){
            for (Suit s : Suit.values()){
                if (!isJoker(p, s)){
                    cards.add(new Card(s, p));
                }
            }
        }
        if (containsJoker) {
            cards.add(new Card(Suit.JOKER, Pip.JOKER));
            cards.add(new Card(Suit.JOKER, Pip.JOKER));
        }
    }
}
