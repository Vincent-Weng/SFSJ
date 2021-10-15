package com.cosgame.sfsj.play;

import static com.cosgame.sfsj.util.TestUtils.cardOfRank;
import static com.cosgame.sfsj.util.TestUtils.cards;
import static org.junit.Assert.assertEquals;

import com.cosgame.sfsj.common.Card.CardRank;
import com.cosgame.sfsj.common.Card.CardSuit;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

public class HandTest {

  @Test
  public void testStarterSplit() {
    // TODO
    Hand hand1 = new Hand(cards("JOJOjo2c2c2s3d"), CardRank.TWO, CardSuit.DIAMOND, false);
    System.out.println(hand1.starterSplit());
  }

  @Test
  public void testGetPoints() {
    Hand hand1 = new Hand(ImmutableList.of(cardOfRank(5)), CardRank.ACE, CardSuit.CLUB, false);
    Hand hand2 = new Hand(ImmutableList.of(cardOfRank(10)), CardRank.ACE, CardSuit.CLUB, false);
    Hand hand3 = new Hand(ImmutableList.of(cardOfRank(13)), CardRank.ACE, CardSuit.CLUB, false);
    Hand hand4 = new Hand(ImmutableList.of(cardOfRank(6)), CardRank.ACE, CardSuit.CLUB, false);
    Hand hand5 = new Hand(ImmutableList.of(cardOfRank(5), cardOfRank(6)), CardRank.ACE,
        CardSuit.CLUB, false);
    Hand hand6 = new Hand(ImmutableList.of(cardOfRank(5), cardOfRank(5), cardOfRank(6)),
        CardRank.ACE, CardSuit.CLUB, false);
    Hand hand7 = new Hand(
        ImmutableList.of(cardOfRank(10), cardOfRank(11), cardOfRank(12), cardOfRank(13)),
        CardRank.ACE, CardSuit.CLUB, false);
    Hand hand8 = new Hand(ImmutableList.of(cardOfRank(6), cardOfRank(7), cardOfRank(8)),
        CardRank.ACE, CardSuit.CLUB, false);
    assertEquals(5, hand1.getPoints());
    assertEquals(10, hand2.getPoints());
    assertEquals(10, hand3.getPoints());
    assertEquals(0, hand4.getPoints());
    assertEquals(5, hand5.getPoints());
    assertEquals(10, hand6.getPoints());
    assertEquals(20, hand7.getPoints());
    assertEquals(0, hand8.getPoints());
  }
}