package com.cosgame.sfsj.util;

import static org.junit.Assert.assertEquals;

import com.cosgame.sfsj.common.Card;
import com.cosgame.sfsj.common.Card.CardRank;
import com.cosgame.sfsj.common.Card.CardSuit;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class CardUtilsTest {

  @Test
  public void testGetCardOfRank() {
    assertEquals(Card.of(CardRank.ACE, CardSuit.CLUB).getRank(),
        CardUtils.cardOfRank(1).getRank());
    assertEquals(Card.of(CardRank.FIVE, CardSuit.CLUB).getRank(),
        CardUtils.cardOfRank(5).getRank());
    assertEquals(Card.of(CardRank.TEN, CardSuit.CLUB).getRank(),
        CardUtils.cardOfRank(10).getRank());
    assertEquals(Card.of(CardRank.KING, CardSuit.CLUB).getRank(),
        CardUtils.cardOfRank(13).getRank());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCardOfRankIllegalSmall() {
    CardUtils.cardOfRank(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCardOfRankIllegalLarge() {
    CardUtils.cardOfRank(14);
  }

  @Test
  public void testCardComparator() {
    List<Card> cards1 = List.of(
        Card.of(CardRank.JOKER, CardSuit.RED_JOKER),
        Card.of(CardRank.JOKER, CardSuit.BLACK_JOKER),
        Card.of(CardRank.NINE, CardSuit.CLUB),
        Card.of(CardRank.NINE, CardSuit.SPADE),
        Card.of(CardRank.NINE, CardSuit.HEART),
        Card.of(CardRank.NINE, CardSuit.DIAMOND),
        Card.of(CardRank.ACE, CardSuit.CLUB),
        Card.of(CardRank.FIVE, CardSuit.CLUB),
        Card.of(CardRank.JACK, CardSuit.SPADE),
        Card.of(CardRank.QUEEN, CardSuit.HEART),
        Card.of(CardRank.KING, CardSuit.DIAMOND));
    List<Card> cards2 = new ArrayList<>(cards1);
    cards2.sort(CardUtils.cardComparator(CardRank.NINE, CardSuit.CLUB));
    assertEquals(cards1, cards2);
  }
}
