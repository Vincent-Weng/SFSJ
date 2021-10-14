package com.cosgame.sfsj.common;

import com.cosgame.sfsj.common.Card.CardRank;
import com.cosgame.sfsj.common.Card.CardSuit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameDeck {

  List<Card> deck;

  public GameDeck(int numOfDecks) {
    deck = new ArrayList<>(54 * numOfDecks);
    for (int i = 0; i < numOfDecks; i++) {
      for (CardRank num : CardRank.values()) {
        for (Card.CardSuit suit : Card.CardSuit.values()) {
          if (num == CardRank.JOKER || suit == CardSuit.RED_JOKER
              || suit == CardSuit.BLACK_JOKER) {
            continue;
          }
          deck.add(Card.of(num, suit));
        }
      }
      deck.add(Card.of(CardRank.JOKER, CardSuit.BLACK_JOKER));
      deck.add(Card.of(CardRank.JOKER, CardSuit.RED_JOKER));
    }
    Collections.shuffle(deck);
  }

  public List<Card> getDeck() {
    return deck;
  }

  public List<Card> getHiddenCards() {
    return deck.subList(0, 12);
  }

  public List<List<Card>> getPlayerCards() {
    return List.of(
        deck.subList(12, 63),
        deck.subList(63, 114),
        deck.subList(114, 165),
        deck.subList(165, 216));
  }

  @Override
  public String toString() {
    return deck.toString();
  }
}
