package com.cosgame.sfsj.common;

import com.cosgame.sfsj.common.Card.CardRank;
import com.cosgame.sfsj.common.Card.CardSuit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck{

    List<Card> deck;

    public Deck() {
        deck = new ArrayList<>(54);
        for (CardRank num : CardRank.values()) {
            for (Card.CardSuit suit : Card.CardSuit.values()) {
                if (num == CardRank.JOKER || suit == CardSuit.RED_JOKER || suit == CardSuit.BLACK_JOKER) {
                    continue;
                }
                deck.add(Card.of(num, suit));
            }
        }
        deck.add(Card.of(CardRank.JOKER, CardSuit.BLACK_JOKER));
        deck.add(Card.of(CardRank.JOKER, CardSuit.RED_JOKER));
        Collections.shuffle(deck);
    }

    public List<Card> getDeck() {
        return deck;
    }

    public Card getCard(int i) {
        return deck.get(i);
    }

    @Override
    public String toString() {
        return deck.toString();
    }
}
