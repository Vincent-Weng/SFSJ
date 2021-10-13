package com.cosgame.sfsj.util;

import static org.junit.Assert.assertEquals;

import com.cosgame.sfsj.common.Card;
import com.cosgame.sfsj.common.Card.CardRank;
import com.cosgame.sfsj.common.Card.CardSuit;
import org.junit.Test;

public class CardUtilsTest {

  @Test
  public void testGetCardOfRank() {
    assertEquals(Card.of(CardRank.ACE, CardSuit.CLUB).getRank(), CardUtils.cardOfRank(1).getRank());
    assertEquals(Card.of(CardRank.FIVE, CardSuit.CLUB).getRank(), CardUtils.cardOfRank(5).getRank());
    assertEquals(Card.of(CardRank.TEN, CardSuit.CLUB).getRank(), CardUtils.cardOfRank(10).getRank());
    assertEquals(Card.of(CardRank.KING, CardSuit.CLUB).getRank(), CardUtils.cardOfRank(13).getRank());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCardOfRankIllegalSmall() {
    CardUtils.cardOfRank(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCardOfRankIllegalLarge() {
    CardUtils.cardOfRank(14);
  }
}
