package com.paypal.bootcamp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Pack <C extends Card>{

    List<C> cards;

    public Pack(boolean containsJoker){
        cards = new ArrayList<C>();
        for (Pip p : Pip.values()) {
            for (Suit s : Suit.values()) {
                C card = generate(s, p);
                if (!card.isJoker())
                    cards.add(card);
            }
        }
        if (containsJoker) {
            cards.add(generate(Suit.JOKER, Pip.JOKER));
            cards.add(generate(Suit.JOKER, Pip.JOKER));
        }
    }

    public abstract C generate(Suit s, Pip p);

    public List<C> getCards() {
        return cards;
    }
}
