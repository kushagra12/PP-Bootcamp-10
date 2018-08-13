package com.paypal.bootcamp;

public class SimpleCard extends Card {

    public SimpleCard(Suit suit, Pip pip) {
        super(suit, pip);
    }

    public int getValue() {
        return 0;
    }
}
