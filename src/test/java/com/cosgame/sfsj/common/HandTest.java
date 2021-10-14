package com.cosgame.sfsj.common;

import static com.cosgame.sfsj.util.CardUtils.cardOfRank;
import static org.junit.Assert.assertEquals;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

public class HandTest {

  @Test
  public void testGetPoints() {
    Hand hand1 = new Hand(ImmutableList.of(cardOfRank(5)));
    Hand hand2 = new Hand(ImmutableList.of(cardOfRank(10)));
    Hand hand3 = new Hand(ImmutableList.of(cardOfRank(13)));
    Hand hand4 = new Hand(ImmutableList.of(cardOfRank(6)));
    Hand hand5 = new Hand(ImmutableList.of(cardOfRank(5), cardOfRank(6)));
    Hand hand6 = new Hand(ImmutableList.of(cardOfRank(5), cardOfRank(5), cardOfRank(6)));
    Hand hand7 = new Hand(ImmutableList.of(cardOfRank(10), cardOfRank(11), cardOfRank(12), cardOfRank(13)));
    Hand hand8 = new Hand(ImmutableList.of(cardOfRank(6), cardOfRank(7), cardOfRank(8)));
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