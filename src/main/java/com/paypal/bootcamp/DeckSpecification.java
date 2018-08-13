package com.paypal.bootcamp;

public interface DeckSpecification<C extends Card> {
    int noOfPacks();
    boolean containJoker();
    int getJokerValue();
    C generate(Suit s, Pip p);
}
