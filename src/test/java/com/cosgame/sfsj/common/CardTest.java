package com.cosgame.sfsj.common;

import static org.junit.Assert.assertEquals;

import com.cosgame.sfsj.common.Card.CardRank;
import com.cosgame.sfsj.common.Card.CardSuit;
import com.google.common.collect.ImmutableList;
import java.util.List;
import org.junit.Test;

public class CardTest {

  @Test
  public void testCompare() {
    // Have to explicitly list all cards with correct sort. Use of any loop/index to generate card 
    // automatically could hide potential issues in card definition.
    List<Card> allCards = ImmutableList.of(
        // JOKERS
        Card.of(CardRank.JOKER, CardSuit.RED_JOKER),
        Card.of(CardRank.JOKER, CardSuit.BLACK_JOKER),
        // SPADE
        Card.of(CardRank.ACE, CardSuit.SPADE),
        Card.of(CardRank.KING, CardSuit.SPADE),
        Card.of(CardRank.QUEEN, CardSuit.SPADE),
        Card.of(CardRank.JACK, CardSuit.SPADE),
        Card.of(CardRank.TEN, CardSuit.SPADE),
        Card.of(CardRank.NINE, CardSuit.SPADE),
        Card.of(CardRank.EIGHT, CardSuit.SPADE),
        Card.of(CardRank.SEVEN, CardSuit.SPADE),
        Card.of(CardRank.SIX, CardSuit.SPADE),
        Card.of(CardRank.FIVE, CardSuit.SPADE),
        Card.of(CardRank.FOUR, CardSuit.SPADE),
        Card.of(CardRank.THREE, CardSuit.SPADE),
        Card.of(CardRank.TWO, CardSuit.SPADE),
        // HEART
        Card.of(CardRank.ACE, CardSuit.HEART),
        Card.of(CardRank.KING, CardSuit.HEART),
        Card.of(CardRank.QUEEN, CardSuit.HEART),
        Card.of(CardRank.JACK, CardSuit.HEART),
        Card.of(CardRank.TEN, CardSuit.HEART),
        Card.of(CardRank.NINE, CardSuit.HEART),
        Card.of(CardRank.EIGHT, CardSuit.HEART),
        Card.of(CardRank.SEVEN, CardSuit.HEART),
        Card.of(CardRank.SIX, CardSuit.HEART),
        Card.of(CardRank.FIVE, CardSuit.HEART),
        Card.of(CardRank.FOUR, CardSuit.HEART),
        Card.of(CardRank.THREE, CardSuit.HEART),
        Card.of(CardRank.TWO, CardSuit.HEART),
        // CLUB
        Card.of(CardRank.ACE, CardSuit.CLUB),
        Card.of(CardRank.KING, CardSuit.CLUB),
        Card.of(CardRank.QUEEN, CardSuit.CLUB),
        Card.of(CardRank.JACK, CardSuit.CLUB),
        Card.of(CardRank.TEN, CardSuit.CLUB),
        Card.of(CardRank.NINE, CardSuit.CLUB),
        Card.of(CardRank.EIGHT, CardSuit.CLUB),
        Card.of(CardRank.SEVEN, CardSuit.CLUB),
        Card.of(CardRank.SIX, CardSuit.CLUB),
        Card.of(CardRank.FIVE, CardSuit.CLUB),
        Card.of(CardRank.FOUR, CardSuit.CLUB),
        Card.of(CardRank.THREE, CardSuit.CLUB),
        Card.of(CardRank.TWO, CardSuit.CLUB),
        // DIAMOND
        Card.of(CardRank.ACE, CardSuit.DIAMOND),
        Card.of(CardRank.KING, CardSuit.DIAMOND),
        Card.of(CardRank.QUEEN, CardSuit.DIAMOND),
        Card.of(CardRank.JACK, CardSuit.DIAMOND),
        Card.of(CardRank.TEN, CardSuit.DIAMOND),
        Card.of(CardRank.NINE, CardSuit.DIAMOND),
        Card.of(CardRank.EIGHT, CardSuit.DIAMOND),
        Card.of(CardRank.SEVEN, CardSuit.DIAMOND),
        Card.of(CardRank.SIX, CardSuit.DIAMOND),
        Card.of(CardRank.FIVE, CardSuit.DIAMOND),
        Card.of(CardRank.FOUR, CardSuit.DIAMOND),
        Card.of(CardRank.THREE, CardSuit.DIAMOND),
        Card.of(CardRank.TWO, CardSuit.DIAMOND));

    for (int i = 0; i < 53; i++) {
      Card c1 = allCards.get(i);
      Card c2 = allCards.get(i + 1);
      assertEquals(-1, c1.compareTo(c2));
    }
  }
}