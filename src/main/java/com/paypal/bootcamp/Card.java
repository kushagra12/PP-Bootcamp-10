package com.paypal.bootcamp;

public abstract class Card implements Comparable<Card>{
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
    public abstract int getValue();

    public int compareTo(Card card) {
        return this.getValue() - card.getValue();
    }

    public boolean isJoker(){
        return pip.equals(Pip.JOKER) || suit.equals(Suit.JOKER);
    }

    public boolean isSameSuite(Card c){
        return this.suit == c.getSuit();
    }

    public boolean isSamePip(Card c){
        return this.pip == c.getPip();
    }

    @Override
    public String toString() {
        char suitsChar = '?';
        switch (suit) {
            case SPADE:
                suitsChar = ((char)'\u2660');
                break;
            case DIAMOND:
                suitsChar = ((char)'\u2666');
                break;
            case CLUB:
                suitsChar = ((char)'\u2663');
                break;
            case HEART:
                suitsChar = ((char)'\u2764');
                break;
            case JOKER:
                suitsChar = 'J';
                break;
        }
        return pip + " " + String.valueOf(suitsChar);
    }
}
