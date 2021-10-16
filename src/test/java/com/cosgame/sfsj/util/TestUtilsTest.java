package com.cosgame.sfsj.util;

import static org.junit.Assert.assertEquals;

import com.cosgame.sfsj.common.Card;
import com.cosgame.sfsj.common.Card.CardRank;
import com.cosgame.sfsj.common.Card.CardSuit;
import com.cosgame.sfsj.common.GameDeck;
import org.junit.Test;

public class TestUtilsTest {

  @Test
  public void TestPrettyPrint() {
    GameDeck deck = new GameDeck(1);
    String printRes = TestUtils.prettyPrintCards(deck.getDeck(), 14);
    // 4 card rows, times 5 text lines for each card row
    assertEquals(4 * 5, printRes.split("\n").length);
    System.out.println(
        "=== This Test CANNOT be automated. Please visual check for pretty print ===");
    System.out.println(printRes);
  }


  @Test
  public void testGetCardOfRank() {
    assertEquals(Card.of(CardRank.ACE, CardSuit.CLUB).getRank(),
        TestUtils.cardOfRank(1).getRank());
    assertEquals(Card.of(CardRank.FIVE, CardSuit.CLUB).getRank(),
        TestUtils.cardOfRank(5).getRank());
    assertEquals(Card.of(CardRank.TEN, CardSuit.CLUB).getRank(),
        TestUtils.cardOfRank(10).getRank());
    assertEquals(Card.of(CardRank.KING, CardSuit.CLUB).getRank(),
        TestUtils.cardOfRank(13).getRank());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCardOfRankIllegalSmall() {
    TestUtils.cardOfRank(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCardOfRankIllegalLarge() {
    TestUtils.cardOfRank(14);
  }
}