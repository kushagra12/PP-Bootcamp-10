package com.paypal.bootcamp.rummy;

import com.paypal.bootcamp.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Rummy {

    List<RummyCard> hand;
    List<List<RummyCard>> pureSeqOfThree;
    List<List<RummyCard>> possibleSeqOfThree;
    List<List<RummyCard>> pureSeqOfFour;
    List<List<RummyCard>> possibleSeqOfFourCountThree;
    List<List<RummyCard>> possibleSeqOfFourCountTwo;

    public Rummy() {
        this.hand = new ArrayList<RummyCard>();
        this.pureSeqOfThree = new ArrayList<List<RummyCard>>();
        this.possibleSeqOfThree = new ArrayList<List<RummyCard>>();
        this.pureSeqOfFour = new ArrayList<List<RummyCard>>();
        this.possibleSeqOfFourCountThree = new ArrayList<List<RummyCard>>();
        this.possibleSeqOfFourCountTwo = new ArrayList<List<RummyCard>>();
    }

    public boolean isSequenceOfTwo(RummyCard c1, RummyCard c2, int diff){
        return c2.isDiffEqual(c1, diff);
    }

    public boolean isSequenceOfFour(RummyCard c1, RummyCard c2, RummyCard c3, RummyCard c4){
        return c2.isDiffEqual(c1, 1) && c3.isDiffEqual(c2, 1) && c4.isDiffEqual(c3, 1);
    }

    public boolean isSequenceOfThree(RummyCard c1, RummyCard c2, RummyCard c3){
        return c2.isDiffEqual(c1, 1) && c3.isDiffEqual(c2, 1);
    }

    public boolean isSetOfThree (RummyCard c1, RummyCard c2, RummyCard c3){
        return c1.isSameRankDiffSuite(c2) && c2.isSameRankDiffSuite(c3) && c1.isSameRankDiffSuite(c3);
    }

    public int isPureSequence(){
        return isSequence(true);
    }

    public int isPotentialSequence(){
        return isSequence(false);
    }

    public int isSequence (boolean isPure){
        int count = 0;

        List<RummyCard> tempRummyCards = new ArrayList<RummyCard>(hand);

        Collections.sort(tempRummyCards, new Comparator<RummyCard>() {
            public int compare(RummyCard o1, RummyCard o2) {
                return o1.compareSuits(o2);
            }
        });

        if (isPure){
            isSequenceLoop(tempRummyCards, true);
            isSequenceLoop(tempRummyCards, false);
        } else {
            isPotenSeqLoop(tempRummyCards, 3, true);
            isPotenSeqLoop(tempRummyCards, 3, false);
            isPotenSeqLoop(tempRummyCards, 2, false);
            isPotenSeqLoop(tempRummyCards, 1, false);
        }
        return count;
    }

    public int isPotenSeqLoop (List<RummyCard> tempRummyCards, int diff, boolean isSeqOfFour){
        int count = 0;
        int startIter = 1;
        List<List<RummyCard>> tempSeq = possibleSeqOfThree;
        if (isSeqOfFour){
            startIter = 2;
            tempSeq = possibleSeqOfFourCountThree;
        }
        if (!isSeqOfFour && diff == 3){
            tempSeq = possibleSeqOfFourCountTwo;
        }
        for (int i = startIter; i < tempRummyCards.size(); i++) {
            if (isSeqOfFour && isSequenceOfTwo(tempRummyCards.get(i - 2), tempRummyCards.get(i), diff)) {
                //System.out.println("Potential 4 - " + tempRummyCards.get(i-2) + " - " + tempRummyCards.get(i-1) + " - "+tempRummyCards.get(i));
                if (isSequenceOfTwo(tempRummyCards.get(i-2), tempRummyCards.get(i-1), 0) || isSequenceOfTwo(tempRummyCards.get(i), tempRummyCards.get(i-1), 0)){
                    continue;
                }

                List<RummyCard> tempPotenSeqlist = new ArrayList<RummyCard>();
                tempPotenSeqlist.add(tempRummyCards.get(i-2));
                tempPotenSeqlist.add(tempRummyCards.get(i-1));
                tempPotenSeqlist.add(tempRummyCards.get(i));

                tempSeq.add(tempPotenSeqlist);

                tempRummyCards.remove(i);
                tempRummyCards.remove(i-1);
                tempRummyCards.remove(i-2);

                return ++count;
            } else if (!isSeqOfFour && isSequenceOfTwo(tempRummyCards.get(i - 1), tempRummyCards.get(i), diff)) {
                //System.out.println("Potential 3 - " + tempRummyCards.get(i-1) + " - "+tempRummyCards.get(i));

                List<RummyCard> tempPotenSeqlist = new ArrayList<RummyCard>();
                tempPotenSeqlist.add(tempRummyCards.get(i-1));
                tempPotenSeqlist.add(tempRummyCards.get(i));

                possibleSeqOfThree.add(tempPotenSeqlist);

                tempRummyCards.remove(i);
                tempRummyCards.remove(i-1);

                count++;
            }
        }
        return count;
    }

    public int isSequenceLoop (List<RummyCard> tempRummyCards, boolean isSeqOfFour) {
        int count = 0;
        int startIter = 2;
        List<List<RummyCard>> tempSeq = pureSeqOfThree;
        if (isSeqOfFour){
            startIter = 3;
            tempSeq = pureSeqOfFour;
        }
        for (int i = startIter; i < tempRummyCards.size(); i++) {
            if (isSeqOfFour && isSequenceOfFour(tempRummyCards.get(i - 3), tempRummyCards.get(i - 2), tempRummyCards.get(i - 1), tempRummyCards.get(i))) {
                //System.out.println("Pure 4 - " + tempRummyCards.get(i - 3) + " - " + tempRummyCards.get(i - 2) + " - " + tempRummyCards.get(i - 1) + " - " + tempRummyCards.get(i));

                List<RummyCard> tempPureSeqlist = new ArrayList<RummyCard>();
                tempPureSeqlist.add(tempRummyCards.get(i - 3));
                tempPureSeqlist.add(tempRummyCards.get(i - 2));
                tempPureSeqlist.add(tempRummyCards.get(i - 1));
                tempPureSeqlist.add(tempRummyCards.get(i));

                tempSeq.add(tempPureSeqlist);

                tempRummyCards.remove(i);
                tempRummyCards.remove(i - 1);
                tempRummyCards.remove(i - 2);
                tempRummyCards.remove(i - 3);

                return ++count;
            } else if (!isSeqOfFour && isSequenceOfThree(tempRummyCards.get(i - 2), tempRummyCards.get(i - 1), tempRummyCards.get(i))) {
                //System.out.println("Pure 3 - " + tempRummyCards.get(i - 2) + " - " + tempRummyCards.get(i - 1) + " - " + tempRummyCards.get(i));

                List<RummyCard> tempPureSeqlist = new ArrayList<RummyCard>();
                tempPureSeqlist.add(tempRummyCards.get(i - 2));
                tempPureSeqlist.add(tempRummyCards.get(i - 1));
                tempPureSeqlist.add(tempRummyCards.get(i));

                tempSeq.add(tempPureSeqlist);

                tempRummyCards.remove(i);
                tempRummyCards.remove(i - 1);
                tempRummyCards.remove(i - 2);
                count++;
                i = 2;
                continue;
            }
        }
        hand = tempRummyCards;
        return count;
    }

//    public int isSet (){
//        int count = 0;
//        List<RummyCard> tempRummyCards = new ArrayList<>(hand);
//
//        Collections.sort(tempRummyCards, (a, b)->RummyCard.compareValue(a, b));
//
//        for (int i=2; i<tempRummyCards.size(); i++){
//            if ( isSetOfThree(tempRummyCards.get(i-2), tempRummyCards.get(i-1), tempRummyCards.get(i))){
//                count++;
//            }
//        }
//        return count;
//    }

    public static void main (String args[]){
        Rummy r = new Rummy();
        Deck rummyDeck = new Deck<RummyCard>(new DeckSpecification<RummyCard>() {
            public int noOfPacks() {
                return 2;
            }

            public boolean containJoker() {
                return true;
            }

            public int getJokerValue() {
                return 13;
            }

            public RummyCard generate(Suit s, Pip p) {
                return new RummyCard(s, p);
            }
        });

        Collections.sort(r.hand, new Comparator<RummyCard>() {
            public int compare(RummyCard o1, RummyCard o2) {
                return o1.compareSuits(o2);
            }
        });

        r.hand = rummyDeck.getNCards(13);
        System.out.println(r.hand);
        r.isPureSequence();
        r.isPotentialSequence();
        System.out.println("All Pure 4 - " + r.pureSeqOfFour);
        System.out.println("All Pure 3 - " + r.pureSeqOfThree);
        System.out.println("All potential 4 (3)- " + r.possibleSeqOfFourCountThree);
        System.out.println("All potential 4 (2)- " + r.possibleSeqOfFourCountTwo);
        System.out.println("All potential 3 - " + r.possibleSeqOfThree);
    }
}
