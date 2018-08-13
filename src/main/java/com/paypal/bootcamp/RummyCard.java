package com.paypal.bootcamp;

public class RummyCard extends Card{
    boolean isWildCard;

    public RummyCard(Suit suit, Pip pip) {
        super(suit, pip);
    }

    public int getValue() {
        return 13 * suit.ordinal() + pip.ordinal();
    }

    public boolean isWildCard() {
        return isWildCard;
    }

    public void setWildCard(boolean wildCard) {
        isWildCard = wildCard;
    }

    public int compareSuits(Card c2){
        if (this.suit.ordinal() - c2.suit.ordinal()==0){
            return this.pip.ordinal() - c2.pip.ordinal();
        }
        return  this.suit.ordinal() - c2.suit.ordinal();
    }

    public int compareValue(Card c2){
        if (this.pip.ordinal() == c2.pip.ordinal()){
            return this.suit.ordinal() - c2.suit.ordinal();
        }
        return  this.pip.ordinal() - c2.pip.ordinal();
    }

    public boolean isSameRankDiffSuite (Card c2){
        return this.suit.ordinal() == c2.suit.ordinal() && this.pip.ordinal() == c2.pip.ordinal();
    }

    public boolean isDiffEqual (Card smallerCard, int diff){
        return this.suit.ordinal() == smallerCard.suit.ordinal() && this.pip.ordinal() ==smallerCard.pip.ordinal() + diff;
    }
}
