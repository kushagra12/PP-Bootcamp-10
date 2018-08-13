package com.paypal.bootcamp;

public class Card{
    Suit suit;
    Pip pip;

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public Pip getPip() {
        return pip;
    }

    public void setPip(Pip pip) {
        this.pip = pip;
    }

    public Card(Suit suit, Pip pip) {
        this.suit = suit;
        this.pip = pip;
    }

    public boolean equals(Card card) {
        return card.pip == this.pip && card.suit == this.suit;
    }
}
